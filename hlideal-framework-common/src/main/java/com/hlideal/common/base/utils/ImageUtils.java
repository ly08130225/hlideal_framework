package com.hlideal.common.base.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang3.StringUtils;

/***
 * 对图片进行操作
 * 
 * @author chenzheng_java
 * @since 2011/7/29
 * 
 */
public class ImageUtils {

	private static ImageUtils imageUtils = null;

	public static ImageUtils getImageUtils() {
		if (imageUtils == null) {
			imageUtils = new ImageUtils();
		}
		return imageUtils;
	}

	/***
	 * 按指定的比例缩放图片
	 * 
	 * @param sourceImagePath
	 *            源地址
	 * @param destinationPath
	 *            改变大小后图片的地址
	 * @param scale
	 *            缩放比例，如1.2
	 */
	public static boolean scaleImage(String sourceImagePath, String destinationPath, double scale, String format) {

		File file = new File(sourceImagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();

			width = parseDoubleToInt(width * scale);
			height = parseDoubleToInt(height * scale);

			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();

			ImageIO.write(outputImage, format, new File(destinationPath));
			return true;
		} catch (IOException e) {
			;
		}
		return false;

	}

	public static boolean scaleImage(String sourceImagePath, String destinationPath) {
		File file = new File(sourceImagePath);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			int width = bufferedImage.getWidth();
			if(width > 1024){
				return scaleImage(sourceImagePath, destinationPath, getDot2Decimal(1024, width), "jpg");
			}else{
				return compressPic(sourceImagePath, destinationPath);
			}
		} catch (IOException e) {
			System.out.println("scaleImage方法压缩图片时出错了");
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean compressPic(String srcFilePath, String descFilePath) {
		ImageIO.setUseCache(false);
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;
		
		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality(0.5f);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
			colorModel.createCompatibleSampleModel(16, 16)));
		
		try {
			if (StringUtils.isEmpty(srcFilePath)) {
				return false;
			} else {
				file = new File(srcFilePath);
				src = ImageIO.read(file);
				out = new FileOutputStream(descFilePath);
				
				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/***
	 * 将图片缩放到指定的高度或者宽度
	 * 
	 * @param sourceImagePath
	 *            图片源地址
	 * @param destinationPath
	 *            压缩完图片的地址
	 * @param width
	 *            缩放后的宽度
	 * @param height
	 *            缩放后的高度
	 * @param auto
	 *            是否自动保持图片的原高宽比例
	 * @param format
	 *            图图片格式 例如 jpg
	 */
	public static void scaleImageWithParams(String sourceImagePath, String destinationPath, int width, int height, boolean auto, String format) {

		try {
			File file = new File(sourceImagePath);
			BufferedImage bufferedImage = null;
			bufferedImage = ImageIO.read(file);
			if (auto) {
				ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage, width, height);
				width = paramsArrayList.get(0);
				height = paramsArrayList.get(1);
				System.out.println("自动调整比例，width=" + width + " height=" + height);
			}

			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();
			ImageIO.write(outputImage, format, new File(destinationPath));
		} catch (Exception e) {
			System.out.println("scaleImageWithParams方法压缩图片时出错了");
			e.printStackTrace();
		}

	}

	/**
	 * 将double类型的数据转换为int，四舍五入原则
	 * 
	 * @param sourceDouble
	 * @return
	 */
	private static int parseDoubleToInt(double sourceDouble) {
		int result = 0;
		result = (int) sourceDouble;
		return result;
	}

	/***
	 * 
	 * @param bufferedImage
	 *            要缩放的图片对象
	 * @param width_scale
	 *            要缩放到的宽度
	 * @param height_scale
	 *            要缩放到的高度
	 * @return 一个集合，第一个元素为宽度，第二个元素为高度
	 */
	private static ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage, int width_scale, int height_scale) {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		double scale_w = getDot2Decimal(width_scale, width);

		System.out.println("getAutoWidthAndHeight width=" + width + "scale_w=" + scale_w);
		double scale_h = getDot2Decimal(height_scale, height);
		if (scale_w < scale_h) {
			arrayList.add(parseDoubleToInt(scale_w * width));
			arrayList.add(parseDoubleToInt(scale_w * height));
		} else {
			arrayList.add(parseDoubleToInt(scale_h * width));
			arrayList.add(parseDoubleToInt(scale_h * height));
		}
		return arrayList;

	}

	/***
	 * 返回两个数a/b的小数点后三位的表示
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getDot2Decimal(int a, int b) {

		BigDecimal bigDecimal_1 = new BigDecimal(a);
		BigDecimal bigDecimal_2 = new BigDecimal(b);
		BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2, new MathContext(4));
		Double double1 = new Double(bigDecimal_result.toString());
		System.out.println("相除后的double为：" + double1);
		return double1;
	}

}