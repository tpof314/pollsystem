package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataaccess.DBConnector;
import com.model.Poll;
import com.model.PollTime;
import com.model.User;

/**
 * Servlet implementation class CreatePollAction
 */
public class CreatePollAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePollAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void redirectSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String title = "Create Poll Successfully";
		String message = "Your poll has been created successfully!";
		String button  = "Homepage";
		String url     = "index.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_success.jsp");
		dispatcher.forward( request, response );
    }
    
    public void redirectLoginError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String title = "Create Poll Failed";
		String message = "You have to login to create new polls.";
		String button  = "Homepage";
		String url     = "index.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
		dispatcher.forward( request, response );
    }    
    
    public void redirectDataMissingError(HttpServletRequest request, HttpServletResponse response, String data) throws ServletException, IOException {
    	String title = "Create Poll Failed";
		String message = "Error: <b>" + data + "</b> field cannot be empty.";
		String button  = "Back";
		String url     = "create_poll.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
		dispatcher.forward( request, response );
    }    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			redirectLoginError(request, response);
			return ;
		}
		
		ServletContext application = getServletConfig().getServletContext();
		DBConnector db = (DBConnector)application.getAttribute("database");
		
		int userid = user.getId();
		int pollid = db.getNextPollId();
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String status = request.getParameter("status");
		String title  = request.getParameter("title");
		
		if (description == null || description.equals("")) {
			redirectDataMissingError(request, response, "description");
			return ;
		}
		if (location == null || location.equals("")) {
			redirectDataMissingError(request, response, "location");
			return ;
		}
		if (title == null || title.equals("")) {
			redirectDataMissingError(request, response, "title");
			return ;
		}
		
		Poll poll = new Poll();
		poll.setId(pollid);
		poll.setCreator(userid);
		poll.setDate(new Date());
		poll.setDescription(description);
		poll.setLocation(location);
		poll.setStatus(status);
		poll.setTitle(title);
		poll.setPolltime(new ArrayList<PollTime>());
		
		db.addPoll(poll);
		redirectSuccess(request, response);
	}

}
