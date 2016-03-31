<!DOCTYPE>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<title>Home Page</title>
	
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="bower_component/bootstrap/dist/css/bootstrap.min.css" type="text/css">

    <!-- Custom Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="bower_component/font-awesome/css/font-awesome.min.css" type="text/css">

    <!-- Plugin CSS -->
    <link rel="stylesheet" href="bower_component/animate-css/animate.min.css" type="text/css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/creative.css" type="text/css">
    <link rel="stylesheet" href="css/loginCustomize.css" type="text/css">
	
		
	<script src="bower_component/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="bower_component/angular/angular.min.js"></script>
	<script type="text/javascript" src="bower_component/angular-messages/angular-messages.min.js"></script>
	

	<script>
	var app = angular.module('mainModule', []);
	app.filter('capitalize', function() {
	    return function(input) {
	      return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
	    }
	});
	</script>

</head>

<body>
	<c:import url="page_component/header.jsp"/>	
	
	<section id="page-top" class="index">	
	<div class="intro-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message" ng-controller="headerCtrl">
                        <h1>Hello, {{user.userName|capitalize}}</h1>
                        <h3>Welcome to Yahoo Finance Trading System</h3>
                        
                        <a href="marketdata" class="btn btn-primary btn-xl page-scoll">Go Explore it!</a>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->
    </div>
	</section>
	
	<section class="bg-primary" id="about">
		<div id="tv-miniwidget-dfa0f" class="markets_chart">
			<!-- <div id="tf-market" class="markets_chart">-->
			<div id="tradingview_690a3-wrapper" style="position: relative; box-sizing: content-box; width: 100%; height: 530px; margin: 0 !important; padding: 0 !important; font-family: Arial, sans-serif;">
				
				<div style="width: 100%; height: 530px; background: #fff; padding: 0 !important;">
					<iframe id="tradingview_690a3"
						src="https://dwq4do82y8xi7.cloudfront.net/miniwidgetembed/?Equities=BABA,DIA,SPY,TWITTER,FACEBOOK,GOOGLE,MICROSOFT,ORACLE&amp;Commodities=GOLD,SILVER,OIL,GAS,BASE%20METALS,URANIUM,LITHIUM,WHEAT&amp;Bonds=1-3%20YEAR,3-7%20YEAR,7-10%20YEAR,20%20YEAR,SHORT%20TERM%20US,EURO%20DEBT,GERMANY%20BOND,ASIA%20LOCAL%20DEBT&amp;Forex=FX%3AEURUSD,FX%3AGBPUSD,FX%3AUSDJPY,FX%3AUSDCHF,FX%3AAUDUSD,FX%3AUSDCAD,FX%3ANZDUSD,FX%3AEURJPY&amp;tabs=Equities%2CCommodities%2CBonds%2CForex&amp;QQQ=NASDAQ%3AQQQ&amp;DIA=AMEX%3ADIA&amp;SPY=AMEX%3ASPY&amp;TWITTER=NYSE%3ATWTR&amp;FACEBOOK=NASDAQ%3AFB&amp;GOOGLE=NASDAQ%3AGOOG&amp;MICROSOFT=NASDAQ%3AMSFT&amp;ORACLE=NYSE%3AORCL&amp;GOLD=AMEX%3AGLD&amp;SILVER=AMEX%3ASLV&amp;OIL=AMEX%3AUSO&amp;GAS=AMEX%3AUNG&amp;BASE%20METALS=AMEX%3ADBB&amp;URANIUM=AMEX%3AURA&amp;LITHIUM=AMEX%3ALIT&amp;WHEAT=AMEX%3AWEAT&amp;1-3%20YEAR=AMEX%3ASHY&amp;3-7%20YEAR=AMEX%3AIEI&amp;7-10%20YEAR=AMEX%3AIEF&amp;20%20YEAR=AMEX%3ATLT&amp;SHORT%20TERM%20US=AMEX%3ASCHO&amp;EURO%20DEBT=AMEX%3AEU&amp;GERMANY%20BOND=FX%3ABUND&amp;ASIA%20LOCAL%20DEBT=AMEX%3AALD&amp;timeAxisBackgroundColor=%23F3F2F3&amp;activeTickerBackgroundColor=%23EDF0F3&amp;trendLineColor=%23FF7965&amp;underLineColor=%23F0F0F0&amp;fontColor=%2383888D&amp;gridLineColor=%23E9E9EA&amp;large_chart_url=http%3A%2F%2Fwww.investopedia.com%2Fmarkets%2Fsymbol-redirect.aspx&amp;width=100%25&amp;height=530px&amp;utmsource=www.investopedia.com&amp;utmmedium=www.investopedia.com/"
						width="100%" height="530px" frameborder="0"
						allowtransparency="true" scrolling="no"
						style="margin: 0 !important; padding: 0 !important;">
					</iframe>
				</div>
				
				<div style="position: absolute; display: block; box-sizing: content-box; height: 24px; width: 100%; bottom: 0; left: 0; margin: 0; padding: 0; font-family: Arial, sans-serif;">
					<div
						style="display: block; margin: 0 1px 1px 1px; line-height: 7px; box-sizing: content-box; height: 11px; padding: 6px 10px; text-align: right; background: #fff;">
						<a
							href="https://www.tradingview.com/?utmsource=www.investopedia.com&amp;utmmedium=www.investopedia.com/"
							target="_blank"
							style="color: #0099d4; text-decoration: none; font-size: 11px;">
						</a>
					</div>
				</div>
				
			</div>
		</div>
		<script type="text/javascript" src="https://d33t3vvu2t2yu5.cloudfront.net/tv.js"></script>
	</section>
	
	<c:import url="page_component/footer.jsp"/>
	
	<!-- jQuery -->
    <script src="bower_component/jquery/dist/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_component/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="bower_component/jquery.easing/js/jquery.easing.min.js"></script>
    <script src="bower_component/FitText.js/jquery.fittext.js"></script>
    <script src="bower_component/wow/dist/wow.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/creative.js"></script>
    <script src="js/loginCustomize.js"></script>
	

</body>
</html>