<!DOCTYPE html>

<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="stylesheet" type="text/css" href="css/html.css" />
<link rel="stylesheet" type="text/css" href="css/layout-v2.css" />
<link rel="stylesheet" type="text/css" href="css/backgrounds.css" />
<link rel="stylesheet" type="text/css" href="css/pages.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
<link rel="stylesheet" type="text/css" href="css/blocks.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/inline.css" />
<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/document-drilldown.css" />
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
			<div class="main-inner" style="width: 100%">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block">
							<h2>Document Drilldown</h2>
							<!-- <div id="vaadintest-2022010683"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="loadVaadinTest.js"></script> -->
							
							<!-- <div id="resumepagesentimentscompanydocs-458386776"
								class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication">
								<img src="images/loading.gif">
							</div>

							<script type="text/javascript" src="loadDocumentList.js"></script> -->

							<div class="document-drilldown">
								<div class="news-title">${news.title}</div>
								<div class="publication-date">${news.publicationdate}</div>
	
								<div>${html}</div>
								
								<div class="news-source">source: ${news.url}</div>
							</div>

						</div>
					</div>
				</div>
				<div id="navigation">
					<ul class="menu" id="main-menu">
			          <li><a href="./">Home</a></li>
			          <li><a href="./company" class="active">Companies</a></li>
			          <li><a href="./country">Countries</a></li>
			          <li><a href="./stocks.jsp">Stocks</a></li>
			          <li><a href="./visualisations">Visualisations</a></li>
			          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
					</ul>
				</div>
				<div id="region-sidebar" class="region">
					<div class="inner">
						<div class="block region-sidebar">

							<div id="drilldown-2022010684"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadDrillDown.js"></script>
							
							
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
