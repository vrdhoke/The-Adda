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
<h3>Update Owner Account</h3>
  <div class="jumbotron">
        
        <p><font color="red">${msg}</font></p>
        <form action="/myapp/updateowner.htm" method="post">
          <div class="form-group">
          
          	<div class="form-group">
             Owner Id: <input type="text" name="userid" class="form-control" value="${user.getId()}" readonly="readonly" >
             </div>
          
          	<div class="form-group">
             Owner Name      <input type="text" name="username" class="form-control" placeholder="Enter User Name" value="${user.getName()}" required>
             </div>
             
             
            <div class="form-group">
             Owner Email:     <input type="email" name="email" class="form-control" placeholder="Enter Email Id" value = "${user.getEmail()}" required> 
             <small  class="form-text text-muted">We will not share your email id with anyone</small>
            </div>
            
            <div class="form-group">
             Update Password:     <input type="password" name="password" class="form-control" placeholder="Type new password" value="" required> 
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