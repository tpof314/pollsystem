package com.soap;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.annotation.Resource;
import javax.jws.WebMethod;

import com.dataaccess.DBConnector;
import com.dataaccess.Polls;
import com.rest.PollRestService;

@WebService
public class PollSoapService {
	
	@Resource
	private WebServiceContext context;
	private ServletContext application;
	private DBConnector database;
	
	private void init() {
		application = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);;
		String userfile = application.getRealPath("WEB-INF/User.xml");
		String pollfile = application.getRealPath("WEB-INF/Poll.xml");
		if (this.database == null) {
			database = new DBConnector(userfile, pollfile);
		}
	}
	
	@WebMethod
	public int createPoll(String username, String password, 
			String title, String location, String status, String description) {
		init();
		return this.database.createPoll(username, password, title, location, status, description);
	}
	
	@WebMethod
	public boolean closePoll(String username, String password, int pollid) {
		init();
		return this.database.closePoll(username, password, pollid);
	}
	
	@WebMethod
	public Polls searchPoll(int creator, String status, int minResponses ) {
		init();
		return this.database.searchPolls(creator, status, minResponses);
	}
	
}
