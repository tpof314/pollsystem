<%@page contentType="application/xml"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="xsl/poll_summary.xsl"?>
<%@page import="java.util.*" %>
<%@page import="com.pageobject.*" %>
<% String userFile = application.getRealPath("WEB-INF/User.xml"); %>
<% String pollFile = application.getRealPath("WEB-INF/Poll.xml"); %>
<jsp:useBean id="database" class="com.dataaccess.DBConnector" scope="application">
    <jsp:setProperty name="database" property="userFile" value="<%=userFile%>"/>
    <jsp:setProperty name="database" property="pollFile" value="<%=pollFile%>"/>
</jsp:useBean>
<% 
	String id   = request.getParameter("pollid");
	String time = request.getParameter("time");
	
	// Check if the id is given.
	if (id == null) {
		String title = "Get Poll Detail Failed";
		String message = "No poll ids specified";
		String button  = "Go to Home";
		String url     = "index.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
		dispatcher.forward( request, response );
		return ;
	}
	
	// Check if the id is given.
	if (time == null) {
			String title = "Get Poll Detail Failed";
			String message = "No poll time specified";
			String button  = "Go to Home";
			String url     = "index.jsp";
			request.setAttribute("title", title);
			request.setAttribute("message", message);
			request.setAttribute("button", button);
			request.setAttribute("url", url);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
			dispatcher.forward( request, response );
			return ;
		}
	
	// Check if the poll exists in the system.
	POPollSummary summary = database.getPollSummary(Integer.parseInt(id), time);
	if (summary == null) {
		String title = "Get Poll Summary Failed (404)";
		String message = "The poll at time = " + time + " does not exist!";
		String button  = "Go to Home";
		String url     = "index.jsp";
		request.setAttribute("title", title);
		request.setAttribute("message", message);
		request.setAttribute("button", button);
		request.setAttribute("url", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/message_failed.jsp");
		dispatcher.forward( request, response );
		return ;
	}
	
	pageContext.setAttribute("summary", summary);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<PollSummary>
	<title>${summary.title}</title>
	<creator>${summary.creator}</creator>
	<date><fmt:formatDate pattern="dd/MM/yyyy" value="${summary.postDate}" /></date>
	<location>${summary.location}</location>
	<status>${summary.status}</status>
	<time>${summary.time}</time>
	<description>${summary.description}</description>
	<total> ${fn:length(summary.followers)} </total>
	<followers>
		<c:forEach items="${summary.followers}" var="time">
			<follower>
				<name>${time}</name>
			</follower>
		</c:forEach>
	</followers>
</PollSummary>