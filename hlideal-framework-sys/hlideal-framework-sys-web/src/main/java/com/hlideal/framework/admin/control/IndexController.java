package com.hlideal.framework.admin.control;


import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;


import com.hlideal.framework.admin.AdminBaseController;
import com.hlideal.common.base.utils.StringUtils;
import com.hlideal.common.mybatis.Global;
import com.hlideal.common.session.SessionLocal;
import com.hlideal.common.session.SessionLocalManager;
import com.hlideal.framework.core.entity.Sysstaff;


@Controller
@RequestMapping(value = "/admin")
public class IndexController extends AdminBaseController {
	String INDEX_PATH = "/admin/";

	@RequestMapping("introduce.htm")
	public String introduce(HttpServletRequest request, HttpServletResponse response, Model model) {
		return INDEX_PATH + "introduce.vm";
	}

	
	@RequestMapping("login.htm")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Object mscode = request.getParameter("mscode");
		if(mscode!= null && !StringUtils.isEmpty(mscode.toString()))
		{
			model.addAttribute("message", mscode.toString());
		}
		return INDEX_PATH + "login.vm";
	}

	@RequestMapping(value = "logincheck.htm")
	@ResponseBody
	public void logincheck(@ModelAttribute("userName") String userName, @ModelAttribute("password") String password, Model model, HttpSession session, HttpServletRequest req,HttpServletResponse res) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("message", "登录失败！");

		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
			Sysstaff user = sysUserService.getUserByLoginName(userName, password);
			if (user != null) {				
				if (Global.NO.equals(user.getLoginflag())){
					json.put("code", 0);
					json.put("message", "该已帐号禁止登录");
				}
				else
				{
					SessionLocal local = new SessionLocal();
					local.setLastDate(new Date());
					local.setTruename(user.getName());
					local.setUserid(user.getId());
					local.setUsername(user.getLoginname());
					local.setUserphone(user.getMobilephone());
					
					SessionLocalManager.setSessionLocal(local);
					json.put("code", 1);
					json.put("message", "登录成功");
				}
			}
		}		
		renderString(res,json);		
	}
	
	
	// 报错
	@RequestMapping("error.htm")
	public String error(HttpServletRequest request, HttpServletResponse response, Model model) {
		return INDEX_PATH + "error.vm";
	}
	// 用户登出
	@RequestMapping("loginout.htm")
	public String loginout(HttpServletRequest request, HttpServletResponse response, Model model) {
		try {
			SessionLocalManager.destroy();
		} catch (Exception e) {
			;
		}

		return "redirect:" + "/admin/login.htm";
	}
	
}
