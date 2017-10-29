package og.liufeng.course.service;

import og.liufeng.course.message.resp.Article;
import og.liufeng.course.message.resp.NewsMessage;
import og.liufeng.course.message.resp.TextMessage;
import og.liufeng.course.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CoreService {
	
	public static String processRequest(HttpServletRequest request) {
		
		String respXml = null;
		
		String respContent = "未知的消息类型";
		
		try {
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息!";
				textMessage.setContent(respContent);
				respXml = MessageUtil.messageToXml(textMessage);
			} else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注!";
					textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后，用户不会再收到公众号发送的消息，因此不需要回复
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫码带参数二维码事件
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					if (eventKey.equals("oschina")) {
						Article article = new Article();
						article.setTitle("开源中国");
						article.setDescription("开源中国社区成立于2008年8月，是目前中国最大的开源技术社区。");
						article.setPicUrl("");
						article.setUrl("http://m.oschina.net");
						List<Article> articleList = new ArrayList<>();
						articleList.add(article);
						// 创建图片图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("iteye")) {
						respContent = "ITeye创办于2003年9月的JavaEye，从最初的以讨论Java技术为主的技术论坛，已经逐渐发展为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com";
						textMessage.setContent(respContent);
						respXml = MessageUtil.messageToXml(textMessage);
					}
				} 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

}
