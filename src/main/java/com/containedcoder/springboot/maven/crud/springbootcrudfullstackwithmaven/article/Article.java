package com.containedcoder.springboot.maven.crud.springbootcrudfullstackwithmaven.article;

import java.util.ArrayList;
import java.util.Date;

public class Article {
	private int id;
	private String title;
	private String text;
	private String author;
	private Date date;
	private ArrayList<String> tags;
	
	public Article(int id, String title, String text, String author, Date date, ArrayList<String> tags) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.author = author;
		this.date = date;
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList getTags() {
		return tags;
	}

	public void setTags(ArrayList tags) {
		this.tags = tags;
	}
}
