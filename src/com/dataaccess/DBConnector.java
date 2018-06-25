package com.dataaccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.pageobject.POPollDetail;
import com.pageobject.POPollShort;
import com.pageobject.POPollSummary;
import com.model.*;

public class DBConnector {
	private String userFile;
	private String pollFile;
	
	private Users users;
	private Polls polls;
	
	public DBConnector() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param userFile
	 * @param pollFile
	 */
	public DBConnector(String userFile, String pollFile) {
		try {
			this.userFile = userFile;
			this.pollFile = pollFile;
			readUserFile(userFile);
			readPollFile(pollFile);
		}
		catch (Exception exp) {
			System.err.println(exp.getMessage());
		}
	}
	
	/**
	 * Helper method for reading user XML file.
	 * @param userFile
	 * @throws JAXBException
	 * @throws IOException
	 */
	private void readUserFile(String userFile) throws JAXBException, IOException {
		JAXBContext jc = JAXBContext.newInstance(Users.class);
		Unmarshaller u = jc.createUnmarshaller();
		FileInputStream fin = new FileInputStream(userFile);
		this.users = (Users) u.unmarshal(fin);
		fin.close();
	}
	
	/**
	 * Helper method for reading poll XML file.
	 * @param pollFile
	 * @throws JAXBException
	 * @throws IOException
	 */
	private void readPollFile(String pollFile) throws JAXBException, IOException {
		JAXBContext jc = JAXBContext.newInstance(Polls.class);
		Unmarshaller u = jc.createUnmarshaller();
		
		FileInputStream fin = new FileInputStream(pollFile);
		this.polls = (Polls)u.unmarshal(fin); 
		fin.close();
	}

	private void updateUserFile() throws JAXBException, IOException {
		File fout = new File(this.userFile);
		JAXBContext jc = JAXBContext.newInstance(Users.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(users, fout);
	}
	
	private void updatePollFile() throws JAXBException, IOException {
		File fout = new File(this.pollFile);
		JAXBContext jc = JAXBContext.newInstance(Polls.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(polls, fout);
	}
	
	public String getUserFile() {
		return userFile;
	}

	public void setUserFile(String userFile) {
		try {
			this.userFile = userFile;
			readUserFile(userFile);
		}
		catch (Exception exp) {
			System.err.println(exp.getMessage());
		}
	}

	public String getPollFile() {
		return pollFile;
	}

	public void setPollFile(String pollFile) {
		try {
			this.pollFile = pollFile;
			readPollFile(pollFile);
		}
		catch (Exception exp) {
			System.err.println(exp.getMessage());
		}
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Polls getPolls() {
		return polls;
	}

	public void setPolls(Polls polls) {
		this.polls = polls;
	}
	
	private void sortPollsByTime() {
		Collections.sort(this.polls.getPollList(), new Comparator<Poll>() {
			@Override
			public int compare(Poll poll1, Poll poll2) {
				if (poll1.getDate().before(poll2.getDate())) {
					return -1;
				}
				else if (poll1.getDate().after(poll2.getDate())) {
					return 1;
				}
				else {
					return 0;
				}
			}
	    });
	}
	
	/**
	 * Find username by a given id.
	 * @param id
	 * @return
	 */
	private String getUsernameById(int id) {
		for (User user : this.getUsers().getList()) {
			if (user.getId() == id) {
				return user.getName();
			}
		}
		return "<Unknown>";
	}
	
	/* ============ Functions for application ============ */
	
	public User login(String username, String password) {
		for (User user : users.getList()) {
			if (user.getName().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public int getNextPollId() {
		int id = 0;
		for (Poll poll : this.polls.getPollList()) {
			if (poll.getId() > id) {
				id = poll.getId();
			}
		}
		return id + 1;
	}
	
	public void addPoll(Poll p) {		
		try {
			this.polls.addPoll(p);
			this.updatePollFile();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean removePollById(int id) {
		try {
			Poll poll = polls.getPollDetail(id);
			if (poll == null) {
				return false;
			}
			else {
				polls.getPollList().remove(poll);
				this.updatePollFile();
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public ArrayList<POPollShort> getRecentPolls() {
		ArrayList<POPollShort> results = new ArrayList<POPollShort>();
		for (Poll poll : this.polls.getPollList()) {
			if (poll.getStatus().equals("Open")) {
				POPollShort poPoll = new POPollShort();
				poPoll.setId(poll.getId());
				poPoll.setCreator(getUsernameById(poll.getCreator()));
				poPoll.setDate(poll.getDate());
				poPoll.setDescription(poll.getDescription());
				poPoll.setTitle(poll.getTitle());
				results.add(poPoll);
			}
		}
		return results;
	}
	
	public ArrayList<POPollShort> getUsersPolls(int userid) {
		ArrayList<POPollShort> results = new ArrayList<POPollShort>();
		for (Poll poll : this.polls.getPollList()) {
			if (poll.getCreator() == userid) {
				POPollShort poPoll = new POPollShort();
				poPoll.setId(poll.getId());
				poPoll.setCreator(getUsernameById(poll.getCreator()));
				poPoll.setDate(poll.getDate());
				poPoll.setDescription(poll.getDescription());
				poPoll.setTitle(poll.getTitle());
				results.add(poPoll);
			}
		}
		return results;
	}
	
	
	public POPollDetail getPollDetail(int id) {
		Poll poll = polls.getPollDetail(id);
		if (poll == null) {
			return null;
		}
		else {
			POPollDetail poPoll = new POPollDetail();
			poPoll.setCreator(getUsernameById(poll.getCreator()));
			poPoll.setDate(poll.getDate());
			poPoll.setDescription(poll.getDescription());
			poPoll.setId(poll.getId());
			poPoll.setLocation(poll.getLocation());
			poPoll.setStatus(poll.getStatus());
			poPoll.setTitle(poll.getTitle());
			
			ArrayList<String> timeList = new ArrayList<String>();
			if (poll.getPolltime() != null) {
				for (PollTime time : poll.getPolltime()) {
					timeList.add(time.getTime());
				}
			}
			poPoll.setTime(timeList);
			return poPoll;
		}
	}
	
	public POPollSummary getPollSummary(int pollid, String time) {
		Poll poll = polls.getPollDetail(pollid);
		if (poll == null) {
			return null;
		}
		for (PollTime polltime : poll.getPolltime()) {
			if (polltime.getTime().equals(time)) {
				POPollSummary summary = new POPollSummary();
				summary.setCreator(getUsernameById(poll.getCreator()));
				summary.setDescription(poll.getDescription());
				summary.setId(poll.getId());
				summary.setLocation(poll.getLocation());
				summary.setPostDate(poll.getDate());
				summary.setStatus(poll.getStatus());
				summary.setTime(polltime.getTime());
				summary.setTitle(poll.getTitle());
				
				if (polltime.getFollowers() != null) {
					summary.setFollowers(polltime.getFollowers());
				}
				else {
					ArrayList<String> followers = new ArrayList<String>();
					summary.setFollowers(followers);
				}
				return summary;
			}
		}
		return null;
	}
	
	
	public PollTime getPollTime(int pollid, String time) {
		Poll poll = polls.getPollDetail(pollid);
		if (poll == null) {
			return null;
		}
		for (PollTime polltime : poll.getPolltime()) {
			if (polltime.getTime().equals(time)) {
				return polltime;
			}
		}
		return null;
	}
	
	public boolean timeExist(String time, Poll poll) {
		for (PollTime polltime : poll.getPolltime()) {
			if (polltime.getTime().equals(time)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addTimeToPoll(String time, Poll poll) {
		try {
			if (timeExist(time, poll)) {
				return false;
			}
			else {
				ArrayList<String> followers = new ArrayList<String>();
				PollTime polltime = new PollTime();
				polltime.setFollowers(followers);
				polltime.setTime(time);
				poll.getPolltime().add(polltime);
				this.updatePollFile();
				return true;
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	public boolean addFollowerToPollTime(PollTime polltime, String name) {
		try {
			if (polltime.getFollowers().contains(name)) {
				return false;
			}
			else {
				polltime.getFollowers().add(name);
				this.updatePollFile();
				return true;
			}
		}
		catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	public boolean removeTimeFromPoll(Poll poll, String time) {
		try {
			if (!timeExist(time, poll)) {
				return false;
			}
			else {
				for (PollTime polltime: poll.getPolltime()) {
					if (polltime.getTime().equals(time)) {
						poll.getPolltime().remove(polltime);
						this.updatePollFile();
						return true;
					}
				}
				return true;
			}
		}
		catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	public boolean setPollStatus(Poll poll, String status) {
		try {
			poll.setStatus(status);
			this.updatePollFile();
			return true;
		}
		catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}
	
	/* ================ Soap Service ================ */
	public int createPoll(String username, String password, 
			String title, String location, String status, String description) {
		
		User user = this.login(username, password);
		if (user == null) {
			return -1;
		}
		
		int userid = user.getId();
		int pollid = this.getNextPollId();
		
		Poll poll = new Poll();
		poll.setId(pollid);
		poll.setCreator(userid);
		poll.setDate(new Date());
		poll.setDescription(description);
		poll.setLocation(location);
		poll.setStatus(status);
		poll.setTitle(title);
		poll.setPolltime(new ArrayList<PollTime>());
		
		if (!status.equals("Open") && !status.equals("Close")) {
			return -1;
		}
		
		this.addPoll(poll);
		return pollid;
	}
	
	public boolean closePoll(String username, String password, int pollid) {
		User user = this.login(username, password);
		if (user == null) {
			return false;
		}
		
		int userid = user.getId();
		Poll poll = this.getPolls().getPollDetail(pollid);
		if (poll == null) {
			return false;
		}
		
		if (poll.getCreator() != userid) {
			return false;
		}
		return this.setPollStatus(poll, "Close");
	}

	
	public Polls searchPolls(int creator, String status, int minResponses) {
		// No creator
		if (creator == 0) {
			if (status == null) {
				if (minResponses == 0) {
					return this.findPollsByStatus("open");
				}
				else {
					return this.findPollsByMinResponses(minResponses);
				}
			}
			else {
				Polls results = this.findPollsByStatus(status);
				if (minResponses == 0) {
					return results;
				}
				else {
					Polls minResps = this.findPollsByMinResponses(minResponses);
					Polls trimedResults = new Polls();
					for (Poll poll : results.getPollList()) {
						if (minResps.getPollList().contains(poll)) {
							trimedResults.addPoll(poll);
						}
					}
					return trimedResults;
				}
			}
		}
		
		// With creator
		else {
			Polls creatorResult = this.findPollsByCreator(creator);
			if (status == null) {
				if (minResponses == 0) {
					return creatorResult;
				}
				else {
					Polls minResps = this.findPollsByMinResponses(minResponses);
					Polls trimedResults = new Polls();
					for (Poll poll : creatorResult.getPollList()) {
						if (minResps.getPollList().contains(poll)) {
							trimedResults.addPoll(poll);
						}
					}
					return trimedResults;
				}
			}
			else {
				Polls statusResult = this.findPollsByStatus(status);
				if (minResponses == 0) {
					Polls trimedResults = new Polls();
					for (Poll poll : creatorResult.getPollList()) {
						if (statusResult.getPollList().contains(poll)) {
							trimedResults.addPoll(poll);
						}
					}
					return trimedResults;
				}
				else {
					Polls minResps = this.findPollsByMinResponses(minResponses);
					Polls trimedResults = new Polls();
					for (Poll poll : creatorResult.getPollList()) {
						if (statusResult.getPollList().contains(poll) && minResps.getPollList().contains(poll)) {
							trimedResults.addPoll(poll);
						}
					}
					return trimedResults;
				}
			}
		}
	}
	
	/* =============== Helper Functions =============== */
	private Polls findPollsByCreator(int creator) {
		Polls resultPolls = new Polls();
		for (Poll poll : this.getPolls().getPollList()) {
			//System.out.println("Hello~~");
			if (poll.getCreator() == creator) {
				resultPolls.addPoll(poll);
			}
		}
		return resultPolls;
	}
	
	private Polls findPollsByStatus(String status) {
		Polls resultPolls = new Polls();
		for (Poll poll : this.getPolls().getPollList()) {
			if (poll.getStatus().toUpperCase().equals( status.toUpperCase()) ) {
				resultPolls.addPoll(poll);
			}
		}
		return resultPolls;
	}
	
	private Polls findPollsByMinResponses(int resp) {
		Polls resultPolls = new Polls();
		for (Poll poll : this.getPolls().getPollList()) {
			int count = 0;
			if (poll.getPolltime() != null) {
				for (PollTime time : poll.getPolltime()) {
					if (time.getFollowers() != null) {
						count = count + time.getFollowers().size();
					}
				}
			}
			if (count >= resp) {
				resultPolls.addPoll(poll);
			}
		}
		return resultPolls;
	}
	
}
