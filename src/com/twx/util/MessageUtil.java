package com.twx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.twx.po.News;
import com.twx.po.NewsMessage;
import com.twx.po.TextMessage;

public class MessageUtil {
	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_NEWS = "news";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_EVENT = "event";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_SCAN = "SCAN";
	public static final String EVENT_LOCATION = "LOCATION";
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_VIEW = "VIEW";
	
	/**
	 * xml转map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request ) throws IOException, DocumentException{
		SAXReader reader = new SAXReader();
		Map<String, String> map = new HashMap<String,String>();
		
		InputStream input = request.getInputStream();
		Document doc = reader.read(input);
		
		List<Element> list = new ArrayList<>();
		
		Element root = doc.getRootElement();
		
		list = root.elements();
		
		for(Element e: list){
			map.put(e.getName(), e.getText());
		}
		input.close();
		return map;
	}
	
	/**
	 * 组装文本消息
	 * @param toUser
	 * @param fromUser
	 * @param content
	 * @return
	 */
	public static String initText(String toUser,String fromUser,String content){
		TextMessage tm = new TextMessage();
		tm.setFromUserName(toUser);
		tm.setToUserName(fromUser);
		tm.setMsgType(MSGTYPE_TEXT);
		tm.setCreateTime(new Date().getTime());
		tm.setContent(content);
		return MessageUtil.textMessageToXml(tm);
	}
	
	public static String textMessageToXml(TextMessage text){
		XStream stream = new XStream();
		stream.alias("xml", text.getClass());
		return stream.toXML(text);
	}
	
	public static String mainMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎关注我的个人微信公众号，在这里你可以给我留言或者给我提出建议\n")
		.append("1、获取我的个人信息\n\n")
		.append("2、获取我的shadowsocks账号密码");
		return sb.toString();
	}
	
	public static String keyWord(String word){
		
		StringBuffer sb = new StringBuffer();
		if("1".equals(word)){
			sb.append("QQ: 843571091\n")
			.append("微信: twx843571091\n")
			.append("手机号码: 18051292045\n")
			.append("email: twx843571091@gmail.com");
		}else if("2".equals(word)){
			sb.append("服务器:108.61.183.107\n")
			.append("密码：nexusqaz\n")
			.append("端口号：4000\n")
			.append("加密方式：aes-256-cfb");
		}
		return sb.toString();
	}
	
	/**
	 * 图文消息转换
	 * @param nm
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage nm){
		XStream stream = new XStream();
		stream.alias("xml", nm.getClass());
		stream.alias("item", new News().getClass());
		return stream.toXML(nm);
	}
	
	/**
	 * 组装图文消息
	 * @param toUser
	 * @param fromUser
	 * @return
	 */
	public static String initNewsMessage(String toUser,String fromUser){
		NewsMessage newsMessage = new NewsMessage();//图文消息
		
		List<News> newsList = new ArrayList<>();//图文消息内容
		News news = new News();
		news.setTitle("甲铁城的卡巴内瑞");
		news.setDescription("本作是一部以蒸汽机发达的岛国“日之本”为舞台的蒸汽朋克生存动作剧，作品主要描写了拥有钢铁心脏的僵尸=“卡巴内”与乘上装甲蒸汽机车“甲铁城”的少年少女的存亡之战。该作标题中的“卡巴内瑞”指的是介于“卡巴内”与人类之间的存在。");
		news.setPicUrl("http://teaegg.ngrok.cc/Weixin/image/jiatie.png");
		news.setUrl("http://bangumi.bilibili.com/anime/3494/");
		
		newsList.add(news);
		
		News news1 = new News();
		news1.setTitle("异国迷宫的十字路口");
		news1.setDescription("十九世纪后半，欧洲正值流行东洋文化的时代。 单身渡海来到法国的日本少女·汤音，在巴黎工商业区中的工艺品店“国王的招牌店”开始了自己的工作。 这是汤音通过和店主克洛德的交流，虽然对异国文化充满困惑、但也努力适应生活的旅居物语。");
		news1.setPicUrl("http://teaegg.ngrok.cc/Weixin/image/tang1.jpg");
		news1.setUrl("http://bangumi.bilibili.com/anime/3433/");
		
		newsList.add(news1);
		
		newsMessage.setToUserName(fromUser);
		newsMessage.setFromUserName(toUser);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MSGTYPE_NEWS);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setArticles(newsList);
		
		return newsMessageToXml(newsMessage);
		
	}
}
