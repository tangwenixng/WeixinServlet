package com.twx.test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.twx.po.AccessToken;
import com.twx.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("token:"+token.getToken());
			System.out.println("expiresIn:"+token.getExpiresIn());
			
			String filePath = "d:/1.jpg";
			String media_id = WeixinUtil.upload(filePath, token.getToken(), "image");
			System.out.println("media_id:"+media_id);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
