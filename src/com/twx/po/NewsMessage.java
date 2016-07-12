package com.twx.po;

import java.util.List;
/**
 * Í¼ÎÄÏûÏ¢
 * @author twx
 *
 */
public class NewsMessage extends BaseMessage{
	private int ArticleCount;
	private List<News> Articles;
	
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}
