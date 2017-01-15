package com.hlideal.framework.admin.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.hlideal.framework.admin.AdminBaseController;
import com.hlideal.common.base.utils.StringUtils;
import com.hlideal.common.base.utils.WebUtil;
import com.hlideal.common.base.TreeDto;
import com.hlideal.common.mybatis.Global;
import com.hlideal.common.mybatis.Page;
import com.hlideal.common.mybatis.mapper.JsonMapper;
import com.hlideal.common.security.MD5Util;
import com.hlideal.framework.admin.utils.UserUtils;
import com.hlideal.framework.core.dao.SysfunctionDao;
import com.hlideal.framework.core.entity.Sysdic;
import com.hlideal.framework.core.entity.Sysfunction;
import com.hlideal.framework.core.entity.Sysrole;
import com.hlideal.framework.core.entity.Sysrolefunction;
import com.hlideal.framework.core.entity.Sysstaff;
import com.hlideal.framework.core.entity.Sysstaffdic;
import com.hlideal.framework.core.entity.Sysstaffrole;
import com.hlideal.framework.core.service.SysdicService;
import com.hlideal.framework.core.service.SysfunctionService;
import com.hlideal.framework.core.service.SysroleService;
import com.hlideal.framework.core.service.SysrolefunctionService;
import com.hlideal.framework.core.service.SysstaffService;
import com.hlideal.framework.core.service.SysstaffdicService;
import com.hlideal.framework.core.service.SysstaffroleService;

@Controller
@RequestMapping(value = "/admin/backstage/sys")
public class UserManageController extends AdminBaseController {
	String INDEX_PATH = "/admin/sys/";
	
	@Autowired
	private SysfunctionDao sysfunctionDao;
	
	@Autowired
	private SysroleService sysroleService;
	
	@Autowired
	private SysfunctionService sysfunctionService;
	
	@Autowired
	private SysrolefunctionService sysrolefunctionService;
	
	@Autowired
	private SysstaffroleService sysstaffroleService;
	
	@Autowired
	private SysstaffService sysstaffService;
	
	@Autowired
	private SysstaffdicService sysstaffdicService;
	
	@Autowired
	private SysdicService sysdicService;

	//url
	@RequestMapping("usermanage.htm")
	public String usermanage(HttpServletRequest request, HttpServletResponse response, Model model) {		
		return INDEX_PATH + "usermanage.vm";
	}
	
	@RequestMapping("staffroleset.htm")
	public String staffroleset(HttpServletRequest request, HttpServletResponse response, Model model) {
		String staffid = WebUtil.getPoPropertyByRequest(request, "staffid");
		StringBuilder sb = new StringBuilder();
		sb.append(" and a.staffid='"+staffid+"'");
		List<Sysstaffrole> liststaffrole = sysstaffroleService.findList(new Sysstaffrole(), sb.toString());		
		StringBuilder staffroles = new StringBuilder();
		if(liststaffrole!= null&& liststaffrole.size()>0){
			boolean flag=false;
			for(Sysstaffrole item:liststaffrole){
				if (flag) {
					staffroles.append(",");
	            }else {
	                flag=true;
	            }
				staffroles.append(item.getRoleid());
			}
		}		
		List<Sysrole> listroles = sysroleService.findList(new Sysrole());
		model.addAttribute("staffid", staffid);
		model.addAttribute("staffroles", staffroles.toString());
		model.addAttribute("listroles", JsonMapper.toJsonString(listroles).replace("\"", "'"));
		return INDEX_PATH + "staffroleset.vm";
	}
	
	@RequestMapping("staffdicset.htm")
	public String staffdicset(HttpServletRequest request, HttpServletResponse response, Model model) {
		String staffid = WebUtil.getPoPropertyByRequest(request, "staffid");
		String dictype = WebUtil.getPoPropertyByRequest(request, "dictype");
		StringBuilder sb = new StringBuilder();
		sb.append(" and a.staffid='"+staffid+"'");
		sb.append(" and a.dictype='"+dictype+"'");
		List<Sysstaffdic> liststaffdic = sysstaffdicService.findList(new Sysstaffdic(), sb.toString());		
		String staffdicvalue = "";
		String staffdictext = "";
		if(liststaffdic!= null&& liststaffdic.size()>0){
			staffdicvalue = liststaffdic.get(0).getDicvalue();
			staffdictext = liststaffdic.get(0).getDictext();
		}	
		
		List<Sysdic> listdics = sysdicService.findList(new Sysdic(), " and a.catekey='"+dictype +"'");
		model.addAttribute("staffid", staffid);
		model.addAttribute("dictype", dictype);
		model.addAttribute("staffdicvalue", staffdicvalue);
		model.addAttribute("staffdictext", staffdictext);
		
		model.addAttribute("listdics", JsonMapper.toJsonString(listdics).replace("\"", "'"));
		return INDEX_PATH + "staffdicset.vm";
	}
	
	@RequestMapping("rolemanage.htm")
	public String rolemanage(HttpServletRequest request, HttpServletResponse response, Model model) {		
		return INDEX_PATH + "rolemanage.vm";
	}
	
	@RequestMapping("rolefunctionassign.htm")
	public String rolefunctionassign(HttpServletRequest request, HttpServletResponse response, Model model) {
		String roleid = WebUtil.getPoPropertyByRequest(request, "roleid");		
		model.addAttribute("roleid", roleid);
		return INDEX_PATH + "rolefunctionassign.vm";
	}	
	//url结束
	
	//用户管理
	
	@RequestMapping("queryStaffList.htm")
	@ResponseBody
	public void queryStaffList(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		Sysstaff paramobj = new Sysstaff();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		if(!StringUtils.isEmpty(paramobj.getName()))
		{
			sb.append(" and a.name like '%" + paramobj.getName() + "%'");
		}	
		Page<Sysstaff> page = sysstaffService.findPageByCondition(new Page<Sysstaff>(request, response), paramobj, sb.toString());
		json.put("page", page);
		renderString(response, json, jsonCallBack);
	}
	
	//保存用户相关信息
	@RequestMapping("staffSaveData.htm")
	@ResponseBody
	public void staffSaveData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		Sysstaff paramobj = new Sysstaff();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		Sysstaff saveobj = new Sysstaff();
		try {
			if (StringUtils.isEmpty(paramobj.getId())) {
				saveobj.setLoginflag(Global.YES);
				saveobj.setAdminstatus(Global.NO);
				saveobj.setIsNewRecord(true);
				saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
				saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
			} else {
				saveobj = sysstaffService.get(paramobj.getId());
			}
			saveobj.setStaffno(paramobj.getStaffno());
			saveobj.setLoginname(paramobj.getLoginname());
			saveobj.setName(paramobj.getName());		
			saveobj.setMobilephone(paramobj.getMobilephone());
			if(!StringUtils.isEmpty(paramobj.getLoginpwd())){
				saveobj.setLoginpwd(MD5Util.getMD5_32(paramobj.getLoginpwd()));
			}
			saveobj.setRemark(paramobj.getRemark());
			saveobj.setLoginflag(paramobj.getLoginflag());
						
			sysstaffService.save(saveobj);

			json.put("code", 1);
			json.put("message", "保存成功");
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("staffDeleteData.htm")
	@ResponseBody
	public void staffDeleteData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			if(!StringUtils.isEmpty(ids)){
				List<String> listids = StringUtils.stringSplitToList(ids,",");
				if(listids!= null && listids.size() > 0){
					for(String id: listids){
						Sysstaff delobj = sysstaffService.get(id);
						if(delobj!= null && !StringUtils.isEmpty(delobj.getId())){
							delobj.setDeleteflag(Global.YES);
							sysstaffService.save(delobj);	
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
	
	@RequestMapping("staffRoleSave.htm")
	@ResponseBody
	public void staffRoleSave(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			String staffid = WebUtil.getPoPropertyByRequest(request, "staffid");
			if(!StringUtils.isEmpty(staffid)){
				Sysstaffrole search = new Sysstaffrole();
				search.setStaffid(staffid);
				sysstaffroleService.deleteBystaffid(search);
				
				List<String> listids = StringUtils.stringSplitToList(ids,",");
				if(listids!= null && listids.size() > 0){
					for(String item: listids){
						Sysstaffrole saveobj = new Sysstaffrole();
						saveobj.setRoleid(item);	
						saveobj.setStaffid(staffid);
						sysstaffroleService.save(saveobj);
					}				
				}
				json.put("code", 1);
				json.put("message", "保存成功");
			}
			
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("staffDicSave.htm")
	@ResponseBody
	public void staffDicSave(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			String staffid = WebUtil.getPoPropertyByRequest(request, "staffid");
			String dictype = WebUtil.getPoPropertyByRequest(request, "dictype");
			String idsText = WebUtil.getPoPropertyByRequest(request, "idsText");
			if(!StringUtils.isEmpty(staffid)&&!StringUtils.isEmpty(dictype)){
				Sysstaffdic saveobj = new Sysstaffdic();
				StringBuilder sb = new StringBuilder();
				sb.append(" and a.staffid='"+staffid+"'");
				sb.append(" and a.dictype='"+dictype+"'");
				List<Sysstaffdic> liststaffdic = sysstaffdicService.findList(new Sysstaffdic(), sb.toString());
				if(liststaffdic!= null && liststaffdic.size() > 0){
					saveobj = liststaffdic.get(0);
				}else{
					saveobj.setDictype(dictype);
					saveobj.setStaffid(staffid);
					saveobj.setIsNewRecord(true);
					saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
					saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
				}
				saveobj.setDicvalue(ids);
				saveobj.setDictext(idsText);
				sysstaffdicService.save(saveobj);
				
				json.put("code", 1);
				json.put("message", "保存成功");
			}
			
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}	
	
	//角色管理
	@RequestMapping("queryRoleList.htm")
	@ResponseBody
	public void queryRoleList(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		Sysrole paramobj = new Sysrole();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		if(!StringUtils.isEmpty(paramobj.getName()))
		{
			sb.append(" and a.name like '%" + paramobj.getName() + "%'");
		}	
		Page<Sysrole> page = sysroleService.findPageByCondition(new Page<Sysrole>(request, response), paramobj, sb.toString());
		json.put("page", page);
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("roleSaveData.htm")
	@ResponseBody
	public void roleSaveData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		Sysrole paramobj = new Sysrole();
		WebUtil.setPoPropertyByRequest(paramobj, request);
		Sysrole saveobj = new Sysrole();
		try {
			if (StringUtils.isEmpty(paramobj.getId())) {
				saveobj.setIsNewRecord(true);
				saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
				saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
			} else {
				saveobj = sysroleService.get(paramobj.getId());
			}

			saveobj.setName(paramobj.getName());
			saveobj.setRemark(paramobj.getRemark());
			sysroleService.save(saveobj);

			json.put("code", 1);
			json.put("message", "保存成功");
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("roleDeleteData.htm")
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
						Sysrole delobj = sysroleService.get(id);
						if(delobj!= null && !StringUtils.isEmpty(delobj.getId())){
							delobj.setDeleteflag(Global.YES);
							sysroleService.save(delobj);	
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
	
	@RequestMapping("roleFunctionData.htm")
	@ResponseBody
	public void roleFunctionData(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		String roleid = WebUtil.getPoPropertyByRequest(request, "roleid");
		List<TreeDto> listdata = new ArrayList<TreeDto>();
		if(!StringUtils.isEmpty(roleid)){
			List<Sysfunction> listpre = sysfunctionService.findList(new Sysfunction());		
			Sysfunction search = new Sysfunction();
			search.setVar01(roleid);			
			List<Sysfunction> ownlist = new ArrayList<Sysfunction>();
			ownlist = sysfunctionDao.findByRoleID(search);	
			
			if(listpre!= null && listpre.size()>0){
				for(Sysfunction pre: listpre){
					boolean checkhave = false;
					for(Sysfunction own: ownlist){
						if(StringUtils.equals(pre.getId(), own.getId())){
							checkhave = true;
						}
					}
					Map<String, String> attributes = new HashMap<String, String>();
					TreeDto dto = new TreeDto();
					dto.setId(pre.getId());
					dto.setpId(pre.getParentid());
					dto.setName(pre.getFunctionname());
					attributes.put("url", pre.getLinkurl());
					dto.setAttributes(attributes);
					dto.setChecked(checkhave);
					listdata.add(dto);
				}
			}	
		}
		json.put("result", listdata);
		renderString(response, json, jsonCallBack);
	}
	
	@RequestMapping("roleFunctionSave.htm")
	@ResponseBody
	public void roleFunctionSave(HttpServletRequest request, HttpServletResponse response, Model model, String jsonCallBack) {		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");
		try {
			String ids = WebUtil.getPoPropertyByRequest(request, "ids");
			String roleid = WebUtil.getPoPropertyByRequest(request, "roleid");
			if(!StringUtils.isEmpty(roleid)){
				Sysrolefunction search = new Sysrolefunction();
				search.setRoleid(roleid);
				sysrolefunctionService.deleteByroleid(search);
				
				List<String> listids = StringUtils.stringSplitToList(ids,",");
				if(listids!= null && listids.size() > 0){
					for(String item: listids){
						Sysrolefunction saveobj = new Sysrolefunction();
						saveobj.setIsNewRecord(true);
						saveobj.setCreateid(UserUtils.getSessionLocal().getUserid());
						saveobj.setCreatename(UserUtils.getSessionLocal().getUsername());
						saveobj.setRoleid(roleid);
						saveobj.setFunctionid(item);						
						sysrolefunctionService.save(saveobj);

					}				
				}
				json.put("code", 1);
				json.put("message", "保存成功");
			}
			
		} catch (Exception e) {
			json.put("code", 0);
			json.put("message", "保存失败");
		}
		renderString(response, json, jsonCallBack);
	}

	@RequestMapping(value = "sysFunctionSave.htm")
	@ResponseBody
	public void sysFunctionSave(HttpServletRequest request,HttpServletResponse response, String jsonCallBack){
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "保存失败");

		try {
			String objs = WebUtil.getPoPropertyByRequest(request, "objs");
			objs = new String(objs.getBytes("ISO-8859-1"), "UTF-8");
			JSONArray jsonArray = JSONArray.parseArray(objs);
			List<TreeDto> treeDtos = new ArrayList<>();
			String id = null;
			for(int k=0;k<jsonArray.size();k++){
				TreeDto treeDto = JSONObject.toJavaObject((JSON) jsonArray.get(k),TreeDto.class);
				if(!treeDto.getId().contains("a")){
					id=treeDto.getId();
				}
				treeDtos.add(treeDto);
			}
			Sysfunction sysfunction =  sysfunctionDao.get(id);
			String pid = sysfunction.getParentid();
			for(int j = 0;j<treeDtos.size();j++){
				if(!treeDtos.get(j).getId().equals(id)){
					Sysfunction sysfunction1 = new Sysfunction();
					sysfunction1.setId(treeDtos.get(j).getId().replace("a",""));
					sysfunction1.setAppid("1");
					sysfunction1.setParentid(treeDtos.get(j).getpId()==null?pid.replace("a",""):treeDtos.get(j).getpId().replace("a",""));
					sysfunction1.setFunctionname(treeDtos.get(j).getName());
					sysfunction1.setCheckshow("1");
					sysfunction1.setDeleteflag("0");
					sysfunction1.setVar01("desktop");
					if(sysfunctionDao.insert(sysfunction1)!=0){
						Sysrolefunction sysrolefunction = new Sysrolefunction();
						sysrolefunction.setRoleid("1");
						sysrolefunction.setAppid("1");
						sysrolefunction.setFunctionid(sysfunction1.getId());
						sysrolefunction.setDeleteflag("0");
						sysrolefunction.setCreateid(UserUtils.getSessionLocal().getUserid());
						sysrolefunction.setCreatename(UserUtils.getSessionLocal().getUsername());
						sysrolefunctionService.save(sysrolefunction);
					}

				}else{
					sysfunction.setFunctionname(treeDtos.get(j).getName());
					sysfunctionDao.update(sysfunction);
				}

			}
		} catch (Exception e) {
				json.put("code", 0);
				json.put("message", "保存失败");
		}

		renderString(response, json, jsonCallBack);


	}


}
