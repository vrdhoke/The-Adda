

<%-- 
    Document   : login-success
    Created on : Feb 15, 2020, 2:52:55 PM
    Author     : vaibhavdhoke
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
-->       
        <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous">
        </script>
        <title>TheAdda</title>
        
<!--        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js">  </script>
         THEN bootstrap 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>-->
       
        
<!--        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
-->     
       
    </head>
    <%        
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    /* response.setHeader("Pragma", "No-cache");
    response.setDateHeader("Expires", 0); */
	%>
    <body>
         
        
        
        <div>
        <h4></h4>
        <div class="container w-100">
        <div class="jumbotron">
            <div class="row">
            <div class="col-4">
            </div>
            <div class="col-5">
            <h1 class="display-3">The Adda</h1>
            </div>
            <div class="col">
            </div>
            </div>
            
            <div class="row">
            <div class="col-10">
              
            <h5>${message}</h5>
            </div>
            <div class="col">
              
            </div>
            <div class="col">
              <div class="btn-group">
                <a class="btn btn-primary" href="#"><i class="icon-user"></i> User</a>
                <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"></a>
                  <!--<span class="icon-caret-down"></span></a>-->
                <ul class="dropdown-menu">
                  <li><a href="/myapp/editowner.htm"><i class="icon-fixed-width icon-pencil"></i> Edit</a></li>
                  <li><a href="/myapp/ownerlogout.htm"><i class="icon-fixed-width icon-ban-circle"></i> Logout</a></li>
                </ul>
               </div>
            </div>
          </div>
          <br><br><br>
          <p class="display-4">List Your Property for Events</p>
            </div>
            
            
            <p class="lead"><font color="green">${msg}</font></p>
            <hr class="my-4">
            <h5>TheAdda is a platform used to organize online groups that host in-person events for people with similar interests</h5>
            
            <hr class="my-4">
            <p class="lead">
              <a class="btn btn-primary btn-lg" href="/myapp/ownervenues.htm" role="button">Explore</a>
            </p>
          
          
          
		
          
          <div class="row">
          <div class="col">
        	<div class="card" style="width: 30rem;">
			  <img src="https://www.generationshall.com/wp-content/uploads/2015/02/neworleans-weddingvenue2.jpg" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h5 class="card-title">Add Venues</h5>
			    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			    <a href="/myapp/addvenue.htm" class="btn btn-primary">Add</a>
			  </div>
			</div>
			</div>
			
			<div class="col">
			<div class="card" style="width: 30rem;">
			  <img src="https://www.oregonzoo.org/sites/default/files/styles/article-full/public/2017/03/15/H_Conservation-Hall.jpg?itok=68Lt1h2X" class="card-img-top" alt="...">
			  <div class="card-body">
			    <h5 class="card-title">View Registered Venues</h5>
			    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
			    <a href="/myapp/ownervenues.htm" class="btn btn-primary">View</a>
			  </div>
			</div>
			</div>
        	</div>
        	</div>
        
        
        
        
    </body>
</html>
