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
		            <h1 id="welcome-to-easypoll-system">Welcome to EasyPoll System</h1>
		            <p>Whether used for business administration or for more personal reasons, there are many occasions when a free voting poll can be a useful tool. Perhaps you need to canvas opinion regarding your next corporate event or, perhaps, you need to organise your next holiday with a group of friends – whatever the reason, EasyPoll makes the whole process extremely simple.</p>

		            <h2 id="project-layout"> <xsl:value-of select="homepage/title"/> </h2>
		            <div class="bs-component">
		              <div class="list-group">
		              	<xsl:for-each select="homepage/polls/poll">
							<div class="row" style="padding-top: 10px; padding-bottom: 10px">
			                    <div class="col-xs-3 col-sm-2 col-md-1">
			                        <img src="img/note.png" style="width: 100%; margin-left: 5px"/>
			                    </div>
			                    <div class="col-xs-9 col-sm-10 col-md-10">
			                        <a href="{url}" class="list-group-item">
			                          <h4 class="list-group-item-heading"><xsl:value-of select="title"/></h4>
			                          <div class="row" style="margin-top: 8px; margin-bottom: 10px">
			                          	<div class="col-sm-4"><b>Creator: </b> <xsl:value-of select="creator"/> </div>
			                          	<div class="col-sm-4"><b>Date: </b> <xsl:value-of select="date"/> </div>
			                          </div>
			                          <p class="list-group-item-text"><xsl:value-of select="description" /></p>
			                        </a>
			                    </div>
			                </div>
		              	</xsl:for-each>
		              </div>
		            <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
		        </div>
		    </div>
		    <xsl:variable name="footerData" select="document('../footer.jsp')" />
			<xsl:copy-of select="$footerData/*"/>
			
			<script src="./js/jquery-1.10.2.min.js"></script>
			<script src="./js/bootstrap-3.0.3.min.js"></script>
			<script src="./js/highlight.pack.js"></script>
			<script src="./js/base.js"></script>
		    </body>
		</html>
	</xsl:template>
</xsl:stylesheet>