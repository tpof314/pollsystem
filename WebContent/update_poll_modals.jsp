<div>
	<%
		int pollid = Integer.parseInt( request.getParameter("pollid") );
	%>
	
	<div id="newtimeModal" class="modal fade">
	  <div class="modal-dialog">
	  	<form action="AddTimeAction" method="post">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		        <h4 class="modal-title">Add New Time</h4>
		      </div>
		      <div class="modal-body">
		        <p>Add new time to this poll.</p>
		        <input type="hidden" name="pollid" value="<%=pollid %>" />
		        <p><input type="text" class="form-control" id="time" name="time" placeholder="e.g. 18/06/2016 2:00 pm" /></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
		        <button type="submit" class="btn btn-primary">Add Time</button>
		      </div>
		    </div>
	  	</form>
	  </div>
	</div>
	
	<!-- Modal -->
	<div id="deleteModal" class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
	        <h4 class="modal-title">Delete Poll</h4>
	      </div>
	      <div class="modal-body">
	        <p>Do you really want to delete this poll?</p>
	      </div>
	      <div class="modal-footer">
	        <form action="DeletePollAction" method="post">
	        	<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        	<input type="hidden" name="pollid" value="<%=pollid %>" />
	        	<button type="submit" class="btn btn-danger">Delete</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
</div>
