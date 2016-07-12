package com.twx.po;

public class TextMessage extends BaseMessage{
	
	private String Content;
	private String MsgId;
	
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
