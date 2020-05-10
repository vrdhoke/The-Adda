<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TheAdda</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>


  <div class="container">
  <div class="jumbotron">
        <h3>Select Venue for Creating Event</h3>
        
         <table id="booking">
          <tr>
            <th>Venue Id</th>
            <th>Venue Location</th>
            <th>Rooms Available</th>
            <th>Transport Accessibility</th>
            <th>Venue Owner<th>
          </tr>
           <c:forEach items="${venues}" var="a" varStatus="count">   
          <tr>
            <form  action="/myapp/createevent.htm" method="post">
            <td><input type="text"  name="venueId" value ="${a.getId()}" readonly></td>                     
		    <td><input type="text"  name="venuelocation" value ="${a.getLocation()}" readonly></td>                     
            <td><input type="text"  name="venueroom" value="${a.getRooms()}" readonly></td> 
            <td><input type="text"  name="venuetransport" value="${a.getTransportation()}" readonly></td>
            <td><input type="text"  name="venueowner" value="${a.getOwner().getName()}" readonly></td>
		    <td><input type="submit" name="submit" class="form-control" value="Select"></td>
		    </form>
		    </tr>
		  </c:forEach>
        </table>  
       
        <p class="text-left"><a href="/myapp/UserDashboard.htm">Go Back to HomePage</a></p>
      </div>  
  </div>
</body>
</html>