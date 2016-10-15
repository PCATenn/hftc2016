<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
<title>PCAT inventory | Request items</title>

</head>
<body>

<form action="addUser">
	<nav>
		<a href="http://www.pcat.org/"> <img
			src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
		</a>

		<button name="logout" class="button logout-button neutral">
			Log out</button>
	</nav>

	<section>
		<div class="section-body">
			<h1>Add User</h1>

          <div class="inline-directive">
                    Enter First Name:
                <label><input type="text" name="firstName" id="firstName" /></label>
            </div>
            <br/>
            <div class="inline-directive">
                    Enter Last Name:
                <label><input type="text" name="firstName" id="firstName" /></label>
            </div>
            
            <div class="inline-directive">
                    Enter Email:
                <label><input type="text" name="email" id="email" /></label>
            </div>
            
            <div class="inline-directive">
                    Enter User Role:
                <label><input type="text" name="role" id="role" /></label>
            </div>
            
            <div class="inline-directive">
                    Enter Supervisor Name:
                <label><input type="text" name="supervisor" id="supervisor" /></label>
            </div>
            
            <div class="inline-directive">
                    Enter Supervisor Email:
                <label><input type="text" name="supervisoremail" id="supervisoremail" /></label>
            </div>
            
    <input type="submit" value="Add User" name="Submit" class="button medium-button affirmative"/>
    
    <input type="reset" value="reset" name="reset" class="button medium-button affirmative"/>

 			
		</div>
	</section>

	<footer> Prevent Child Abuse Tennessee </footer>
</form>
</body>
</html>
