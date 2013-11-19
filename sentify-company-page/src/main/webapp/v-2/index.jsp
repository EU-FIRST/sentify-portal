<!DOCTYPE html>

<html>
<head>
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta content="utf-8" http-equiv="encoding">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="stylesheet" type="text/css" href="css/html.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/backgrounds.css" />
<link rel="stylesheet" type="text/css" href="css/pages.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
<link rel="stylesheet" type="text/css" href="css/blocks.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="css/inline.css" />
<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/newslist.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/trending-topics.css" />

<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>
<script src="js/d3.v3.js" charset="utf-8"></script>
<script src="js/d3.layout.cloud.js"></script>

</head>

<body >
<div id="page-wrapper" class="page-wrapper">
  <div id="header" class="header">
    <div id="header-inner" > <a href="" id="logo"><img src="images/logo_head.png"></a> <a href="#main-menu" id="main-menu-link"> Menu </a> </div>
  </div>
  <div id="main" class="main clearfix">
    <div class="main-inner">
      
      <div id="navigation">
        <ul class="menu" id="main-menu">
          <li><a href="./" class="active">Home</a></li>
          <li><a href="./company">Companies</a></li>
          <li><a href="./country">Countries</a></li>
          <li><a href="./stocks.jsp">Stocks</a></li>
          <li><a href="./visualisations">Visualisations</a></li>
          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
        </ul>
      </div>
      <div class="intro-panel">

            	<script src="js/vis-test/jquery-1.8.3.min.js"></script>
            	<script src="http://code.highcharts.com/stock/highstock.js"></script>
				
				<div id="container" style="height: 500px; min-width: 500px"></div>
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
					                text: 'News and blogs trends',
					                x: -20, //center
					                style: {
					                    fontSize: '16px',
					                    fontFamily: 'Arial, sans-serif',
					                    fontWeight: 'bold'
					                }
					            },
					            subtitle: {
					                text: 'Aggregated daily sentiment, moving average (3 days)',
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
      
      
      <div id="main-content" class="main-content">
        <div class="inner">
        
<!--         	<div class="block graphic">
            	<h2>Twitter trends</h2>

				
            </div> -->
            
<!--
          <div class="block graphic">
            <h2>Trending Topics</h2>
               
				
				
				<script src="js/libs/modernizr-2.0.6.min.js"></script>
				
 				<div id="main" role="main">
			      <div id="view_selection" class="btn-group">
			        <a href="#" id="all" class="btn active">All topics</a>
			        <a href="#" id="year" class="btn">Topics by week</a>
			      </div>
			      <div id="vis"></div>
    			</div>
    			
    			
    			  <script>window.jQuery || document.write('<script src="js/vis-test/jquery-1.6.2.min.js"><\/script>')</script>

  <script defer src="js/vis-test/plugins.js"></script>
  <script defer src="js/vis-test/script.js"></script>
  <script src="js/vis-test/CustomTooltip.js"></script>
  <script src="js/vis-test/coffee-script.js"></script>
  <script src="js/vis-test/d3.min.js"></script>
  <script src="js/vis-test/d3.csv.min.js"></script>
  <script src="js/vis-test/d3.layout.min.js"></script>
  <script src="js/vis-test/d3.geom.min.js"></script>
  <script type="text/coffeescript" src="js/vis-test/vis.coffee"></script>
  <script type="text/javascript">
    $(document).ready(function() {
        $(document).ready(function() {
          $('#view_selection a').click(function() {
            var view_type = $(this).attr('id');
            $('#view_selection a').removeClass('active');
            $(this).toggleClass('active');
            toggle_view(view_type);
            return false;
          });
        });
    });
  </script> 
    			
    			
    			
          </div>
-->          
          <div class="block">
            <h2>Latest News</h2>
			 <div id="vaadintest-2022010684"
				class=" v-app v-app-VaadinServiceSession">
				<div class=" v-app-loading"></div>
				<noscript>You have to enable javascript in your
					browser to use an application built with Vaadin.</noscript>
			</div>
			<script type="text/javascript" src="widgetloaders/loadDocumentList2.js"></script>
            <div style="text-align: center; padding: 10px;">
             <!-- ALP: Comment this line
            <button type="submit" class="btn">Load more news</button>-->
            </div> 
          </div>

        </div>
      </div>
      
      
      <div id="region-sidebar" class="region region-sidebar">
        <div class="inner">
        
          <div class="block" id="about">
            <h2>Trending topics (30 days)</h2>
			<div id="cloud">
			
			<script>
					

  var fill = d3.scale.category20();
  var colorFill = d3.scale.sqrt().domain([-1,0,1]).range(['red', 'orange', 'green']);

  var fontSize = d3.scale.log().range([5, 25]);
  
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth() + 1;
	var curr_year = d.getFullYear();
	
	var todayDate = curr_year + '-' + curr_month + '-' + curr_date;
  
 

	d3.json("./trendingtopics?enddate=" + todayDate, function(data) {
		

   

	d3.layout.cloud()
 	  .words(data.map(function(d) { 
        return {text: d[0], size: fontSize(d[1]/500), score: d[2]};
      }))
      .rotate(function() { return ~~(Math.random() * 5) * 5 -10; })
      .font("Impact")
      .fontSize(function(d) { /* console.info(d.size); console.info(fontSize(d.size)); */ return d.size; })
      .on("end", draw)
      .start();

  function draw(words) {
    d3.select("#cloud").append("svg")
        .attr("width", 320)
        .attr("height", 220)
      .append("g")
        .attr("transform", "translate(150,110)")
      .selectAll("text")
        .data(words)
      .enter().append("text")
        .style("font-size", function(d) { return d.size + "px"; })
        .style("font-family", "Impact")
        .style("fill", function(d, i) {return colorFill(d.score); })
        .attr("text-anchor", "middle")
        .attr("transform", function(d) {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text(function(d) { return d.text; });
  }
  
	});
  </script>
			</div>
 		  </div>
        
          <div class="block" id="about">
            <h2>Search Companies</h2>
			<form class="form-inline" action="search" method="get">
				<input name="q" type="text" class="input" placeholder="Type company name">
				<button type="submit" class="btn">Search</button>
			</form>
			<p>Not sure what to do? Try with <a href="./search?q=microsoft">Microsoft</a>, <a href="./search?q=microsoft">Google</a> or <a href="./search?q=microsoft">Barclays</a>!</p>
 		  </div>
 		  
          <div class="block analysis">
             <!-- ALP: LO COMENTO POR EL ERROR DE SERVICIOS => DESCOMENTAR -->
			 <!-- <h2>
				<div id="documents-analyzed-widget-2022010683"
					class=" v-app v-app-VaadinServiceSession">
					<div class=" v-app-loading"></div>
					<noscript>You have to enable javascript in your
						browser to use an application built with Vaadin.</noscript>
				</div>
				<script type="text/javascript" src="widgetloaders/loadAnalyzedDocuments.js"></script>
            </h2>  -->
            <p>Documents analyzed since 1/03/2013</p>
          </div>
          
          <div class="block" id="about">
            <h2>About FIRST</h2>
            <p class="teaser">FIRST develops and provides a large scale information extraction and integration infrastructure which will assist in various ways during the process of financial decision making.</p>
            <ul class="links">
              <li class="read_more"><a href="">Read More</a></li>
            </ul>
          </div>
          
        </div>
      </div>
    </div>
    <div id="footer">Footer disclaimer </div>
  </div>
  <!-- main --> 
</div>
<iframe tabIndex="-1" id="__gwt_historyFrame" style="position:absolute;width:0;height:0;border:0;overflow:hidden;" src="javascript:false"></iframe>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
