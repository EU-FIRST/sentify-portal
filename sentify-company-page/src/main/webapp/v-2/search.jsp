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
							<h3>Sentiment object search results for '<%= request.getParameter("q") %>':</h3>
							<h2>${company.name}</h2>

 
 							<script src="js/vis-test/jquery-1.8.3.min.js"></script>
    			        	<script src="http://code.highcharts.com/stock/highstock.js"></script>
							<div id="container" style="height: 300px; min-width: 500px"  data-step="3" data-intro="Here we have some twitter chart."></div>
							<script type="text/javascript">

							
							(function($){ // encapsulate jQuery

								
								$(function () {
									
									
									
									var names = [{'url': '${company.URIFragment}', 'name': '${company.name} sentiment'}],
											//, 'comp_APPLE_INC', 'comp_AMAZON_COM_INC'],
										colors = Highcharts.getOptions().colors,
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
									
									seriesOptions[names.length] = {
											name: "Volume",
											data: [],
											color: colors[names.length],
											//showInLegend: false,
											marker: {
								                	enabled: false
								            },
								            type: 'area',
											yAxis: 1
										};
									
									$.each(names, function(i, name) {

										$.getJSON('./timeserie?so='+ name.url +'&enddate='+todayDate+'&callback=?',	function(data) {

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
									
									
									$.getJSON('./timeserievolume?so=${company.URIFragment}&enddate='+todayDate+'&callback=?',	function(data) {
										var hc = $('#container').highcharts();
										
										var max = 0;
										var maximum = Math.max.apply(Math, data.map(function(array) {
											return array[1];											
										}));
										
										hc.series[names.length].setData(data);
										//hc.yAxis[1].max = maximum * 2;
										hc.yAxis[1].update({max: maximum * 2})
										console.info(hc.yAxis[1].max);
										console.info("Loaded.");
										console.info(maximum);
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
								                
								                marginRight: 70,
								                marginLeft: 50
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
								            
								            yAxis: [{
								                title: {
								                    text: 'Sentiment'
								                },
								                plotLines: [{
								                    value: 0,
								                    width: 1,
								                    //color: '#808080'
								                }],
								                min: -1, 
								                max: 1
								            }, {
								            	title: {
								                    text: 'Volume'
								                },
								                plotLines: [{
								                    value: 0,
								                    width: 1,
								                    //color: '#808080'
								                }],
								                min: 0, 
								                max: 1000,
								                opposite: true

								            }],
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
								                x: -40,
								                y: -5,
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
						<div class="block">
							<h3>Latest News</h3>
							<!-- <div id="vaadintest-2022010683"
								class=" v-app v-app-VaadinServiceSession">
								<div class=" v-app-loading"></div>
								<noscript>You have to enable javascript in your
									browser to use an application built with Vaadin.</noscript>
							</div>
							<script type="text/javascript" src="loadVaadinTest.js"></script> -->
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

							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>

						</div>
						<div class="block" id="about">
							<h2>Quick resumé</h2>
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
