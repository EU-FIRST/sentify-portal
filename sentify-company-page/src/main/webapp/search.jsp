
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="icon" type="image/png" href="images/favicon.png">


<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="js/script.js"></script>


<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/inline.css" />

<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/newslist.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/trending-topics.css" />

<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>

</head>

<body class="">

	<div id="page-wrapper" class="page-wrapper">

		<div id="header" class="header">

			<div id="header-inner">

				<a href="" id="logo"> First Project </a>
				 
				<h1 class="page-title">Search - Sentify Portal</h1>
				
				<a href="#main-menu" id="main-menu-link"> Menu </a>
			</div>

		</div>
		<div id="main" class="main clearfix">
			<div class="main-inner">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block">
							<h2>Latest News</h2>
							<div id="vaadintest-2022010684" class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="widgetloaders/loadDocumentList2.js"></script>
							
						</div>					
					</div>
				</div>
				<div id="navigation">
					<div id="main-menu">
						<ul class="menu">
							<li class="active"><a href="index.php" class="active">Home</a>
								</li>
							<li><a href="/company">Companies</a>
								<ul>
									<li><a href="/company">Crisp Sentiment</a></li>
									<li><a href="/company-fuzzy">Fuzzy Sentiment</a></li>
									<li><a href="/company-reputation">Reputation</a></li>
								</ul></li>
							<li><a href="/stocks">Stocks</a>
								<ul>
									<li><a href="/stocks">Crisp Sentiment</a></li>
									<li><a href="/stocks-fuzzy">Fuzzy Sentiment</a></li>
								</ul></li>
							<li><a href="/countries">Regions</a>
								<ul>
									<li><a href="/countries">Crisp Sentiment</a></li>
									<li><a href="/countries-fuzzy">Fuzzy Sentiment</a></li>
								</ul></li>
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
          
						<div class="block" id="about">
				            <h2>Search Companies</h2>
							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>
							<p>Not sure what to do? Try with <a href="./search?q=microsoft">Microsoft</a>, <a href="./search?q=microsoft">Google</a> or <a href="./search?q=microsoft">Barclays</a>!</p>
			 		    </div>		
			 		    				
						<div class="block" id="about">
							<h2>About FIRST</h2>
							<p>
								Date: <input type="text" class="datepicker" />
							</p>
							<p class="teaser">FIRST develops and provides a large scale information extraction and integration infrastructure which will assist in
								various ways during the process of financial decision making.</p>
							<ul class="links">
								<li class="read_more"><a href="">Read More</a></li>
							</ul>

							</ul>
						</div>
						<div class="block">
							<h2>List of Monitored Sources</h2>
							<ul>
								<li>Lorem ipsum d<strong>olor sit ame</strong>t, consectetur adipiscing elit.
								</li>
								<li>Praesent eget erat a ipsum sagittis molestie vel sit amet est.</li>
								<li>Vestibulum ac mass<strong>a a urna luctus </strong>aliquet.
								</li>
								<li>Ut eu<em>ismod lectus iaculis mag</em>na mollis luctus.
								</li>
								<li>Nulla eu sem risus, et pretium libero.</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div id="footer">Footer disclaimer</div>
		</div>
		<!-- main -->
	</div>
	<iframe tabIndex="-1" id="__gwt_historyFrame" style="position:absolute;width:0;height:0;border:0;overflow:hidden;" src="javascript:false"></iframe>
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>
