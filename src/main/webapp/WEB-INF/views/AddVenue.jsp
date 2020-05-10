<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TheAdda</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>

<div class="container">
<div class="col-centered">
<h3>Add Venue</h3>
  <div class="jumbotron">
        
        <p><font color="red">${error}</font></p>
        <form action="/myapp/addvenuedetails.htm" method="post">
          <div class="form-group">
          <div class="form-group">
             Venue Location:     <input type="text" name="location" class="form-control" placeholder="Enter Venue Address" >
             <small id="emailHelp" class="form-text text-muted">Please enter the venue location.</small>
             </div>
             <div class="form-group">
             Rooms Available: <input type="number" name="availability" class="form-control" placeholder="How many rooms are available?"  >
             </div>
             <div class="form-group">
             Transport Accessibility:     <input type="text" name="transport" class="form-control" placeholder="Specify the transport accessibility">
            </div>
            <br>
            <input type="submit" name="submit" class="form-control" value="Submit">
          </div>
        <p class="text-left"><a href="/myapp/OwnerDashboard.htm">Go Back</a></p>
        </form>
      </div>  
  </div>
  </div>


</body>
</html>