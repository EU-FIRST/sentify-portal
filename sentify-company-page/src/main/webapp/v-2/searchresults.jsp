<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<link rel="stylesheet" type="text/css" href="css/searchresults.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>
</head>

<body>
	<script src="js/vis-test/jquery-1.8.3.min.js"></script>
  	<script src="http://code.highcharts.com/stock/highstock.js"></script>
  	<script type="text/javascript" src="sparklines/jquery.sparkline.min.js"></script>
    			        	
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
							<h1>Search results for '<%= request.getParameter("q") %>':</h1>
							<h2>${company.name}</h2>

							<ul class="searchresults">
							<c:forEach items="${results}" var="entry">
								<li class="row">
							    	<div class="result-name"><a href="${linkmap.get(entry.name)}">${entry.name}</a></div>
							    	<c:if test="${entry.symbol.isEmpty() == false}">
							    		<div class="result-symbol">(<b>${entry.symbol}</b>)</div>
							    	</c:if>
							    	<div class="${entry.type.toLowerCase()}-tag">${entry.type.toUpperCase()}</div>
							    	
							    	<c:if test="${entry.mapping.isEmpty() == false}">
							    		<div class="result-mapping"><a href="${entry.mapping}"><img src="images/dbpedia_logo_inline.png"></a></div>
							    	</c:if>
							    	<c:if test="${entry.wikipedia.isEmpty() == false}">
							    		<div class="result-mapping"><a href="${entry.wikipedia}"><img src="images/icon-wikipedia.png"></a></div>
							    	</c:if>

							    	<div class="result-sparkline" id="sparkline1"></div>
							    	<script>$("#sparkline").sparkline([5,6,7,9,9,5,3,2,2,4,6,7], {type: 'line'});</script>
							    </li>
							</c:forEach>
							
<!-- 							<li class="row">
							<div class="company-tag">test test</div>
							<div class="capital-tag">test test</div>
							<div class="other-tag">test test</div>
							<div class="country-tag">test test</div>
							<div class="industry-tag">test test</div>
							<div class="continent-tag">test test</div>
							<div class="organisation-tag">test test</div>
							<div class="bank-tag">test test</div>
							<div class="currency-tag">test test</div>
							<div class="geographicalregion-tag">test test</div>
							<div class="insurance-tag">test test</div>
							</li>
 -->
 							</ul>
 							
 
						</div>
<!-- 						<div class="block">
							<h3>Latest News</h3>

							<div id="vaadintest-2022010684"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadDocumentList2.js"></script>

						</div> -->
					</div>
				</div>
				<div id="navigation">
					<ul class="menu" id="main-menu">
			          <li><a href="./">Home</a></li>
			          <li><a href="./company">Companies</a></li>
			          <li><a href="./country">Countries</a></li>
			          <li><a href="./stocks.jsp">Stocks</a></li>
			          <li><a href="./visualisations">Visualisations</a></li>
			          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
					</ul>
				</div>
				<div id="region-sidebar" class="region region-sidebar">
					<div class="inner">
						
						<div class="block" id="about">
							<h2>Search</h2>

							<form class="form-inline" action="search" method="get" accept-charset="utf-8">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>

						</div>
						<div class="block" id="about">
							<h2>Browse by category</h2>
							<p class="teaser">Here you can see the actual wiki page
								related to the information on this page. Wikipedia is a free,
								web-based, collaborative, multilingual encyclopedia project
								supported by the non-profit Wikimedia Foundation.</p>


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

	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>
