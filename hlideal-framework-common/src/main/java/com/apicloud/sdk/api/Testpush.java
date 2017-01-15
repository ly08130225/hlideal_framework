package com.apicloud.sdk.api;

import com.alibaba.fastjson.JSONObject;

public class Testpush {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String appKey = "6619B147-FA8D-8171-7F20-206DE6505DE8";
		String appId = "A6904778204209";
		Push push = new Push(appId, appKey, "");
		JSONObject obj = push.pushMessage("测试推送", "测试推送", 2, 0, "", "57eaa26fb3704aaaa1524aa9fb25cce3,");
		System.out.println("obj");
	}

}
