package com.pageobject;
import java.util.Date;


public class POPollShort {
	private int id;
	private String title;
	private String creator;
	private String description;
	private Date date;
	
	public POPollShort() {
		super();
	}
	
	public POPollShort(int id, String title, String creator, String description, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.date = date;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "poPollShort [id=" + id + ", title=" + title + ", creator=" + creator + ", description=" + description
				+ ", date=" + date + "]";
	}
}
