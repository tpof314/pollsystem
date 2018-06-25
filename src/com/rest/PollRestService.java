package com.rest;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.dataaccess.DBConnector;
import com.dataaccess.Polls;
import com.model.Poll;
import com.model.PollTime;

@Path("/pollService")
public class PollRestService {
	@Context
	private ServletContext application;
	private DBConnector database;
	
	public PollRestService() {
		
	}
	
	private void init() {
		String userfile = application.getRealPath("WEB-INF/User.xml");
		String pollfile = application.getRealPath("WEB-INF/Poll.xml");
		if (this.database == null) {
			database = new DBConnector(userfile, pollfile);
		}
	}
	
	@Path("test")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hello World";
	}
	
	@Path("allPolls")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Polls getPolls() {
		init();
		return database.getPolls();
	}
	
	@Path("searchPolls")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Polls searchPolls(@QueryParam("creator") int creator, @QueryParam("status") String status,
													@QueryParam("minResponses") int minResponses) {
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
		init();
		Polls resultPolls = new Polls();
		for (Poll poll : database.getPolls().getPollList()) {
			//System.out.println("Hello~~");
			if (poll.getCreator() == creator) {
				resultPolls.addPoll(poll);
			}
		}
		return resultPolls;
	}
	
	private Polls findPollsByStatus(String status) {
		init();
		Polls resultPolls = new Polls();
		for (Poll poll : database.getPolls().getPollList()) {
			if (poll.getStatus().toUpperCase().equals( status.toUpperCase()) ) {
				resultPolls.addPoll(poll);
			}
		}
		return resultPolls;
	}
	
	private Polls findPollsByMinResponses(int resp) {
		init();
		Polls resultPolls = new Polls();
		for (Poll poll : database.getPolls().getPollList()) {
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
