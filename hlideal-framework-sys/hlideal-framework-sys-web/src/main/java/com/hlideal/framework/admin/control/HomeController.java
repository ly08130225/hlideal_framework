package com.hlideal.framework.admin.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hlideal.common.base.TreeDto;
import com.hlideal.common.base.utils.StringUtils;
import com.hlideal.common.base.utils.WebUtil;
import com.hlideal.framework.admin.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import com.hlideal.framework.admin.AdminBaseController;
import com.hlideal.framework.core.dao.SysfunctionDao;
import com.hlideal.framework.core.entity.Sysfunction;

@Controller
@RequestMapping(value = "/admin/backstage")
public class HomeController extends AdminBaseController {
	String INDEX_PATH = "/admin/";
	
	@Autowired
	private SysfunctionDao sysfunctionDao;

	@RequestMapping("home.htm")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {		
		return INDEX_PATH + "home.vm";
	}
	
	@RequestMapping(value = "getUserFunctions.htm")
	@ResponseBody
	public void getUserFunctions(Model model, HttpSession session, HttpServletRequest req,HttpServletResponse res) {
		JSONObject json = new JSONObject();
		
		Sysfunction search = new Sysfunction();
		search.setUserId(UserUtils.getUserid());
		search.setAppid("1");
		search.setVar01("desktop");
		if(StringUtils.isNotEmpty(req.getParameter("appId"))){
			search.setId(req.getParameter("appId"));
		}
		List<Sysfunction> menuList = new ArrayList<Sysfunction>();
		menuList = sysfunctionDao.findByUserId(search);

		List<TreeDto> treeDtos = new ArrayList<>();
		if(StringUtils.isNotEmpty(req.getParameter("appId"))){
			for(int i=0;i<menuList.size();i++){
				Sysfunction sysfunction = menuList.get(0);
				TreeDto treeDto = new TreeDto();
				treeDto.setId(sysfunction.getId());
				treeDto.setpId(sysfunction.getParentid());
				treeDto.setName(sysfunction.getFunctionname());
				treeDtos.add(treeDto);
			}
		}
		json.put("result", treeDtos.size()>0?treeDtos:menuList);
		renderString(res,json);		
	}

	@RequestMapping(value = "/editMenu.htm")
	public String editMenu(Model model,String appId){
		model.addAttribute("num",sysfunctionDao.findMaxId().getId());
		model.addAttribute("appId", appId);
		return INDEX_PATH+"menu.vm";
	}
}
