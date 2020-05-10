<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TheAdda</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body style="background-color: #4b5257" >
<P color="white">${headerMsg}</P>

  <div class="container">
  <div class="jumbotron">
        <h3>Register for Events</h3>
        <br>
        <p class="text-left"><a href="/myapp/UserDashboard.htm">Go Back to HomePage</a></p>
        <P color="green">${Message}</P>
        
      
      </div>  
  
  
  
  <div class="row">
  
  		<c:forEach items="${events}" var="a" varStatus="count">   
            <div class="col-sm-3">
			    <div class="card">
			      <div class="card-body">
			      <form  action="/myapp/registerevent.htm" method="post">
			        <h4 class="card-title">${a.getEventname()}</h4>
			        <h6 class="card-title">Location : ${a.getLocation()}</h6>
			        <h6 class="card-title">Date: <fmt:formatDate value="${a.getEvent_date()}" pattern="yyyy-MM-dd" /></h6>
			        <p class="card-text">Details: ${a.getDetails()}</p>
			        <p class="card-text">Seats Available: ${a.getSeats_available()}</p>
			        <p class="card-text">Capacity: ${a.getCapacity()}</p>
			        <p class="card-text">Status: ${a.getStatus()}</p>
			        <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
			        <input type="hidden" name="eventid" value ="${a.getId()}" readonly>
			        <input type="submit" name="submit" class=" btn btn-primary" value="Register">
			        </form>
			      </div>
			    </div>
			  </div>
		  </c:forEach> 
 
  
</div>

  
  
 </div> 
</body>
</html>