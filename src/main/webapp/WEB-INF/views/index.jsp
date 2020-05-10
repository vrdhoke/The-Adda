<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">

<title>Sign-Up/Login Form</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


</head>
	<%        
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    /* response.setHeader("Pragma", "No-cache");
    response.setDateHeader("Expires", 0); */
	%>
<body>

          <div class="container w-50">
              <div class="col-centered">
              <br>
              <br>
              <h1 class="text-center">The Adda</h1><br>
              <br>
              <div class="jumbotron">
				  
                  <p><font color="red">${invalidUser}</font></p>
                  <p><font color="red">${msg}</font></p>
                  <% session.removeAttribute("invalidUser"); %>
                  <p><font color="red">${authorizationFailed}</font></p>

                  <h5>Please login</h5>

                    <form action="/myapp/userlogin.htm" method="post">
                      <div class="form-group">
                        <input type="text" name="userName" class="form-control" placeholder="Enter Username" required>
                      </div>
                      <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Enter password" required>
                      </div>
                      <input type="hidden" value="option" value="login"/>
                      
                      
                      <div class="form-group">    			
			    	   <input type="radio" name="role" value="owner" required>  Owner  
			    	   <input type="radio" name="role" value="user" required>   User <br>
				        </div>
				    
                      <input type="submit" value="Login" class="btn-primary form-control"/>
                      
                    </form>
                  <br>
                  
                  
                  
                  <form action="/myapp/usersignup.htm" method="post">
                      <input type="submit" value="Go to Signup" class="btn-primary form-control"/>
                  </form>
         
                  </div>
              </div>

              </div>
           

		
		<!-- tab-content -->


	<!-- /form -->
	

	



</body>
</html>
