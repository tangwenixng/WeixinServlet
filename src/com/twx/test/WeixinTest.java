package com.twx.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.twx.po.AccessToken;
import com.twx.util.WeixinUtil;

import net.sf.json.JSONObject;

public class WeixinTest {
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("token:"+token.getToken());
			System.out.println("expiresIn:"+token.getExpiresIn());
			
		/*	String filePath = "d:/1.jpg";
			String media_id = WeixinUtil.upload(filePath, token.getToken(), "thumb");
			System.out.println("media_id:"+media_id);*/
			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if(result==0){
				System.out.println("创建成功");
			}else{
				System.out.println("创建失败");
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
