package com.twx.test;

import java.io.IOException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.twx.po.AccessToken;
import com.twx.util.WeixinUtil;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("token:"+token.getToken());
			System.out.println("expiresIn:"+token.getExpiresIn());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
