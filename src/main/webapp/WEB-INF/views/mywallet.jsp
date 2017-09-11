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


                    <li class="active">
                        <a href="#">My Wallet </a>
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
                    <li>
                        <a href="settings">Wallet Settings</a>
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
                <div class="panel-heading">My Wallet</div>
                <div class="panel-body">
                
	                <div class="panel-body text-center"><!--<i class="fa fa-qrcode" aria-hidden="true"></i>-->
	
	                    <img src="https://chart.googleapis.com/chart?chs=250x250&cht=qr&chl=ethereum:${currentUser.bcaddress }" alt=""
	                         style="width:200px;height:200px;"/>
	                    <br><br>
	                    <p style="color:#a6a831;font-size: 20px;font-weight: bold;"> ${currentUser.bcaddress }</p>
	
	                    <span> Current Balance </span>
	                    <h6> ETH <span style="font-family: myFirstFont;font-size: 20px;color:#fff;"> ${myBalance}  </span> </h6>
	                    <span> Token Balance </span>
	                    <h6>DTC <span style="font-family: myFirstFont;font-size: 20px;color:#fff;"> ${currentUser.tokenBalance }  </span></h6>
	                </div>
                </div>
            </div>
        </div>
    </div>

</section>
</body>
</html>