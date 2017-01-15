package com.hlideal.framework.admin.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlideal.framework.admin.AdminBaseController;
import com.hlideal.framework.admin.utils.UserUtils;
import com.hlideal.framework.core.entity.Sysparam;
import com.hlideal.framework.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import com.hlideal.common.base.utils.StringUtils;
import com.hlideal.common.base.utils.WebUtil;
import com.hlideal.common.mybatis.Global;
import com.hlideal.common.mybatis.Page;
import com.hlideal.framework.core.dao.SysfunctionDao;
import com.hlideal.framework.core.entity.Sysdic;
import com.hlideal.framework.core.enums.DicCateName;

@Controller
@RequestMapping(value = "/admin/backstage/sys/dic")
public class DicManageController extends AdminBaseController {
	String INDEX_PATH = "/admin/sys/dic/";
	
	@Autowired
	private SysfunctionDao sysfunctionDao;
	
	@Autowired
	private SysroleService sysroleService;
	
	@Autowired
	private SysfunctionService sysfunctionService;
	
	@Autowired
	private SysrolefunctionService sysrolefunctionService;
	
	@Autowired
	private SysdicService sysdicService;

	@Autowired
	private SysparamService sysparamService;
	
	//url
	@RequestMapping("dicmanage01.htm")
	public String dicmanage01(HttpServletRequest request, HttpServletResponse response, Model model) {
		//
		model.addAttribute("catekey", DicCateName.PROCESSNODE);//进度节点
		return INDEX_PATH + "dicmanage01.vm";
	}
	
	@RequestMapping("dicmanage02.htm")
	public String dicmanage02(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("catekey", DicCateName.ORDERSOURCE);//订单来源
		return INDEX_PATH + "dicmanage02.vm";
	}
	@RequestMapping("dicmanage03.htm")
	public String dicmanage03(HttpServletRequest request, HttpServletResponse response, Model model) {		
		model.addAttribute("catekey", DicCateName.CLASSTYPE);//类别
		return INDEX_PATH + "dicmanage03.vm";
	}
	@RequestMapping("dicmanage04.htm")
	public String dicmanage04(HttpServletRequest request, HttpServletResponse response, Model model) {		
		model.addAttribute("catekey", DicCateName.STRUCTURETYPE);//结构档案
		return INDEX_PATH + "dicmanage04.vm";
	}
	@RequestMapping("dicmanage05.htm")
	public String dicmanage05(HttpServletRequest request, HttpServletResponse response, Model model) {		
		model.addAttribute("catekey", DicCateName.AGENTTYPE);//代理商
		return INDEX_PATH + "dicmanage05.vm";
	}
	@RequestMapping("dicmanage06.htm")
	public String dicmanage06(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("catekey", DicCateName.AGENTTYPE);//属性
		return INDEX_PATH + "dicmanage06.vm";
	}
	
	//url结束
	@RequestMapping("queryDicList.htm")
	@ResponseBody
	public void queryDicList(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		Sysdic paramobj = new Sysdic();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		if(!StringUtils.isEmpty(paramobj.getCatekey())){
			sb.append(" and a.catekey='"+paramobj.getCatekey()+"'");
		}else{
			sb.append(" and 1=0 ");
		}
		if(!StringUtils.isEmpty(paramobj.getDicvalue())){
			sb.append(" and a.dicvalue='"+paramobj.getDicvalue()+"'");
		}
		Page<Sysdic> page = sysdicService.findPageByCondition(new Page<Sysdic>(request, response), paramobj, sb.toString());
		json.put("page", page);
		renderString(response, json, jsonCallBack);
	}

	@RequestMapping("queryParamList.htm")
	@ResponseBody
	public void queryParamList(HttpServletRequest request,HttpServletResponse response, String jsonCallBack){
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		Sysparam paramobj = new Sysparam();
		WebUtil.setPoPropertyByRequest(paramobj, request);

		Page<Sysparam> page = sysparamService.findPageByCondition(new Page<Sysparam>(request, response), paramobj, sb.toString());
		json.put("page", page);
		renderString(response, json, jsonCallBack);
	}

	@RequestMapping("saveParam.htm")
	@ResponseBody
	public void saveParam(HttpServletRequest request, HttpServletResponse response, String jsonCallBack){
		JSONObject json = new JSONObject();
		Sysparam paramobj = new Sysparam();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		Sysparam saveobj = new Sysparam();

		try {
			if (StringUtils.isEmpty(paramobj.getId())) {
                saveobj.setIsNewRecord(true);
                saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
                saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
            } else {
                saveobj = sysparamService.get(paramobj.getId());
            }

			saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
			saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
			saveobj.setParamvalue(paramobj.getParamvalue());
			saveobj.setParamkey(paramobj.getParamkey());
			saveobj.setDeleteflag(String.valueOf(0));
			sysparamService.save(saveobj);

			json.put("code", 1);
			json.put("message", "保存成功");
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);

	}

	@RequestMapping("deleteParamData.htm")
	@ResponseBody
	public void deleteParamData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			if(!StringUtils.isEmpty(ids)){
				List<String> listids = StringUtils.stringSplitToList(ids,",");
				if(listids!= null && listids.size() > 0){
					for(String id: listids){
						Sysparam delobj = sysparamService.get(id);
						if(delobj!= null && !StringUtils.isEmpty(delobj.getId())){
							delobj.setDeleteflag(Global.YES);
							sysparamService.save(delobj);
						}
					}
					json.put("code", 1);
					json.put("message", "保存成功");
				}
			}

		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}

	@RequestMapping("upload_iframe.htm")
	public String upload_iframe(){
		return INDEX_PATH + "upload_file.vm";
	}

	
	//url结束
	@RequestMapping("queryDicList02.htm")
	@ResponseBody
	public void queryDicList02(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		StringBuilder sb = new StringBuilder();
		Sysdic paramobj = new Sysdic();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		if(!StringUtils.isEmpty(paramobj.getCatekey())){
			sb.append(" and a.catekey='"+paramobj.getCatekey()+"'");
		}else{
			sb.append(" and 1=0 ");
		}
		if(!StringUtils.isEmpty(paramobj.getDicvalue())){
			sb.append(" and a.dicvalue='"+paramobj.getDicvalue()+"'");
		}
		List<Sysdic> list = sysdicService.findList(paramobj, sb.toString());
		renderString(response, list, jsonCallBack);
	}
	
	@RequestMapping("dicSaveData.htm")
	@ResponseBody
	public void roleSaveData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		Sysdic paramobj = new Sysdic();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		Sysdic saveobj = new Sysdic();
		try {
			if (StringUtils.isEmpty(paramobj.getId())) {
				saveobj.setIsNewRecord(true);
				saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
				saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
			} else {
				saveobj = sysdicService.get(paramobj.getId());
			}

			saveobj.setCateid(paramobj.getCateid());
			saveobj.setCatekey(paramobj.getCatekey());
			saveobj.setParentid(paramobj.getParentid());
			saveobj.setChecktree(paramobj.getChecktree());
			saveobj.setDickey(paramobj.getDickey());
			saveobj.setDicvalue(paramobj.getDicvalue());
			saveobj.setMinvalue(paramobj.getMinvalue());
			saveobj.setMaxvalue(paramobj.getMaxvalue());
			saveobj.setVar01(paramobj.getVar01());
			saveobj.setVar02(paramobj.getVar02());
			saveobj.setVar03(paramobj.getVar03());
			saveobj.setVar04(paramobj.getVar04());
			saveobj.setVar05(paramobj.getVar05());
			saveobj.setVar06(paramobj.getVar06());
			saveobj.setVar07(paramobj.getVar07());
			saveobj.setVar08(paramobj.getVar08());
			saveobj.setVar09(paramobj.getVar09());
			saveobj.setVar10(paramobj.getVar10());
			sysdicService.save(saveobj);

			json.put("code", 1);
			json.put("message", "保存成功");
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("dicDeleteData.htm")
	@ResponseBody
	public void roleDeleteData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			if(!StringUtils.isEmpty(ids)){
				List<String> listids = StringUtils.stringSplitToList(ids,",");
				if(listids!= null && listids.size() > 0){
					for(String id: listids){
						Sysdic delobj = sysdicService.get(id);
						if(delobj!= null && !StringUtils.isEmpty(delobj.getId())){
							delobj.setDeleteflag(Global.YES);
							sysdicService.save(delobj);	
						}
					}
					json.put("code", 1);
					json.put("message", "保存成功");
				}
			}
			
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}	
}
