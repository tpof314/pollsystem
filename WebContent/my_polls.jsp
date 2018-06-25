<%@page contentType="application/xml"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="xsl/homepage.xsl"?>
<%@page import="java.util.*" %>
<%@page import="com.pageobject.*" %>
<%@page import="com.model.*" %>

<% String userFile = application.getRealPath("WEB-INF/User.xml"); %>
<% String pollFile = application.getRealPath("WEB-INF/Poll.xml"); %>
<jsp:useBean id="database" class="com.dataaccess.DBConnector" scope="application">
    <jsp:setProperty name="database" property="userFile" value="<%=userFile%>"/>
    <jsp:setProperty name="database" property="pollFile" value="<%=pollFile%>"/>
</jsp:useBean>
<% 

	User user = (User)session.getAttribute("user");
	if (user == null) {
		String title = "View Polls Failed";
		String message = "You have not logged in. You need to login before viewing your polls.";
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

	ArrayList<POPollShort> pollList = database.getUsersPolls(user.getId()); 
	pageContext.setAttribute("pollList", pollList);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<homepage>
	<title>All My Polls</title>
	<total><%=pollList.size() %></total>
	<polls>
		<c:forEach items="${pollList}" var="poll">
		<poll id="${poll.id}">
			<title><c:out value="${poll.title}" /></title>
			<creator><c:out value="${poll.creator}" /></creator>
			<date><fmt:formatDate pattern="dd/MM/yyyy" value="${poll.date}" /></date>
			<description>${fn:substring(poll.description, 0, 100)} ...</description>
			<url>poll.jsp?pollid=${poll.id}</url>
		</poll>
		</c:forEach>
	</polls>
</homepage>