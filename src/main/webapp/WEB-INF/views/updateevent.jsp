<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TheAdda</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
            	$("#datepicker").datepicker({ minDate: 0 });
            });
        </script>
</head>
<body>
<div class="container">
<div class="col-centered">
<h3>Update Event</h3>
  <div class="jumbotron">
        
        <p><font color="green">${msg}</font></p>
        <form action="/myapp/updateeventdetails.htm" method="post">
          <div class="form-group">
          
          	<div class="form-group">
             Event ID: <input type="text" name="eventid" class="form-control" value="${event.getId()}" readonly="readonly" >
             </div>
          
          	<div class="form-group">
             Event Name      <input type="text" name="eventname" class="form-control" placeholder="Enter Event Name" value="${event.getEventname()}" >
             </div>
             
             
            <div class="form-group">
             Event Details:     <input type="text" name="details" class="form-control" placeholder="Specify the event details" value = "${event.getDetails()}"> 
             <small  class="form-text text-muted">Please enter more details about event</small>
            </div>
            
            <div class="form-group">
             Event Capacity:     <input type="number" name="capacity" class="form-control" placeholder="Capacity" value="${event.getCapacity()}"> 
            </div>
            
			<select name="status" class="custom-select" size="3" required>
			  <option value="Completed">Completed</option>
			  <option value="Canceled">Canceled</option>
			  <option value="Scheduled">Scheduled</option>
			</select>
            
            <div class="form-group">
            
             Date: <input type="text" name="eventdate" id="datepicker" class="form-control" value="${evdate}">
            </div>
            <p><font color="red">${booked}</font></p>
            
            <div class="form-group">
            
             <input type="hidden" name="oldeventdate" id="datepicker" class="form-control" value="${evdate}">
            </div>
            
            
           <!--  <select class="custom-select" size="3" name="status">
			  <option value="2">Completed</option>
			  <option value="3">Canceled</option>
			</select> -->
            
            
            
            <br>
            <input type="submit" name="submit" class="form-control" value="Update">
            <br>
            <input type="submit" name="submit" class="form-control" value="Delete">
          </div>
        <p class="text-left"><a href="/myapp/UserDashboard.htm">Go Back</a></p>
        </form>
      </div>  
  </div>
  </div>

</body>
</html>