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
	<br><br>
	<a href="index"><h1 style="color:#a6a831;margin-left: 120px;text-align: center;">DataCoin Token Wallet</h1></a>
	<div class="login-wrap">
	    <div class="sign-up">
	        <div class="login-html">
	            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
	            <label for="tab-1" class="tab" style="padding: 16px 5px">Registration using Private Key
	                <img src="<c:url value="/resources/image/regis.png" />" class="img-responsive" style="width: 32px;display: inline-block;"> </label>
				<form action="proceedRegister" method="post">	            
	            <div class="login-form" style="padding: 0px 15px">
	                <div class="sign-in-htm">
	                	<div class="errorMessageDiv"><script>showErrorMessage('<c:out value='${param.errormsg}'/>')</script></div>
						<div class="successMessageDiv"><script>showSuccessMessage('<c:out value='${param.successmsg}'/>')</script></div>
	                    <div class="group">
	                        <label for="user" class="label">Private Key</label>
	                        <input id="privatekey" name="privatekey" type="text" class="input" placeholder="Private Key" value="<c:out value='${param.privatekey}'/>" required readonly="readonly" style="width: 80%;margin-left: 0px">
	                    </div>
	                    <div class="group">
	                        <label for="user" class="label">Address</label>
	                        <input id="address" name="address" type="text" class="input" placeholder="Address" value="<c:out value='${param.address}'/>" required readonly="readonly" style="width: 80%;margin-left: 25px">
	                    </div>
	                    <div class="group">
	                        <input type="submit" class="button" value="Proceed" style="margin: 4% 41%;width: 47%;color: #fff;"> 
	                        <a href="registerprivatekey" style="float: right;">Retry Again!</a>
	                    </div>
	                </div>
	            </div>
	            </form>
	        </div>
	    </div>
	</div>

</body>
</html>