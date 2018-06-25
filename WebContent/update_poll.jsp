<div class="row" style="text-align: right">
	<%@page import="java.util.*" %>
	<%@page import="com.pageobject.*" %>
	<%@page import="com.model.*" %>
	<% String userFile = application.getRealPath("WEB-INF/User.xml"); %>
	<% String pollFile = application.getRealPath("WEB-INF/Poll.xml"); %>
	<jsp:useBean id="database" class="com.dataaccess.DBConnector" scope="application">
	    <jsp:setProperty name="database" property="userFile" value="<%=userFile%>"/>
	    <jsp:setProperty name="database" property="pollFile" value="<%=pollFile%>"/>
	</jsp:useBean>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<% 
		int pollid = Integer.parseInt( request.getParameter("pollid") );
		Poll poll = database.getPolls().getPollDetail(pollid);
		User user = (User)session.getAttribute("user");
		String status = request.getParameter("status");
		pageContext.setAttribute("status", status);
		pageContext.setAttribute("pollid", pollid);
	%>

	<% if (user != null ) { if (user.getId() == poll.getCreator()) { %>
	<button class="btn btn-sm btn-success" data-toggle="modal" data-target="#newtimeModal" style="margin-right: 1%">New Time</button>
	<c:if test="${ status  == 'Open'}">
		<a class="btn btn-sm btn-primary" style="margin-right: 1%" href="ChangePollStatus?pollid=${pollid}&amp;status=Close">Close</a>
	</c:if>
	<c:if test="${ status  == 'Close'}">
		<a class="btn btn-sm btn-primary" style="margin-right: 1%" href="ChangePollStatus?pollid=${pollid}&amp;status=Open">Open</a>
	</c:if>
	<button class="btn btn-sm btn-danger"  data-toggle="modal" data-target="#deleteModal" style="margin-right: 4%">Delete Poll</button>
	<% } } %>
</div>


