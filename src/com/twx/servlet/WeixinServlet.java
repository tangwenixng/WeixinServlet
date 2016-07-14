package com.twx.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.twx.util.CheckUtil;
import com.twx.util.MessageUtil;

public class WeixinServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1111111111!!!");
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> message = MessageUtil.xmlToMap(req);
			String fromUser = message.get("FromUserName");
			String toUser = message.get("ToUserName");
			String content = message.get("Content");
			String msgType = message.get("MsgType");
			String msgId = message.get("MsgId");
			
			
			String respone = null;
			if(MessageUtil.MSGTYPE_TEXT.equals(msgType)){
				if("1".equals(content) || "2".equals(content)){
					respone = MessageUtil.initText(toUser, fromUser, MessageUtil.keyWord(content));
				}else if("3".equals(content)){
					respone = MessageUtil.initNewsMessage(toUser, fromUser);
				}else if("4".equals(content)){
					respone = MessageUtil.initImageMessage(toUser, fromUser);
				}else if("5".equals(content)){
					respone = MessageUtil.initMusicMessage(toUser, fromUser);
				}else{
					respone = "输入的指令无效哦(′⌒`)";
				}
				
			}else if(MessageUtil.MSGTYPE_NEWS.equals(msgType)){//图文消失
				respone = MessageUtil.initNewsMessage(toUser, fromUser);
			}
			else if(MessageUtil.MSGTYPE_EVENT.equals(msgType)){//如果是事件
				String event = message.get("Event");//事件类型
				//订阅
				if(MessageUtil.EVENT_SUBSCRIBE.equals(event)){
					respone = MessageUtil.initText(toUser, fromUser, MessageUtil.mainMenu());
				}else if(MessageUtil.EVENT_CLICK.equals(event)){
					respone = MessageUtil.initText(toUser, fromUser, MessageUtil.mainMenu());
				}else if(MessageUtil.EVENT_VIEW.equals(event)){
					String url = message.get("EventKey");
					respone = MessageUtil.initText(toUser, fromUser, url);
				}else if(MessageUtil.EVENT_SCANCODE_PUSH.equals(event)){
					String key = message.get("EventKey");
					respone = MessageUtil.initText(toUser, fromUser, key);
				}else if(MessageUtil.EVENT_LOCATION.equals(event)){
					String label = message.get("SendLocationInfo");
					System.out.println("SendLocationInfo:"+label);
					respone = MessageUtil.initText(toUser, fromUser, label);
				}
			}
//			System.out.println(respone);
			out.println(respone);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
