package com.model;


import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class PollTime implements Serializable {

	@XmlElement(name = "time")
	private String time;
	
	@XmlElementWrapper(name = "names")
	@XmlElement(name = "name")
	private ArrayList<String> followers;
	
	public PollTime() {
		super();
	}

	public PollTime(String time, ArrayList<String> followers) {
		super();
		this.time = time;
		this.followers = followers;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<String> getFollowers() {
		return followers;
	}

	public void setFollowers(ArrayList<String> followers) {
		this.followers = followers;
	}

	@Override
	public String toString() {
		return "PollTime [time=" + time + ", followers=" + followers + "]";
	}
	
}
