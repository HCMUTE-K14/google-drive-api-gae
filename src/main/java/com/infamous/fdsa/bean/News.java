package com.infamous.fdsa.bean;



public class News {

	private String id;
	
	private String title;
	
	private String content;
	
	private String attactlink;

	public News() {
	}

	public News(String id, String title, String content, String attactlink) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.attactlink = attactlink;
	}

	public News(String title, String content, String attactlink) {
		this.title = title;
		this.content = content;
		this.attactlink = attactlink;
	}

	public News(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttactLink() {
		return attactlink;
	}

	public void setAttactLink(String attactLink) {
		this.attactlink = attactLink;
	}

	@Override
	public String toString() {
		return "NewsModel [id=" + id + ", title=" + title + ", content=" + content + ", attactLink=" + attactlink + "]";
	}
}
