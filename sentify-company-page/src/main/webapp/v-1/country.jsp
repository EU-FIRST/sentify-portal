<!DOCTYPE html>

<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="stylesheet" type="text/css" href="css/html.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/backgrounds.css" />
<link rel="stylesheet" type="text/css" href="css/pages.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
<link rel="stylesheet" type="text/css" href="css/blocks.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/inline.css" />
<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>
</head>

<body>
	<div id="page-wrapper" class="page-wrapper">
		<div id="header" class="header">
			<div id="header-inner">
				<a href="" id="logo"> First Project </a> <a href="#main-menu"
					id="main-menu-link"> Menu </a>
			</div>
		</div>
		<div id="main" class="main clearfix">
			<div class="main-inner">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block graphic">
							<h2 style="float:left;">${country.name}</h2> <a href="${country.mapping}" target="_new"><img alt="DBPedia" src="images/dbpedia_logo.png" style="width: 58px; height: 36px; margin-left: 20px;"></a>
							<!-- 
							<div class="btn-group">
								<button class="btn">1 day</button>
								<button class="btn">7 days</button>
								<button class="btn">14 days</button>
								<button class="btn btn-primary">30 days</button>
							</div>
							 -->

							<!-- 				<div id="resumepagesentimentscompanygraph-1327946193" class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication">
					<img src="images/loading.gif">
				</div>            
				<script type="text/javascript" src="loadResumePageCompanyGraph.js"></script>
 -->
						</div>
						<div class="block">
							<h3>Latest News</h3>
<!-- 							<div id="vaadintest-2022010683"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="loadVaadinTest.js"></script>  -->
							<div id="vaadintest-2022010684"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadDocumentList2.js"></script>

							<!-- <div id="resumepagesentimentscompanydocs-458386776"
								class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication">
								<img src="images/loading.gif">
							</div>

							<script type="text/javascript" src="loadDocumentList.js"></script> -->


							<!-- 
            <ul class="news">
              <li class="row up">
                <h3 class=""><a href="">Should You BUY Facebook Now?</a></h3>
                <span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span> </li>
              <li class="row down"><a href="">
                <h3>DBJ Tech Watch for Friday: SpaceX makes history, Facebook, Apple, Google</h3>
                </a> <span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span> </li>
              <li class="row up"><a href="">
                <h3>Here’s How Pandora is Schooling Facebook</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
              <li class="row down"><a href="">
                <h3>Apple Is Going To Start Giving Away One Free App Every Week</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
              <li class="row up"><a href="">
                <h3>2 Things You Need to Know About Mutual Funds</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
              <li class="row"><a href="">
                <h3>Facebook falls 3.5% in mild tech action</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
              <li class="row"><a href="">
                <h3>Apple CEO Cook gives up \$75M in stock dividends</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
              <li class="row"><a href="">
                <h3>Apple Is Going To Start Giving Away</h3>
                </a><span class="meta">source: New York Times, date: Fri, 11:51AM EDT</span></li>
            </ul>
             -->
						</div>
					</div>
				</div>
				<div id="navigation">
					<ul class="menu" id="main-menu">
			          <li><a href="./">Home</a></li>
			          <li><a href="./company">Companies</a></li>
					  <li><a href="./country" class="active">Countries</a></li>			          
			          <li><a href="./stocks.jsp">Stocks</a></li>
			          <li><a href="./visualisations">Visualisations</a></li>
			          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
					</ul>
				</div>
				<div id="region-sidebar" class="region region-sidebar">
					<div class="inner">
						<!-- 
						<div class="block analysis">
							<h2>30-days Aggregated Sentiment</h2>
							<div id="resumepagesentimentscompanysentiment-1390475342"
								class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication"></div>

							<script type="text/javascript"
								src="loadCompanyAggregatedSentiment.js"></script>

						</div>
						 -->
						<div class="block" id="about">
							<h2>Country Search</h2>
							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>
							
							<p class="teaser"> Try with Greece
 							</p>
						</div>
						<div class="block" id="about">
							<h2>Country resumé</h2>
							<p class="teaser">Here you can see the actual wiki page
								related to the information on this page. Wikipedia is a free,
								web-based, collaborative, multilingual encyclopedia project
								supported by the non-profit Wikimedia Foundation.</p>

							<iframe width="100%" height="600px" frameborder="0"
								allowtransparency="true"
								src="${country.wikipedia}" name="PID5"
								style="overflow: hidden;"></iframe>
						</div>

					</div>
				</div>
			</div>
			<div id="footer">Footer disclaimer</div>
		</div>
		<!-- main -->
	</div>
	<iframe tabIndex="-1" id="__gwt_historyFrame"
		style="position: absolute; width: 0; height: 0; border: 0; overflow: hidden;"
		src="javascript:false"></iframe>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
