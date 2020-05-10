<%-- 
    Document   : signup
    Created on : Mar 27, 2020, 9:01:43 PM
    Author     : vaibhavdhoke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TheAdda</title>
    </head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <body>
        <div class="container w-50">
              <div class="col-centered">
              <br><br>
              <h1 class="text-center">The Adda</h1><br>
              <div class="jumbotron">

                  <p><font color="red">${invalidUser}</font></p>
                  <p><font color="red">${authorizationFailed}</font></p>

                  <h5>Signup</h5>

                    <form action="/myapp/userregister.htm" method="post">
                      <div class="form-group">
                          <input type="text" name="firstName" class="form-control" placeholder="Enter First Name" required>
                      </div>
                      <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Enter Email Id">
                      </div>
                      <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Enter password">
                      </div>
                      <input type="hidden" value="option" value="register"/>
                      
                      	<div class="form-group">    			
				    	   <input type="radio" name="role" value="owner">  Owner  
				    	   <input type="radio" name="role" value="user">   User <br>
				        </div>
                      <input type="submit" value="Signup" class="btn-primary form-control"/>
                    </form>
                    <br>
                    
                    <form action="/myapp/.htm" method="GET">
                      <input type="submit" value="Go to Login" class="btn-primary form-control"/>
                  </form>
                  </div>
              </div>

              </div>
    </body>
</html>
