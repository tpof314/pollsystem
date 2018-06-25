<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	User user = (User) session.getAttribute("user");
%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Collapsed navigation -->
        <div class="navbar-header">
            <!-- Expander button -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Main title -->
            <a class="navbar-brand" href=".">Easy Poll System</a>
        </div>

        <!-- Expanded navigation -->
        <div class="navbar-collapse collapse">
            <!-- Main navigation -->
            <ul class="nav navbar-nav">
                <li>
                    <a href=".">Home</a>
                </li>
                <% if (user != null) { %>
                <li>
                    <a href="my_polls.jsp">View My Polls</a>
                </li>
                <li>
                    <a href="create_poll.jsp">New Poll</a>
                </li>
                <% } %>
            </ul>

            <!-- Search, Navigation and Repo links -->
            <ul class="nav navbar-nav navbar-right">
            	<% if (user == null) { %>
                <li>
                    <a href="login.jsp">
                        <i class="fa fa-sign-in" aria-hidden="true"></i> Login
                    </a>
                </li>
                <% } %>
                
                <% if (user != null) { %>
                <li>
                	<a>Welcome, <%=user.getName() %></a>
                </li>
                <li>
                    <a href="LogoutAction">
                        <i class="fa fa-sign-in" aria-hidden="true"></i> Logout
                    </a>
                </li>
                <% } %>
                
            </ul>
        </div>
    </div>
</div>