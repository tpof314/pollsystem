<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html lang="en">
		<xsl:variable name="headData" select="document('../head.jsp')" />
		<xsl:copy-of select="$headData/*"/>
		
	    <body>
	    <xsl:variable name="navigationData" select="document('../navigation.jsp')" />
		<xsl:copy-of select="$navigationData/*"/>

		<xsl:variable name="pollid" select="PollDetail/id" />
	    <div class="container">
	        <div class="col-md-12" role="main">
	            <h1 id="welcome-to-easypoll-system">Poll Detail</h1><br/>
	            <h3><xsl:value-of select="PollDetail/title"/></h3><br/>
	            <div class="panel panel-info">
	                <div class="panel-heading">
	                  <h3 class="panel-title">Poll Details</h3>
	                </div>
	                <div class="panel-body">
	                    <table class="table table-striped table-hover ">
	                        <tbody>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Creator</b></td>
	                            <td><xsl:value-of select="PollDetail/creator"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Date of Poll</b></td>
	                            <td><xsl:value-of select="PollDetail/date"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Location</b></td>
	                            <td><xsl:value-of select="PollDetail/location"/></td>
	                          </tr>
	                          <tr>
	                            <td class="col-sm-3 col-md-3"><b>Status</b></td>
	                            <td><xsl:value-of select="PollDetail/status"/></td>
	                          </tr>
	                        </tbody>
	                    </table>
	                    <h5 style="margin-top: 10px"><b>Event Description</b></h5>
	                    <p style="margin-left: 2%">
	                    	<xsl:value-of select="PollDetail/description"/>    
	                    </p>
	                    <br/>
	                    
	                    <xsl:variable name="mypollid" select="../id" />
	                    
	                    <xsl:if test="PollDetail/status = 'Open'">
	                    	<xsl:variable name="statusURL" select="string(concat('../update_poll.jsp?status=Open&amp;pollid=', $pollid))" />
							<xsl:variable name="updatePollData" select="document($statusURL)" />
							<xsl:copy-of select="$updatePollData/*"/>
						</xsl:if>
	                    
	                    <xsl:if test="PollDetail/status = 'Close'">
	                    	<xsl:variable name="statusURL" select="string(concat('../update_poll.jsp?status=Close&amp;pollid=', $pollid))" />
							<xsl:variable name="updatePollData" select="document($statusURL)" />
							<xsl:copy-of select="$updatePollData/*"/>
						</xsl:if>
	                    <h5 style="margin-top: 10px"><b>Meeting Time</b></h5>
	                    <table class="table table-striped table-hover ">
	                        <thead>
	                          <tr>
	                            <th class="col-sm-7 col-md-7">Time</th>
	                            <th class="col-sm-3 col-md-3">Join this session</th>
	                            <th class="col-sm-2 col-md-2">Summary</th>  
	                          </tr>
	                        </thead>
	                        <tbody>
	                          <xsl:for-each select="PollDetail/timeList/timeSection">
		                          <tr>
		                            <td><xsl:value-of select="time"/> </td>
		                            <td>
		                            	<form style="float: left" action="JoinAction" method="post">
		                            		<input type="hidden" name="pollid">
		                            		  <xsl:attribute name="value">
											    <xsl:value-of select="../../id" />
											  </xsl:attribute>
		                            		</input>
		                            		<input type="hidden" name="time" value="{time}"></input> 
		                            		<button type="submit" class="btn btn-primary btn-xs">Join</button>
		                            	</form>
		                            	
		                            	
		                            	<xsl:if test="../../ismine = 'true'">
		                            	<form sytle="float: left" action="RemoveTimeAction" method="post">
		                            		<input type="hidden" name="pollid">
		                            		  <xsl:attribute name="value">
											    <xsl:value-of select="../../id" />
											  </xsl:attribute>
		                            		</input>
		                            		<input type="hidden" name="time" value="{time}"></input> 
		                            		<button type="submit" class="btn btn-danger  btn-xs">Remove</button>
		                            	</form>
		                            	</xsl:if>
		                            </td>
		                            <td><a class="btn btn-success btn-xs" href="{summary}">Summary</a></td>
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

		<xsl:variable name="modalURL" select="string(concat('../update_poll_modals.jsp?pollid=', $pollid))" />
		<xsl:variable name="modalsData" select="document($modalURL)" />
		<xsl:copy-of select="$modalsData/*"/>
		
		<script src="./js/jquery-1.10.2.min.js"></script>
		<script src="./js/bootstrap-3.0.3.min.js"></script>
		<script src="./js/highlight.pack.js"></script>
		<script src="./js/base.js"></script>
	    </body>
	</html>
</xsl:template>
</xsl:stylesheet>