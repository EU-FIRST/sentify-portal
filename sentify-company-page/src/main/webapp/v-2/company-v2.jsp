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
<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>

<script language='javascript' src='js/d3.v2.js'></script>

    <script type="text/javascript" src="introjs/intro.js"></script>
    <link href="introjs/introjs.css" rel="stylesheet">
<style>


.axis path,
.axis line {
  fill: none;
  stroke: #aaa;
  shape-rendering: crispEdges;
}

.x.axis path {
  /* display: none; */
}

.axis-label {
  fill: gray;
}

.line,
.line-shadow {
  fill: none;
  stroke: steelblue;
  stroke-width: 1.5px;
}

.line-shadow {
  stroke-opacity: 0.4
}
.chart-text {
  color: #aaa;
  font-family: arial, helvetica, sans-serif;
  font-size: 100%;
  line-height: 1.4em;
}

.area {
  fill: steelblue;
  opacity: 0.5;
  stroke-opacity: 1;
}

</style>

</head>

<body>
	<div id="page-wrapper" class="page-wrapper">
		<div id="header" class="header">
			<div id="header-inner">
				<a href="" id="logo" data-step="1" data-intro="Hello all! :) This project's called Intro.js."> <img src="images/logo_head.png"> </a> <a href="#main-menu"
					id="main-menu-link"> Menu </a>
			</div>
		</div>
		<div id="main" class="main clearfix">
			<div class="main-inner">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block">
						<script src="js/vis-test/jquery-1.8.3.min.js"></script>
            	<script src="http://code.highcharts.com/stock/highstock.js"></script>
				<!-- <script src="http://code.highcharts.com/stock/modules/exporting.js"></script>  -->
				
				<div id="container" style="height: 300px; min-width: 500px"  data-step="3" data-intro="Here we have some twitter chart."></div>
				<script type="text/javascript">

</script>
						</div>
	
						<div class="block" data-step="4" data-intro="Here are some news" data-position="top">
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


						</div>
					</div>
				</div>
				<div id="navigation" >
					<ul class="menu" id="main-menu" >
			          <li><a href="./">Home</a></li>
			          <li><a href="./company" class="active">Companies</a></li>
			          <li><a href="./country" data-step="4" data-intro="Here are some news" data-position="top">Countries</a></li>
			          <li><a href="./stocks.jsp">Stocks</a></li>
			          <li><a href="./visualisations">Visualisations</a></li>
			          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
<li><a href="javascript:void(0);" onclick="javascript:introJs().start();">Tutorial</a></li>			          
					</ul>
				</div>
				<div id="region-sidebar" class="region">
					<div class="inner">
						<div class="block">
						
						<div id="container-twitter" style="height: 300px; min-width: 500px"  data-step="3" data-intro="Here we have some twitter chart."></div>
				<script type="text/javascript">

				(function($){ // encapsulate jQuery

					
					$(function () {
						var names = [{'url': 'comp_GOOGLE_INC', 'name': 'Google'},
							{'url': 'comp_MICROSOFT_CORP', 'name': 'Microsoft'}],
								//, 'comp_APPLE_INC', 'comp_AMAZON_COM_INC'],
							colors = colors = Highcharts.getOptions().colors,
							seriesCounter = 0,
							seriesOptions = [],
							yAxisOptions = [];
						
						colors [0] = '#1f77b4';
						colors [1] = '#ff7f0e';
						colors [2] = '#2ca02c';
						colors [3] = '#d62728';

						var d = new Date();
						var curr_date = d.getDate();
						var curr_month = d.getMonth() + 1;
						var curr_year = d.getFullYear();
						
						var todayDate = curr_year + '-' + curr_month + '-' + curr_date;
						
						$.each(names, function(i, name) {

							$.getJSON('http://localhost:8080/sentify-portal/timeserie?so='+ name.url +'&startdate=1234&enddate='+todayDate+'&callback=?',	function(data) {

								seriesOptions[i] = {
									name: name.name,
									data: data,
									color: colors[seriesCounter],
									//showInLegend: false,
									marker: {
						                	enabled: false
						            },
						            type: 'spline'
									
								};

								// As we're loading the data asynchronously, we don't know what order it will arrive. So
								// we keep a counter and create the chart when all the data is loaded.
								seriesCounter++;

								if (seriesCounter == names.length) {
									createChart();
								}
							});
						});
						
						
						function createChart() {
						
						
					        $('#container').highcharts({
					        	
					        	credits: {
					        		enabled: false
					        	},
					            chart: {
					                type: 'line',
					                //marginRight: 130,
					                marginBottom: 25,
					                backgroundColor: '#f3f3f3', 
					                
					                marginRight: 25
					            },
					            title: {
					                text: 'News and blogs trend',
					                x: -20, //center
					                style: {
					                    fontSize: '14px',
					                    fontFamily: 'Arial, sans-serif',
					                    fontWeight: 'bold'
					                }
					            },
				                subtitle: {
					                text: 'aggregated daily sentiment',
					                x: -20
					            },
					            
					            xAxis: {
					            	type: 'datetime'
					            },
					            
					            yAxis: {
					                title: {
					                    text: 'Sentiment'
					                },
					                plotLines: [{
					                    value: 0,
					                    width: 1,
					                    color: '#808080'
					                }],
					                min: -1, 
					                max: 1
					            },
					            tooltip: {
					                valueSuffix: ''
					            },
					            series: seriesOptions,
					            tooltip: {
							    	pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',
							    	valueDecimals: 2,
							    	crosshairs: true,
							    	shared: true
							    },
							    legend: {
					                layout: 'vertical',
					                align: 'right',
					                verticalAlign: 'top',
					                x: -10,
					                y: 10,
					                borderWidth: 0,
					                style: {
					                    fontSize: '13px',
					                    fontFamily: 'Arial, sans-serif'
					                }
					            },
					        });
						}
					    });
					    

					})(jQuery);
</script>
						</div>
						
						
						
						</div>
						<div class="block analysis">
							<h2>30-days Aggregated Sentiment</h2>
							<!-- <div id="resumepagesentimentscompanysentiment-1390475342"
								class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication"></div>

							<script type="text/javascript"
								src="loadCompanyAggregatedSentiment.js"></script> -->

						</div>
						<div class="block" id="about"  data-step="4" data-intro="Here you have some search">
							<h2>Search Companies</h2>
							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>
							
							<p class="teaser"> Try with Microsoft
 							</p>
						</div>
						<div class="block" id="about">
							<h2>Company resumé</h2>
							<p class="teaser">Here you can see the actual wiki page
								related to the information on this page. Wikipedia is a free,
								web-based, collaborative, multilingual encyclopedia project
								supported by the non-profit Wikimedia Foundation.</p>

							<iframe width="100%" height="600px" frameborder="0"
								allowtransparency="true"
								src="${company.wikipedia}" name="PID5"
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
