<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="com.pageobject.*" %>
<%@page import="com.model.User" %>
<% String userFile = application.getRealPath("WEB-INF/User.xml"); %>
<% String pollFile = application.getRealPath("WEB-INF/Poll.xml"); %>
<jsp:useBean id="database" class="com.dataaccess.DBConnector" scope="application">
    <jsp:setProperty name="database" property="userFile" value="<%=userFile%>"/>
    <jsp:setProperty name="database" property="pollFile" value="<%=pollFile%>"/>
</jsp:useBean>
<%
	User user = (User)request.getSession().getAttribute("user");
	if (user == null) {
		String title = "Create Poll Failed";
		String message = "You have not logged in. You need to login before posting a new poll.";
		String button  = "Go to Login";
		String url     = "login.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
		dispatcher.forward( request, response );
		return ;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<jsp:include page="head.jsp"/>
    <body>
	<jsp:include page="navigation.jsp"/>

    <div class="container">
        <div class="col-md-12" role="main">
          <h1 id="welcome-to-easypoll-system">Create New Poll</h1><br/>
	      <form action="CreatePollAction" method="post">
			<div class="panel panel-info">
                <div class="panel-heading">
                  <h3 class="panel-title">Poll Details</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-hover ">
                        <tbody>
                          <tr>
                            <td class="col-sm-3 col-md-3"><b>Title</b></td>
                            <td><input type="text" class="form-control" id="title" name="title" placeholder="Title" /></td>
                          </tr>
                          <tr>
                            <td class="col-sm-3 col-md-3"><b>Location</b></td>
                            <td><input type="text" class="form-control" id="location" name="location" placeholder="Location" /></td>
                          </tr>
                          <tr>
                            <td class="col-sm-3 col-md-3"><b>Status</b></td>
                            <td>
                              <select class="form-control" id="status" name="status">
                                <option>Open</option>
                                <option>Close</option>
                              </select>
                            </td>
                          </tr>
                        </tbody>
                    </table>
                    <h5 style="margin-top: 10px"><b>Event Description</b></h5>
                    <p style="margin-left: 2%">
                        <textarea class="form-control" rows="4" id="description" name="description"></textarea>
                    </p>
                    <br/>
                    <div class="row">
                        <div class="col-sm-4 col-md-4"></div>
                        <div class="col-sm-4 col-md-4"><button type="submit" class="btn btn-primary" style="width: 100%">Submit</button></div>
                        <div class="col-sm-4 col-md-4"></div>
                    </div>
                </div>
            </div>
	      </form>
        </div>
      </div>
    


    
	<jsp:include page="footer.jsp"/>
	<script src="./js/jquery-1.10.2.min.js"></script>
	<script src="./js/bootstrap-3.0.3.min.js"></script>
	<script src="./js/highlight.pack.js"></script>
	<script src="./js/base.js"></script>
    </body>
</html>