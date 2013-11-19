<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="icon" type="image/png" href="images/favicon.png">

<link rel="stylesheet" href="jquery/jquery-ui.min.css" />
<script src="jquery/jquery-1.10.2.min.js"></script>
<script src="jquery/jquery-ui.min.js"></script>
<script src="js/highstock/highstock.js"></script>

<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/fixes.css" />

<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/newslist.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/trending-topics.css" />

<link rel="stylesheet" type="text/css" href="css/date-form.css" />

<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>

</head>

<body class="">
	<script>
	//initialisation variables.
	var params = {
		"so": "${so}",
		"from-date" : "${fromDate}",
		"to-date": "${toDate}",
		"topic": "${topic}",
		"companyName": "${company.name}",
		"sentiment": "reputation",
		"newslist-app": "news-list-crisp-2022010684"
	};
	
	</script>
	<div id="page-wrapper" class="page-wrapper">

		<div id="header" class="header">

			<div id="header-inner">

				<a href="" id="logo"> First Project </a>
				
				<h1 class="page-title">Companies Reputation - Sentify Portal </h1>
				
				<a href="#main-menu" id="main-menu-link"> Menu </a>
			</div>

		</div>
		<div id="main" class="main clearfix">
			<div class="main-inner">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block">
		
						
		
							<h2>${company.name}</h2>
							<h3>Reputation topics</h3>
							
							<div class="reputation-selector">
							<div class="btn-group reputation-selector" data-toggle="buttons-radio" style="display: inline">
								<button class="btn btn-small" name="reputation" value="bb" id="btn-reputation-bb">Business Behavior</button>
								<button class="btn btn-small" name="reputation" value="sr" id="btn-reputation-sr">Social Responsibility</button>
								<button class="btn btn-small" name="reputation" value="ecm" id="btn-reputation-ecm">Exposure on Critical Markets</button>
								<button class="btn btn-small" name="reputation" value="hr"  id="btn-reputation-hr">Human Resources</button>
								<button class="btn btn-small" name="reputation" value="cg"  id="btn-reputation-cg">Corporate Governance</button>
							</div>						
							</div>
							
							
							<div id="container" style="height: 300px; min-width: 600px"  data-step="3" data-intro="Here we have some twitter chart."></div>

						</div>	
						<div class="block">
							<h2 style="display: inline">Latest News</i></h2>  
							<!-- <i class="icon-time"> -->
							<!-- 
							<div class="btn-group news-selector" data-toggle="buttons-radio" style="display: inline; vertical-align: 7px; padding-left: 20px; float: right;">
								<button class="btn btn-mini btn-warning active" name="news" value="7" id="btn-news-7">7 days</button>
								<button class="btn btn-mini" name="news" value="30" id="btn-news-30">30 days</button>
							</div>
							 -->
							 
							<div id="news-list-crisp-2022010684" class="v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadDocumentList-Crisp.js"></script>
							
						</div>					
				
					</div>
				</div>
				<div id="navigation">
					<div id="main-menu">
						<ul class="menu">
							<li><a href="/">Home</a>
								</li>
							<li class="active"><a href="/company?so=${so}">Company Reputation</a>
								<ul>
									<li><a href="/company?so=${so}">Crisp Sentiment</a></li>
									<li><a href="/company-fuzzy?so=${so}">Fuzzy Sentiment</a></li>
									<li class="active"><a href="/company-reputation?so=${so}">Reputation Topics</a></li>
								</ul></li>
							<li><a href="/stocks?so=${so}">Stock Price</a>
								<ul>
									<li><a href="/stocks?so=${so}">Crisp Sentiment</a></li>
									<li><a href="/stocks-fuzzy?so=${so}">Fuzzy Sentiment</a></li>
									<!--<li><a href="/stocks-twitter?so=${so}">Twitter</a></li>-->
								</ul></li>
							<li><a href="/world">Regions</a>
								<ul>
									<li><a href="/world">World</a></li>
									<li><a href="/countries">Crisp Sentiment</a></li>
									<li><a href="/countries-fuzzy">Fuzzy Sentiment</a></li>
								</ul></li>
							<!-- <li><a href="/stocks">Currencies</a>
								<ul>
									<li><a href="/stocks">Crisp Sentiment</a></li>
									<li><a href="/stocks-fuzzy">Fuzzy Sentiment</a></li>
									<li><a href="/stocks-twitter">Twitter</a></li>
								</ul></li> -->
							<li><a href="/other">Other</a>
								<ul>
									<li><a href="/other">Crisp Sentiment</a></li>
									<li><a href="/other-fuzzy">Fuzzy Sentiment</a></li>
								</ul></li>
						</ul>			
					</div>
				</div>

				<div id="region-sidebar" class="region region-sidebar">
					<div class="inner">
						
						
						<div class="block analysis">
			            <h2>
							<div id="documents-analyzed-widget-2022010683"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadAnalyzedDocuments.js"></script>
			            </h2>
			            <p>Documents available in the knowledge base</p>
			          	</div>
          
          				<div class="block">
							<h2>Date selection</h2>
							<p>
								From: <input type="text" class="datepicker" name="from-date" />
								To: <input type="text" class="datepicker" name="to-date" />
								<button type="submit" class="btn" id="date-accept-btn">Go!</button>
							</p>
						</div>
          
						<div class="block" id="about">
				            <h2>Search Companies</h2>
							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>
							<p>Not sure what to do? Try with <a href="./search?q=microsoft">Microsoft</a>, <a href="./search?q=google">Google</a> or <a href="./search?q=barclays">Barclays</a>!</p>
			 		    </div>		

						<div class="block" id="about">
				            <h2>About Reputation Topics</h2>

							<p>
							This page presents sentiment related only to expected reputation change indicator.
							It further allows to explore 5 reputation topics (features):
							Business Behavior,  Social Responsibility, Exposure on Critical Markets,  Human Resources and Corporate Governance.
							The sentiment presented on this page is the crisp sentiment on the document level.
							</p>
			 		    </div>		
					</div>
				</div>
			</div>
			<div id="footer">Footer disclaimer</div>
		</div>
		<!-- main -->
	</div>
	<iframe tabIndex="-1" id="__gwt_historyFrame" style="position:absolute;width:0;height:0;border:0;overflow:hidden;" src="javascript:false"></iframe>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
	<!-- <script src="js/script.js"></script>
	<script src="js/reputation-select.js"></script>
	<script src="js/chart-script.js"></script>
	<script src="js/url-controller.js"></script> -->
	<script src="js/app-script.js"></script>
</body>
</html>
