<%@page contentType="application/xml"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="xsl/poll_detail.xsl"?>
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
	String id = request.getParameter("pollid");
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
	
	// Check if the poll exists in the system.
	POPollDetail pollDetail = database.getPollDetail(Integer.parseInt(id));
	if (pollDetail == null) {
		String title = "Get Poll Detail Failed (404)";
		String message = "The poll with id = " + id + " does not exist!";
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
	
	User user = (User)session.getAttribute("user");
	if (user == null) {
		pageContext.setAttribute("username", "<nulluser>");
	}
	else {
		pageContext.setAttribute("username", user.getName());
	}
	
	pageContext.setAttribute("pollDetail", pollDetail);
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<PollDetail>
	<id>${pollDetail.id}</id>
	<title>${pollDetail.title}</title>
	<creator>${pollDetail.creator}</creator>
	<c:if test="${ pollDetail.creator == username }">
		<ismine>true</ismine>
	</c:if>
	<c:if test="${ pollDetail.creator != username }">
		<ismine>false</ismine>
	</c:if>
	
	<date><fmt:formatDate pattern="dd/MM/yyyy" value="${pollDetail.date}" /></date>
	<location>${pollDetail.location}</location>
	<status>${pollDetail.status}</status>
	<updateStatus> ${pollDetail.status} </updateStatus>
	<description>${pollDetail.description}</description>
	<timeList>
		<c:forEach items="${pollDetail.time}" var="time">
			<timeSection>
				<time>${time}</time>
				<summary>poll_summary.jsp?pollid=${pollDetail.id}&amp;time=${time}</summary>
			</timeSection>
		</c:forEach>
	</timeList>
</PollDetail>