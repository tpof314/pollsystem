<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html lang="en">
		<xsl:variable name="headData" select="document('../head.jsp')" />
		<xsl:copy-of select="$headData/*"/>
		
	    <body>
	    <xsl:variable name="navigationData" select="document('../navigation.jsp')" />
		<xsl:copy-of select="$navigationData/*"/>

	    <div class="container">
	        <div class="col-md-12" role="main">
	            <h1 id="welcome-to-easypoll-system">Poll Summary</h1><br/>
	            <h3>
	            	<xsl:value-of select="PollSummary/title"/>
	            	 - 
	            	<xsl:value-of select="PollSummary/time"/>
	            </h3>
	            <br/>
	            <div class="panel panel-info">
	                <div class="panel-heading">
	                  <h3 class="panel-title">Poll Details</h3>
	                </div>
	                <div class="panel-body">
	                    <table class="table table-striped table-hover ">
	                        <tbody>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Creator</b></td>
	                            <td><xsl:value-of select="PollSummary/creator"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Date of Poll</b></td>
	                            <td><xsl:value-of select="PollSummary/date"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Meeting Time</b></td>
	                            <td><xsl:value-of select="PollSummary/time"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Location</b></td>
	                            <td><xsl:value-of select="PollSummary/location"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Followers</b></td>
	                            <td><xsl:value-of select="PollSummary/total"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Status</b></td>
	                            <td><xsl:value-of select="PollSummary/status"/></td>
	                          </tr>
	                        </tbody>
	                    </table>
	                    <h5 style="margin-top: 10px"><b>Event Description</b></h5>
	                    <p style="margin-left: 2%">
	                    	<xsl:value-of select="PollSummary/description"/>    
	                    </p>
	                    <br/>
	                    
	                    <h5 style="margin-top: 10px"><b>Follower List</b></h5>
	                    <table class="table table-striped table-hover ">
	                        <thead>
	                          <tr>
	                          	<th class="col-sm-1 col-md-1"></th>
	                            <th class="col-sm-7 col-md-7">Name</th>
	                          </tr>
	                        </thead>
	                        <tbody>
	                          <xsl:for-each select="PollSummary/followers/follower">
		                          <tr>
		                            <td style="text-align: center">&#9899;</td>
		                            <td><xsl:value-of select="name"/></td>
		                          </tr>
	                          </xsl:for-each>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <xsl:variable name="footerData" select="document('../footer.jsp')" />
		<xsl:copy-of select="$footerData/*"/>
		
		<xsl:variable name="modalsData" select="document('../update_poll_modals.jsp')" />
		<xsl:copy-of select="$modalsData/*"/>
		
		<script src="./js/jquery-1.10.2.min.js"></script>
		<script src="./js/bootstrap-3.0.3.min.js"></script>
		<script src="./js/highlight.pack.js"></script>
		<script src="./js/base.js"></script>
	    </body>
	</html>
</xsl:template>
</xsl:stylesheet>