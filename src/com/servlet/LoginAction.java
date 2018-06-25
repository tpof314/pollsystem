package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dataaccess.DBConnector;
import com.model.User;

/**
 * Servlet implementation class LoginAction
 */
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ServletContext application = getServletConfig().getServletContext();
		DBConnector db = (DBConnector)application.getAttribute("database");
		User user = db.login(username, password);
		
		if (user != null) {
			request.getSession().setAttribute("user", user);
			
			String title = "Login Successed";
			String message = "You have successfully logged in. Please go to home page.";
			String button  = "Homepage";
			String url     = "index.jsp";
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_success.jsp");
			dispatcher.forward( request, response );
		}
		else {
			String title = "Login Failed";
			String message = "Invalid username or password!";
			String button  = "Try again";
			String url     = "login.jsp";
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
			dispatcher.forward( request, response );
		}
		
	}

}
