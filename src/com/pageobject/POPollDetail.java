package com.pageobject;

import java.util.ArrayList;
import java.util.Date;

public class POPollDetail {
	private int    id;
	private String title;
	private String creator;
	private Date   date;
	private String location;
	private String status;
	private String description;
	
	private ArrayList<String> time;

	public POPollDetail() {
		super();
	}

	public POPollDetail(int id, String title, String creator, Date date, String location, String status,
			String description, ArrayList<String> time) {
		super();
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.date = date;
		this.location = location;
		this.status = status;
		this.description = description;
		this.time = time;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public ArrayList<String> getTime() {
		return time;
	}

	public void setTime(ArrayList<String> time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "POPollDetail [id=" + id + ", title=" + title + ", creator=" + creator + ", date=" + date + ", location="
				+ location + ", status=" + status + ", description=" + description + ", time=" + time + "]";
	}
	
	
}
