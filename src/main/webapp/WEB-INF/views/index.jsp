<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>DataCoin Token - Login</title>
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
    <!--Sign in page-->
    <br><br>
    <%-- <img class="img-responsive" src="<c:url value="/resources/image/logo.svg" />" style="width:15%;margin: 24px 44%;"> --%>
    <h1 style="color:#a6a831;margin-left: 120px;text-align: center;">DataCoin Token Wallet</h1>
                                           
<div class="login-wrap">

    <div class="sign-up">

        <div class="login-html">
             <input id="tab-1"  type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab" style="padding: 16px 5px;">Sign In
            <%-- <img src="<c:url value="/resources/image/user-login.png" />" class="img-responsive" style="width: 68px;display: inline-block;">  --%></label>
            <div class="login-form">
                <div class="sign-in-htm">
                	<div class="errorMessageDiv"><script>showErrorMessage('<c:out value='${param.errormsg}'/>')</script></div>
					<div class="successMessageDiv"><script>showSuccessMessage('<c:out value='${param.successmsg}'/>')</script></div>
               	 <form action="loginUser" method="post">
                    <div class="group">
                        <label for="user" class="label">Username </label>
                         <input id="userName" name="userName" type="text" class="input" placeholder="Username" required>
                     </div>
                    <div class="group">
                         <label for="pass" class="label">Password </label>
                         <input id="password" name="password" type="password" class="input" data-type="password" placeholder="Password" required>
                    </div>
                     <div class="group">
                         <input type="submit" class="button" value="Sign In" style="margin: 4% 41%;width: 47%;color:#fff;">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                         <div class="col-md-12 control">
                                 Don't have an account!
                         </div>
                         <div class="col-md-12 control" style="margin-top: 15px;"><a href="register"> Sign Up Here </a> | <a href="registerprivatekey"> Upload Private key  </a></div>
                     </div>
                  </form>
                </div>
             </div>
        </div>
    </div>
</div>

</body>
</html>