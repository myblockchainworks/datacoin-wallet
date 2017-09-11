<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>DataCoin Token</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="<c:url value="/resources/js/tgw.js" />"></script>

</head>
<body>


<div id="wrapper">
    <!-- header -->
    <div class="headermain">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="col-lg-6">
                <div class="col-xs-12"><h2 style="color:#a6a831;">DataCoin Token Wallet</h2></div>

                <!--<p style=""> My Wallet </p>-->

            </div>
            <div class="col-lg-4">
                <!-- <input type="text" placeholder="Search Symbol" style="width:55%;margin: 10px 0px;"/>
                <button class="btn-block">Search</button> -->
            </div>
            <div class="col-lg-2" style="padding-top: 10px;">
                <span>${currentUser.fullname}</span>
                <a href="logout"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
            </div>
        </div>

    </div>
</div>
<!-- menu -->


<!-- /. leftside  -->
<section style="color:#2d313b; margin-top:5px">
    <div class="leftsidebar">
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse menubar">
                <ul class="nav ">


                    <li>
                        <a href="mywallet">My Wallet </a>
                    </li>
                    <li>
                        <a href="transactions">My Transactions </a>
                    </li>
              		<li>
                        <a href="buytoken">Buy Token</a>
                    </li>
                    <li>
                        <a href="sendtoken">Send Token</a>
                    </li>
                    <li class="active">
                        <a href="#">Wallet Settings</a>
                    </li>

                </ul>
            </div>

            
        </nav>

    </div>
    <!-- /. leftside end  -->


    <div id="page-wrapper">
        <!-- <div id="page-inner">  -->
        <!-- /. ROW  -->
        
        <!-- left sidebar  -->
        <div class="col-lg-12">
            <div class="panel panel-default" style="height:600px;">
                <div class="panel-heading">Wallet Settings</div>
                <div class="panel-body">
                
	                <div style="padding: 10px">
						 <img src="https://chart.googleapis.com/chart?chs=250x250&cht=qr&chl=ethereum:${currentUser.bcaddress }" alt=""
	                         style="width:135px;height:135px;float: right"/>
						<div class="form-inline">
							<label for="name" style="width: 250px;"><span>Name</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${currentUser.fullname }</span></label>
						</div>
		
						<div class="form-inline">
							<label for="user_name" style="width: 250px;"><span>User Name</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${currentUser.username }</span></label>
						</div>
						
						<div class="form-inline">
							<label for="name" style="width: 250px;"><span>Current Balance</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${myBalance } ETH</span></label>
						</div>
						
						
						<div class="form-inline">
							<label for="user_name" style="width: 250px;"><span>Public Address</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${currentUser.bcaddress }</span></label>
						</div>
						
						<div class="form-inline">
							<label for="user_name" style="width: 250px;"><span>Private Address</span></label> <span>:</span> <label for="name" style="font-weight: normal;" id="privatekey">
								<span>${currentUser.privatekey }</span></label>
							<input type="hidden" name="keystoreJson" id="keystoreJson" value="${currentUser.keystore}">
						</div>
		
						<div class="form-inline">
							<label for="number" style="width: 250px;"><span>Contact Number</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${currentUser.contactnumber }</span></label>
						</div>
						<div class="form-inline">
							<label for="email" style="width: 250px;"><span>Email</span></label> <span>:</span> <label for="name" style="font-weight: normal;">
								<span>${currentUser.email }</span></label>
						</div>
						<div class="form-inline">
							<c:forEach items="${sessions}" var="session">
								<c:set var="count" value="${count + 1}" scope="page"/>
								<script>updateSessionDetail('<c:out value='${session.sid}'/>','<c:out value='${session.unitsForPrice}'/>','<c:out value='${session.unitPrice}'/>','<c:out value='${session.deviceId}'/>','<c:out value='${session.sessionType}'/>','<c:out value='${session.stop}'/>','<c:out value='${session.limit}'/>','<c:out value='${session.totalSessionCost}'/>','<c:out value='${session.totalDataConsumed}'/>','<c:out value='${session.totalSessionTime}'/>','<c:out value='${session.startedAt}'/>','<c:out value='${session.stoppedAt}'/>','<c:out value='${session.buyer}'/>','<c:out value='${session.seller}'/>')</script>
							</c:forEach>
							<label for="download" style="width: 250px;"><span>Download</span></label> <span>:</span> <label for="name" style="font-weight: normal;color: white;">
								<a href="#" onclick="downloadKeyStore()" style="cursor: pointer;">Keystore File</a> | <a href="#" onclick="sessionDataDownloadOption()" style="cursor: pointer;">Session Data</a> </label>
							
						</div>
						
					</div>
                </div>
            </div>
        </div>
    </div>

</section>
</body>
</html>