<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="com.pageobject.*" %>
<% String userFile = application.getRealPath("WEB-INF/User.xml"); %>
<% String pollFile = application.getRealPath("WEB-INF/Poll.xml"); %>
<jsp:useBean id="database" class="com.dataaccess.DBConnector" scope="application">
    <jsp:setProperty name="database" property="userFile" value="<%=userFile%>"/>
    <jsp:setProperty name="database" property="pollFile" value="<%=pollFile%>"/>
</jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<jsp:include page="head.jsp"/>
    <body>
	<jsp:include page="navigation.jsp"/>

    <div class="container">
        <div class="col-md-12" role="main">
            <h1 id="welcome-to-easypoll-system">Login</h1>
            <br/>
            <div class="panel panel-primary" style="width: 60%; margin-left: 20%">
                <div class="panel-heading">
                  <h3 class="panel-title">Login</h3>
                </div>
				
				<form action="LoginAction" method="post">
                <div class="panel-body">
                    <div class="form-group">
                    	<div class="row">
							<label for="username" class="col-sm-12 control-label">Username</label>
	                        <div class="col-sm-12">
	                          <input type="text" class="form-control" id="username" name="username" placeholder="Username">
	                        </div>
	
	                        <label for="password" class="col-sm-12 control-label" style="margin-top: 10px">Password</label>
	                        <div class="col-sm-12">
	                          <input type="password" class="form-control" id="password" name="password" placeholder="Password">
	                        </div>
                    	</div>
                    	<br/>
                        <div class="row">
                        	<button class="btn btn-primary" type="submit" style="width: 50%; margin-left: 25%">login</button>
                        </div>
                    </div>
                </div>
            	</form>
            </div>
        </div>
    </div>
    
	<jsp:include page="footer.jsp"/>
	<script src="./js/jquery-1.10.2.min.js"></script>
	<script src="./js/bootstrap-3.0.3.min.js"></script>
	<script src="./js/highlight.pack.js"></script>
	<script src="./js/base.js"></script>
    </body>
</html>