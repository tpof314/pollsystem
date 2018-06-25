package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataaccess.DBConnector;
import com.model.Poll;
import com.model.User;

/**
 * Servlet implementation class DeletePollAction
 */
public class DeletePollAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePollAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pollid = Integer.parseInt( request.getParameter("pollid") );
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			String title = "Delete Failed";
			String message = "You are not the right person to delete this poll! Please login as the poll creater.";
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
		
		if (poll.getCreator() != user.getId()) {
			String title = "Delete Failed";
			String message = "You are not the right person to delete this poll! Please login as the poll creater.";
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
		
		db.removePollById(pollid);
		String title = "Delete Poll Successed";
		String message = "The poll has been deleted successfully. Please go to home page.";
		String button  = "Homepage";
		String url     = "index.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_success.jsp");
		dispatcher.forward( request, response );
	}

}
