package com.pageobject;

import java.util.ArrayList;
import java.util.Date;

public class POPollSummary {
	private int    id;
	private String title;
	private String creator;
	private Date   postDate;
	private String time;
	private String location;
	private String status;
	private String description;
	private ArrayList<String> followers;
	
	public POPollSummary() {
		super();
	}
	public POPollSummary(int id, String title, String creator, Date postDate, String time, String location,
			String status, String description, ArrayList<String> followers) {
		super();
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.postDate = postDate;
		this.time = time;
		this.location = location;
		this.status = status;
		this.description = description;
		this.followers = followers;
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
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<String> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}
	@Override
	public String toString() {
		return "POPollSummary [id=" + id + ", title=" + title + ", creator=" + creator + ", postDate=" + postDate
				+ ", time=" + time + ", location=" + location + ", status=" + status + ", description=" + description
				+ ", followers=" + followers + "]";
	}
}
