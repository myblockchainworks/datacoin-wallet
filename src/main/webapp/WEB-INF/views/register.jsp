<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>DataCoin Token - Register</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value="/resources/js/tgw.js" />"></script>
</head>
<body>
        <!--sign up page-->
	<%-- <a href="index"><img class="img-responsive" src="<c:url value="/resources/image/logo.svg" />" style="width:15%;margin: 0px 43%"></a>  --%>
	<br>
	<a href="index"><h1 style="color:#a6a831;margin-left: 120px;text-align: center;">DataCoin Token Wallet</h1></a>
	<div class="login-wrap">
	    <div class="sign-up">
	        <div class="login-html">
	            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
	            <label for="tab-1" class="tab" style="padding: 16px 5px">Registration
	                <img src="<c:url value="/resources/image/regis.png" />" class="img-responsive" style="width: 32px;display: inline-block;"> </label>
				<form action="registerUser" method="post">	            
	            <div class="login-form">
	                <div class="sign-in-htm">
	                	<div class="errorMessageDiv"><script>showErrorMessage('<c:out value='${param.errormsg}'/>')</script></div>
						<div class="successMessageDiv"><script>showSuccessMessage('<c:out value='${param.successmsg}'/>')</script></div>
	                    <div class="group">
	                        <label for="user" class="label">Full Name</label>
	                        <input id="fullname" name="fullname" type="text" class="input" placeholder="Fullname" required>
	                    </div>
	                    <div class="group">
	                        <label for="user" class="label">Username</label>
	                        <input id="username" name="username" type="text" class="input" placeholder="Username" required>
	                    </div>
	                    <div class="group">
	                        <label for="pass" class="label">Password</label>
	                        <input id="password" name="password" type="password" class="input" data-type="password" placeholder="Password" required>
	                    </div>
	                    <div class="group">
	                        <label for="pass" class="label">Confirm Password</label>
	                        <input id="confirmPassword" name="confirmPassword" type="password" class="input" data-type="password" placeholder="Confirm Password" style="margin-left: -4px !important;" required>
	                    </div>
	                    <div class="group">
	                        <label for="pass" class="label">Contact Number</label>
	                        <input id="contactNumber" name="contactNumber" type="number" class="input" data-type="number" placeholder="Contact Number" style="margin-left: 18px;" required>
	                    </div>
	                    <div class="group">
	                        <label for="pass" class="label">Email</label>
	                        <input id="email" name="email" type="email" class="input" data-type="email" placeholder="Email" required style="margin-left: 108px;" >
	                    </div>
	                    <div class="group">
	                    	<input type="hidden" name="privatekey" value="<c:out value='${param.privatekey}'/>">
	                    	<input type="hidden" name="address" value="<c:out value='${param.address}'/>">
	                        <input type="submit" class="button" value="Register" style="margin: 4% 41%;width: 47%;color: #fff;"> 
	                    </div>
	                </div>
	            </div>
	            </form>
	        </div>
	    </div>
	</div>

</body>
</html>