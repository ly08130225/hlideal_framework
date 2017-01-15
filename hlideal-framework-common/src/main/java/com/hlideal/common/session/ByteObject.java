package com.hlideal.common.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteObject {
	public static Object bytes2Object(byte[] bytes) {
		if ((bytes == null) || (bytes.length < 1))
			return null;
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object object = objectInputStream.readObject();
			objectInputStream.close();
			byteArrayInputStream.close();
			return object;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ByteObjectConvetException(e, "字节数组转为对象失败。。。");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ByteObjectConvetException(e, "字节数组转为对象失败。。。");
		}
	}

	public static byte[] object2Bytes(Object object) {
		if (object == null)
			return null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			byte[] bytes = byteArrayOutputStream.toByteArray();
			objectOutputStream.close();
			byteArrayOutputStream.close();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ByteObjectConvetException(e, "对象转为字节数组失败。。。");
		}
	}
}