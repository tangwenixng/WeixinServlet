package com.twx.test;
import java.util.Date;

import com.thoughtworks.xstream.XStream;
import com.twx.po.TextMessage;

public class XStreamTest {
	public static void main(String[] args) {
		XStream xStream = new XStream();
		TextMessage tm = new TextMessage();
		tm.setContent("ฤ๚บรฃก");
		tm.setCreateTime(new Date().getTime());
		tm.setMsgType("text");
		tm.setFromUserName("tangwenxing");
		tm.setMsgId("fsfs24sfd");
		
		xStream.alias("xml", tm.getClass());
		String result = xStream.toXML(tm);
		System.out.println(result);
	}
}
