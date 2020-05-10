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
                $("#datepicker").datepicker();
            });
        </script>
</head>
<body>
<div class="container">
<div class="col-centered">
<h3>Update Venue</h3>
  <div class="jumbotron">
        
        <p><font color="red">${error}</font></p>
        <form action="/myapp/updatevenuedetails.htm" method="post">
          <div class="form-group">
          
          	<div class="form-group">
             Venue ID: <input type="text" name="venueid" class="form-control" value="${venue.getId()}" readonly="readonly" >
             </div>
          
             
           	<div class="form-group">
             Venue Location      <input type="text" name="venueloc" class="form-control" placeholder="Enter Venue Location" value="${venue.getLocation()}" >
             </div>
             
             <div class="form-group">
             Rooms Available     <input type="number" name="vroom" class="form-control" placeholder="Enter Number of rooms" value="${venue.getRooms()}" >
             </div>
             
             <div class="form-group">
             Transport Accessibility     <input type="text" name="venuetrans" class="form-control" placeholder="Enter Event Name" value="${venue.getTransportation()}" >
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
        <p class="text-left"><a href="/myapp/OwnerDashboard.htm">Go Back</a></p>
        </form>
      </div>  
  </div>
  </div>

</body>
</html>