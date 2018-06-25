package com.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Poll implements Serializable {
	@XmlAttribute(name = "id")
	private int id;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "creator")
	private int creator;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "date")
	private Date date;
	@XmlElement(name = "location")
	private String location;
	@XmlElement(name = "status")
	private String status;
	@XmlElement(name = "timetable")
	private ArrayList<PollTime> polltime;
	
	
	public Poll() {
		super();
	}
	
	public Poll(int id, String title, int creator, String description, Date date, String location, String status,
			ArrayList<PollTime> polltime) {
		super();
		this.id = id;
		this.title = title;
		this.creator = creator;
		this.description = description;
		this.date = date;
		this.location = location;
		this.status = status;
		this.polltime = polltime;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
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
	public ArrayList<PollTime> getPolltime() {
		return polltime;
	}
	public void setPolltime(ArrayList<PollTime> polltime) {
		this.polltime = polltime;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", title=" + title + ", creator=" + creator + ", description=" + description
				+ ", date=" + date + ", location=" + location + ", status=" + status + ", polltime=" + polltime + "]";
	}
	
}
