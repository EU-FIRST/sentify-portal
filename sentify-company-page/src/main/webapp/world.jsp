
<!DOCTYPE html>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=yes" />
<link rel="icon" type="image/png" href="images/favicon.png">

<link rel="stylesheet" href="jquery/jquery-ui.min.css" />
<script src="jquery/jquery-1.10.2.min.js"></script>
<script src="jquery/jquery-ui.min.js"></script>



<link rel="stylesheet" type="text/css" href="css/style.css" />

<link rel="stylesheet" type="text/css" href="css/loading.css" />
<link rel="stylesheet" type="text/css" href="css/newslist.css" />
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/trending-topics.css" />

<link rel="stylesheet" type="text/css" href="css/world-map.css" />

<link rel="stylesheet" href="js/jvectormap/jquery-jvectormap-1.2.2.css" type="text/css" media="screen"/>
<script src="js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/jvectormap/jquery-jvectormap-world-mill-en.js"></script>


<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>

</head>

<body class="">

	<div id="page-wrapper" class="page-wrapper">

		<div id="header" class="header">

			<div id="header-inner">

				<a href="" id="logo"> First Project </a>
				
				<h1 class="page-title">The World - Sentify Portal</h1>
				 
				<a href="#main-menu" id="main-menu-link"> Menu </a>
			</div>

		</div>
		<div id="main" class="main clearfix">
			<div class="main-inner">
				<div id="main-content" class="main-content">
					<div class="inner">
						<div class="block">
						
						
		<div class="btn-group" data-toggle="buttons-radio" style="display: inline">
			<button class="btn" name="days" value="1" id="btn-map-1-day">1 day</button>
			<button class="btn" name="days" value="7" id="btn-map-7-days">7 days</button>
			<button class="btn" name="days" value="14" id="btn-map-14-days">14 days</button>
			<button class="btn btn-info active" name="days" value="30"  id="btn-map-30-days">30 days</button>
		</div>
		<div class="btn-group" data-toggle="buttons-radio" style="display: inline">
			<button class="btn" name="sentiment-data" value="volume" >Volume</button>
			<button class="btn btn-info active" name="sentiment-data" value="sentiment" >Aggregated Sentiment</button>
		</div>
		
		<div id="world-map" style="width: 760px; height: 500px" class="map-loading"></div>
			  <script>
			    $(function(){

			    	$('button[name="days"]').each(function(){
			    		$(this).click(function(e) {
			    			$('button[name="days"].btn-info').removeClass("btn-info");
					    	$(e.target).addClass("btn-info");
					    	reloadMap($(e.target).val(), $('button[name="sentiment-data"].btn-info').val());
					    	//console.info($(e.target).val());
					    	//console.info($('button[name="sentiment-data"].btn-primary').val());
			    		})
			    	});
			    	
			    	$('button[name="sentiment-data"]').each(function(){
			    		$(this).click(function(e) {
			    			$('button[name="sentiment-data"].btn-info').removeClass("btn-info");
					    	$(e.target).addClass("btn-info");
					    	reloadMap($('button[name="days"].btn-info').val(), $(e.target).val());
					    	//console.info($('button[name="days"].btn-primary').val());
					    	//console.info($(e.target).val());
			    		})
			    	});
			    

			    	function reloadMap(days, measure) {
			    		var mapObject = $('#world-map').vectorMap('get', 'mapObject');
			    		$.getJSON('/aggregates/cas?days='+days,	function(data) {
					    	dataValues = {}
					    	volumeValues = {}
					    	
					    	if (measure.toLowerCase() == "volume") {
					    		maxval = 0;
					    		for (var i=0; i<data.length; i++) {
					    			if (data[i].days == days) {
							    		dataValues[data[i].country] = data[i].sentiment;
							    		volumeValues[data[i].country] = data[i].volume; 	
							    		if (data[i].volume > maxval) {
							    			maxval = data[i].volume;
							    		}
					    			}
								}	
					    		mapObject.series.regions[0].scale.setMin(0);
					    		mapObject.series.regions[0].scale.setMax(maxval);
					    		//mapObject.series.regions[0].setScale(['#666666', '#FFA500']);
					    		mapObject.series.regions[0].setScale(['#666666', '#FF0000']);
					    		mapObject.series.regions[0].setValues(volumeValues);
					    		mapObject.series.regions[0].setNormalizeFunction('linear')
					    	} else {
					    		
					    		for (var i=0; i<data.length; i++) {
					    			if (data[i].days == days) {
							    		dataValues[data[i].country] = data[i].sentiment;
							    		volumeValues[data[i].country] = data[i].volume; 	
					    			}
						    	}
					    		mapObject.series.regions[0].scale.setMin(-1);
					    		mapObject.series.regions[0].scale.setMax(1);
					    		mapObject.series.regions[0].setScale(['#FF0000', '#00FF00']);
					    		mapObject.series.regions[0].setNormalizeFunction('linear')
					    		mapObject.series.regions[0].setValues(dataValues);
					    	}
					    	
					    	
					    	mapObject.onRegionLabelShow = function(e, el, code){
					    	    el.html(el.html()+'  <br> Average Sentiment: '+dataValues[code]+' <br> Volume: ' + volumeValues[code]);
					    	  }
					    	mapObject.onRegionClick = function(e, code) {
					    		  console.info(e);
					    		  console.info(code);
					    	  } 
			    			
					    	
					    });
			    		
			    	}
			    	
				    function loadMap(days, measure) {
					    $.getJSON('/aggregates/cas?days='+days,	function(data) {
					    	dataValues = {}
					    	volumeValues = {}
					    	
 					    	for (var i=0; i<data.length; i++) {
 					    		if (data[i].days == days) {
 						    		dataValues[data[i].country] = data[i].sentiment;
 						    		volumeValues[data[i].country] = data[i].volume; 					    			
 					    		}
					    	}
					    	
						      $('#world-map').vectorMap({
						    	  map: 'world_mill_en',
						    	  backgroundColor: 'rgba(243, 243, 243, 0)',
						    	  regionStyle: {
						    		  initial: {
						    			  fill: '#666666',
						    			  "fill-opacity": 1,
						    			  stroke: 'none',
						    			  "stroke-width": 0,
						    			  "stroke-opacity": 1
						    		  },
						    		  hover: {
						    			  "fill-opacity": 0.8
						    		  },
						    		  selected: {
						    			   fill: 'yellow'
						    		  },
						    		  selectedHover: {
						    		  }
						    	  },
						    	  series: {
						    	    regions: [ {
						    	      values: dataValues,
						    	      attribute: "fill",
						    	      //scale: ['#FF0000', '#00FF00'],
						    	      scale: ['#FF0000', '#00FF00'],
						    	      normalizeFunction: 'linear'
						    	    }]
						    	  },
						    	  onRegionLabelShow: function(e, el, code){
						    	    el.html(el.html()+' <br> Average Sentiment: '+dataValues[code]+' <br> Volume: ' + volumeValues[code]);
						    	  },
						    	  onRegionClick: function(e, code) {
						    		  window.location.href = "/countries?so=cou_" + code;
						    		  
						    		  //console.info(e);
						    		  //console.info(code);
						    		  //console.info(dataValues);
						    		  //console.info(volumeValues);
						    	  }
						    	  
						    	});
						    });

				    }
			    
				    
				    loadMap(30, "data");
			    });
			  </script>



						</div>					
					</div>
				</div>
				<div id="navigation">
					<div id="main-menu">
						<ul class="menu">
							<li><a href="/">Home</a>
								</li>
							<li><a href="/company">Company Reputation</a>
								<ul>
									<li><a href="/company">Crisp Sentiment</a></li>
									<li"><a href="/company-fuzzy">Fuzzy Sentiment</a></li>
									<li><a href="/company-reputation">Reputation Topics</a></li>
								</ul></li>
							<li><a href="/stocks">Stock Price</a>
								<ul>
									<li><a href="/stocks">Crisp Sentiment</a></li>
									<li><a href="/stocks-fuzzy">Fuzzy Sentiment</a></li>
									<!--<li><a href="/stocks-twitter">Twitter</a></li>-->
								</ul></li>
							<li class="active"><a href="/world">Regions</a>
								<ul>
									<li class="active"><a href="/world">World</a></li>
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
          
						<div class="block" id="about">
				            <h2>Search Companies</h2>
							<form class="form-inline" action="search" method="get">
								<input name="q" type="text" class="input" placeholder="Type company name">
								<button type="submit" class="btn">Search</button>
							</form>
							<p>Not sure what to do? Try with <a href="./search?q=microsoft">Microsoft</a>, <a href="./search?q=google">Google</a> or <a href="./search?q=barclays">Barclays</a>!</p>
			 		    </div>		
			 		    				
						<div class="block" id="about">
							<h2>About FIRST</h2>

							<p class="teaser">FIRST develops and provides a large scale information extraction and integration infrastructure which will assist in
								various ways during the process of financial decision making.</p>
							<ul class="links">
								<li class="read_more"><a href="">Read More</a></li>
							</ul>

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
	<script src="bootstrap/js/bootstrap.min.js"></script>
	
</body>
</html>
