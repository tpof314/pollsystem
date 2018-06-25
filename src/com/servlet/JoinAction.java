package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class JoinAction
 */
public class JoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Sorry! you come to a wrong place.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pollid = Integer.parseInt( request.getParameter("pollid") );
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			String title = "Join Poll Failed";
			String message = "You are not logged in! Please login before joining any polls.";
			String button  = "Login";
			String url     = "login.jsp";
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
			dispatcher.forward( request, response );
			
			return ;
		}
		
		ServletContext application = getServletConfig().getServletContext();
		DBConnector db = (DBConnector)application.getAttribute("database");
		Poll poll = db.getPolls().getPollDetail(pollid);
		
		if (poll == null) {
			String title = "Join Poll Failed";
			String message = "The poll you are joining does not exist.";
			String button  = "Home";
			String url     = "index.jsp";
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
			dispatcher.forward( request, response );
			
			return ;
		}
		
		String time = request.getParameter("time");
		String username = user.getName();
		PollTime polltime = db.getPollTime(pollid, time);
		
		boolean result = db.addFollowerToPollTime(polltime, username);
		if (result == false) {
			String title = "Join Poll Failed";
			String message = "You have joined this poll already.";
			String button  = "Poll Summary";
			String url     = "poll_summary.jsp?pollid=" + pollid + "&time=" + polltime.getTime();
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
			dispatcher.forward( request, response );
			
			return ;
		}
		else {
			String title = "Join Poll Successful";
			String message = "You have successfully joined this poll.";
			String button  = "Poll Summary";
			String url     = "poll_summary.jsp?pollid=" + pollid + "&time=" + polltime.getTime();
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_success.jsp");
			dispatcher.forward( request, response );
			
			return ;
		}
		
	}

}
