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

<style>
#vis2 {
	background-image: url('images/spinner.gif');
	background-position: center;
	background-repeat: no-repeat;
}

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
  opacity: 0.3;
  stroke-opacity: 1;
}

</style>

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
							<h2>${company.name}</h2>
							<!-- <div class="btn-group">
								<button class="btn">1 day</button>
								<button class="btn">7 days</button>
								<button class="btn">14 days</button>
								<button class="btn btn-primary">30 days</button>
							</div> -->
			


							<!-- 				<div id="resumepagesentimentscompanygraph-1327946193" class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication">
					<img src="images/loading.gif">
				</div>            
				<script type="text/javascript" src="loadResumePageCompanyGraph.js"></script>
 -->
						</div>
						
						<div class="block">
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
			          <li><a href="./company" class="active">Companies</a></li>
			          <li><a href="./country">Countries</a></li>
			          <li><a href="./stocks.jsp">Stocks</a></li>
			          <li><a href="./visualisations">Visualisations</a></li>
			          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
					</ul>
				</div>
				<div id="region-sidebar" class="region region-sidebar">
					<div class="inner">
						<div class="analysis" style="margin: 10px 0;">
						<h2>Twitter statements volume</h2>
										<div id="vis" style="height: 370px; margin-left: -20px;">
			<script> 
			
			function dateformat(d) {
				var curr_date = d.getDate();
				var curr_month = d.getMonth() + 1; //Months are zero based
				var curr_year = d.getFullYear();
				var today = curr_year + "-" + curr_month + "-" + curr_date;
				return today;
			}

			var today=new Date();

			var oneWeek=new Date();
			oneWeek.setDate(today.getDate() - 7);
			var twoWeeks=new Date();
			twoWeeks.setDate(today.getDate() - 14);
			var oneMonth=new Date();
			oneMonth.setDate(today.getDate() - 30);
			var oneQuarter=new Date();
			oneQuarter.setDate(today.getDate() - 120);

			var toDate = dateformat(today);
			var fromDate = dateformat(oneQuarter);

			var margin = {top: 0, right: 80, bottom: 30, left: 45},
			    width = 560 - margin.left - margin.right,
			    height = 330 - margin.top - margin.bottom;

			var parseDate = d3.time.format("%Y-%m-%d").parse;

			var x = d3.time.scale()
			    .range([0, width]);

			var y = d3.scale.linear()
			    .range([height, 0]);

			var color = d3.scale.category10();

			var xAxis = d3.svg.axis()
			    .scale(x)
			    .ticks(6)
			    .orient("bottom")
			    ;

			var yAxis = d3.svg.axis()
			    .scale(y)
			    .orient("left");

			var line = d3.svg.line()
			    //.interpolate("cardinal")
			    .x(function(d) { return x(d.date); })
			    .y(function(d) { return y(d.temperature); });


			var area = d3.svg.area()
				//.interpolate("cardinal")
			    .x(function(d) { return x(d.date); })
			    .y0(function(d) { return y(0); })
			    .y1(function(d) { return y(d.temperature); });

			var svg = d3.select("div#vis")
				.style("background-image", "url('images/spinner.gif')")
				.style("background-repeat", "no-repeat")
				.style("background-position", "center")
				.append("svg")
			    .attr("width", width + margin.left + margin.right)
			    .attr("height", height + margin.top + margin.bottom)
			    
			  .append("g")
			    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			var filter = svg.append("svg:defs")
			.append("svg:filter")
			.attr("id", "blur");

			filter
			.append("svg:feGaussianBlur")
			  .attr("in", "SourceAlpha")
			  .attr("stdDeviation", 5)
			  ;
			filter
			  .append("svg:feOffset")
			  .attr("dx", 2)
			  .attr("dy", 2)
			  .attr("result", "offsetBlur");

			//d3.json("./GetSentimentTimeline?from="+fromDate+"&to="+toDate+"&stock=$" +"${company.symbol}", function(data) {
			d3.json("./GetSentimentTimeline?days=120&stock=$" +"${company.symbol}", function(data) {
					
			  color.domain(d3.keys(data[0].v).filter(function(key) { return key !== "d"; }));

			  data.forEach(function(d) {
			    d.date = parseDate(d.d);
			  });

			  var cities = color.domain().map(function(name) {
			    return {
			      name: name,
			      values: data.map(function(d) {
			    	  console.info(name)
			    	  if (name=="p") {
			    		  return {date: d.date, temperature: +d.v[name]+d.v["pn"]};
			    	  } 
			    	  if (name=="pn") {
			    		  return {date: d.date, temperature: d.v[name]};
			    	  } 
			    	  if (name=="nn") {
			    		  return {date: d.date, temperature: -d.v[name]};
			    	  } 
			    	  if (name=="n") {
			    		  return {date: d.date, temperature: -d.v[name]-d.v["nn"]};  
			    	  }
			      })
			    };
			  });

			  x.domain(d3.extent(data, function(d) { return d.date; }));

			  y.domain([
			    d3.min(cities, function(c) { return d3.min(c.values, function(v) { return 1.2 * v.temperature; }); }),
			    d3.max(cities, function(c) { return d3.max(c.values, function(v) { return 1.2 * v.temperature; }); })
			  ]);

			  //x axis and labels
			  svg.append("g")
			      .attr("class", "x axis")
			      .attr("transform", "translate(0," + height + ")")      
			      .call(xAxis)
			      .attr("fill","#444")
			      
			      ;
			  
			  //y axis and labels
			  svg.append("g")
			      .attr("class", "y axis")
			      .call(yAxis)
			            //.attr("class","axis-label")
			      .attr("fill","#444")
			      
			    .append("text")
			      .attr("transform", "rotate(-90)")
			      .attr("y", 6)
			      .attr("dy", ".71em")
			      .style("text-anchor", "end")
			      .text("Volume");

			  var city = svg.selectAll(".city")
			      .data(cities)
			      .enter().append("g")
			      .attr("class", "city");

			  city.append("path")
			      .attr("class", "area")
			      .attr("d", function(d) { console.info(d); return area(d.values); })
			      .style("stroke", function(d) { return color(d.name); })
			  	  .style("fill", function(d) { return color(d.name); });

			  city.append("path")
			      .attr("class", "line-shadow")
			      .attr("d", function(d) { console.info(d); return line(d.values); })
			      .style("stroke", function(d) { return color(d.name); })
			      .attr("filter", "url(#blur)");
			  
			  city.append("path")
			  .attr("class", "line")
			  .attr("d", function(d) { console.info(d); return line(d.values); })
			  .style("stroke", function(d) { return color(d.name); })


			  city.append("svg:circle") // Append circle elements
			    .attr("cx", 50)
			    .attr("cy", function(d, i) { return 20 + i*20; } )
			    .attr("stroke-width", ".5")
			    .style("fill", function(d) { return color(d.name); }) // Bar fill color
			    .attr("r", 5)
			    .attr("color_value", function(d) { return color(d.name); }); // Bar fill color...
			  
			  city.append("svg:a")
			  		.attr("xlink:href", function(d) { return d.link; })
			  		.append("text")
			        .attr("text-anchor", "left")
			        .attr("x", 60)
			        .attr("y", function(d, i) { return 20 + i*20 - 10; })
			        .attr("dx", 0)
			        .attr("dy", "1em") // Controls padding to place text above bars
			        .text(function(d) { return d.name;})
			        .style("fill", "Gray")
			        .attr("index_value", function(d, i) { return "index-" + i; });
			 
			  d3.select("div#vis")
				.style("background-image", "url('')");      
			    
			  //document.getElementById("vis").style['backgroundImage']="url('images/spinner.gif')";
			  
			//  city.append("text")
//			      .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
//			      .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.temperature) + ")"; })
//			      .attr("x", 3)
//			      .attr("dy", ".35em")
//			      .attr("class", "chart-text")
//			      .text(function(d) { return d.name; });
			});
			
			
			</script>
			</div>
						
						
						
							
							<!-- <div id="resumepagesentimentscompanysentiment-1390475342"
								class="v-app v-app-loading v-theme-reindeer v-app-MyVaadinApplication"></div>

							<script type="text/javascript"
								src="loadCompanyAggregatedSentiment.js"></script> -->

						</div>
						<div class="block" id="about">
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
