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

  <!-- <div class="container"> -->
  <div class="jumbotron">
        <h3>Events Registered by you</h3>
        <P color="green">${Message}</P>
        <p><font color="red">${msg}</font></p>
        <p><font color="green">${registered}</font></p>
        
         <table id="booking">
          <tr>
            <th>Event Id</th>
            <th>Event Name</th>
            <th>Location</th>
            <th>Details</th>
            <th>Date</th>
            <th>Seats Available</th>
            <th>Capacity</th>
            <th>Status</th>
            <!-- <th>Action</th> -->
          </tr>
           <c:forEach items="${events}" var="a" varStatus="count">   
          <tr>
            <form  action="/myapp/deregister.htm" method="post">
            <td><input type="text"  name="eventid" value ="${a.getId()}" readonly></td>                     
		    <td><input type="text"  name="eventname" value ="${a.getEventname()}" readonly></td>                     
            <td><input type="text" name="eventloc" value="${a.getLocation()}" readonly></td> 
            <td><input type="text"  name="eventdetails" value="${a.getDetails()}" readonly></td>
            <td><input value="<fmt:formatDate value="${a.getEvent_date()}" pattern="yyyy-MM-dd" />"readonly></td> 
            <td><input type="text"  name="seats" value ="${a.getSeats_available()}" readonly></td> 
            <td><input type="text"  name="cap" value ="${a.getCapacity()}" readonly></td> 
            <td><input type="text"  name="status" value ="${a.getStatus()}" readonly></td>
		    <!-- <td><input type="submit" name="submit" class="form-control" value="De-register"></td> -->
		    </form>
		  </tr>
		  </c:forEach>
        </table> 
        
        <!-- <p class="text-left"><a href="/myapp/DownloadPDF.htm">Download Pdf</a></p>  -->
       
        <p class="text-left"><a href="/myapp/UserDashboard.htm">Go Back to HomePage</a></p>
      </div>  
  <!-- </div> -->
</body>
</html>