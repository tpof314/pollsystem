package com.dataaccess;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.*;

import com.model.Poll;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "polls")
public class Polls implements Serializable {
	@XmlElementWrapper(name = "polls")
	@XmlElement(name = "poll")
	private ArrayList<Poll> pollList;
	
	public Polls() {
		super();
		this.pollList = new ArrayList<Poll>();
	}

	public Polls(ArrayList<Poll> pollList) {
		super();
		this.pollList = pollList;
	}

	public ArrayList<Poll> getPollList() {
		return pollList;
	}

	public void setPollList(ArrayList<Poll> pollList) {
		this.pollList = pollList;
	}

	public void addPoll(Poll p) {
		this.pollList.add(p);
	}
	
	public Poll getPollDetail(int id) {
		for (Poll poll : pollList) {
			if (poll.getId() == id) {
				return poll;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Polls [pollList=" + pollList + "]";
	}
	
	
}
