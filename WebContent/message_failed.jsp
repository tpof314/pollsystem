<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<jsp:include page="head.jsp"/>
    <body>
	<jsp:include page="navigation.jsp"/>

    <div class="container">
        <div class="col-md-12" role="main">

            <h1 id="welcome-to-easypoll-system">System Response</h1>
            <br/>
            <div class="panel panel-danger" style="width: 60%; margin-left: 20%">
                <div class="panel-heading">
                  <h3 class="panel-title">${ title }</h3>
                </div>

                <div class="panel-body">
                    <div class="form-group">
                        <h2>${ title }</h2>
                        <p style="margin-left: 2%; margin-top: 25px">${ message }</p>
                        <br/>
                        <a class="btn btn-danger" href="${ url }" style="width: 50%; margin-left: 25%">${ button }</a>
                    </div>
                </div>

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