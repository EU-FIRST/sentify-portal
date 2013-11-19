<!DOCTYPE html>

<html>
<head>
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
<link rel="stylesheet" type="text/css" href="css/fadein.css" />
<link rel="stylesheet" type="text/css" href="css/world-map.css" />

<link rel="stylesheet" href="js/jvectormap/jquery-jvectormap-1.2.2.css" type="text/css" media="screen"/>
<script src="js/vis-test/jquery-1.8.3.min.js"></script>
<script src="js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<script type="text/javascript" src="./VAADIN/vaadinBootstrap.js"></script>

</head>

<body >
<div id="page-wrapper" class="page-wrapper">
  <div id="header" class="header">
    <div id="header-inner" > <a href="" id="logo"> First Project </a> <a href="#main-menu" id="main-menu-link"> Menu </a> </div>
  </div>
  <div id="main" class="main clearfix">
    <div class="main-inner">
      <div id="main-content" class="main-content" style="width: 100%;">
        <div class="inner">
         
		 
        <div class="block">

		<div class="btn-group" data-toggle="buttons-radio" style="display: inline">
			<button class="btn" name="days" value="1" id="btn-map-1-day">1 day</button>
			<button class="btn" name="days" value="7" id="btn-map-7-days">7 days</button>
			<button class="btn" name="days" value="14" id="btn-map-14-days">14 days</button>
			<button class="btn btn-primary" name="days" value="30"  id="btn-map-30-days">30 days</button>
		</div>
		<div class="btn-group" style="display: inline">
			<button class="btn" name="sentiment-data" value="volume" >Volume</button>
			<button class="btn btn-primary" name="sentiment-data" value="sentiment" >Aggregated Sentiment</button>
		</div>
			<div id="world-map" style="width: 800px; height: 500px" class="map-loading"></div>
			  <script>
			    $(function(){

			    	$('button[name="days"]').each(function(){
			    		$(this).click(function(e) {
			    			$('button[name="days"].btn-primary').removeClass("btn-primary");
					    	$(e.target).addClass("btn-primary");
					    	reloadMap($(e.target).val(), $('button[name="sentiment-data"].btn-primary').val());
					    	//console.info($(e.target).val());
					    	//console.info($('button[name="sentiment-data"].btn-primary').val());
			    		})
			    	});
			    	
			    	$('button[name="sentiment-data"]').each(function(){
			    		$(this).click(function(e) {
			    			$('button[name="sentiment-data"].btn-primary').removeClass("btn-primary");
					    	$(e.target).addClass("btn-primary");
					    	reloadMap($('button[name="days"].btn-primary').val(), $(e.target).val());
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
							    		dataValues[data[i].country] = data[i].volume;
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
					    		mapObject.series.regions[0].setValues(dataValues);
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
					    	    el.html(el.html()+' in 7-days <br> Average Sentiment: '+dataValues[code]+' <br> Volume: ' + volumeValues[code]);
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
						    	  backgroundColor: '#f3f3f3',
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
						    	    el.html(el.html()+' in 7-days <br> Average Sentiment: '+dataValues[code]+' <br> Volume: ' + volumeValues[code]);
						    	  },
						    	  onRegionClick: function(e, code) {
						    		  console.info(e);
						    		  console.info(code);
						    		  console.info(dataValues);
						    		  console.info(volumeValues);
						    	  }
						    	  
						    	});
						    });

				    }
			    
				    
				    loadMap(30, "data");
			    });
			  </script>




		</div>

		<div class="block">
			<iframe width="100%" height="2000px" frameborder="0" allowtransparency="true" src="${frame.url}" name="PID5" style="overflow: hidden;"></iframe>
			

		</div>

          <div class="block">
            <p>This website is under active development and some features may not work as expected. Please be patient. For more information on the <strong>FIRST</strong> project visit our website: <a href="#" >http://project-first.eu/</a></p>
            
          </div>

        </div>
      </div>
      <div id="navigation">
        <ul class="menu" id="main-menu">
          <li><a href="./">Home</a></li>
          <li><a href="./company">Companies</a></li>
          <li><a href="./country">Countries</a></li>
          <li><a href="./stocks.jsp">Stocks</a></li>
          <li><a href="./visualisations" class="active">Visualisations</a></li>
          <li><a href="./twitter.jsp">Twitter Sentiments</a></li>
        </ul>
      </div>
     
      <!-- 
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
				<script type="text/javascript" src="loadAnalyzedDocuments.js"></script>
            </h2>
            <p>Documents analyzed</p>
          </div>
          <div class="block" id="about">
            <h2>Company Search</h2>
			<form class="form-inline" action="search" method="get">
				<input name="q" type="text" class="input" placeholder="Type company name">
				<button type="submit" class="btn">Search</button>
			</form>
 		  </div>
          <div class="block" id="about">
            <h2>About FIRST</h2>
            <p class="teaser">FIRST develops and provides a large scale information extraction and integration infrastructure which will assist in various ways during the process of financial decision making.</p>
            <ul class="links">
              <li class="read_more"><a href="">Read More</a></li>
            </ul>
          </div>
          <div class="block">
            <h2>List of Monitored Sources</h2>
            <ul>
              <li>To be downloaded from the knowledgebase</li>
            </ul>
          </div>
        </div>
        
      </div>
        -->
    </div>
    <div id="footer">Footer disclaimer </div>
  </div>
  <!-- main --> 
</div>
</body>
</html>
