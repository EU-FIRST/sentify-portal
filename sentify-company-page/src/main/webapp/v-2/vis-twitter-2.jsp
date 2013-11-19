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
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />

<script language='javascript' src='js/d3.v2.js'></script>
<!--  
<script language='javascript' src='js/vis1.js'></script>
-->
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

.line {
  fill: none;
  stroke: steelblue;
  stroke-width: 1.5px;
  
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

<body >
<div id="page-wrapper" class="page-wrapper">
  <div id="header" class="header">
    <div id="header-inner" > <a href="" id="logo"> First Project </a> <a href="#main-menu" id="main-menu-link"> Menu </a> </div>
  </div>
  <div id="main" class="main clearfix">
    <div class="main-inner">
      <div id="main-content" class="main-content">
        <div class="inner">

          <div class="block">
            <h2>Twitter Sentiments Timeline</h2>
            <h3>MSFT</h3>
                <div class="btn-group">
    				<button class="btn">1 day</button>
    				<button class="btn">7 days</button>
    				<button class="btn">14 days</button>
    				<button class="btn">30 days</button>
    				<button class="btn btn-primary">120 days</button>
   				</div>
			<div id="vis">
			<script language='javascript' src='js/vis2.js'></script>
			</div>


          </div>
        </div>
      </div>
      <div id="navigation">
        <ul class="menu" id="main-menu">
          <li><a href="/document-sentiments">Home</a></li>
          <li><a href="/resumepage-sentiments">Companies</a></li>
          <li><a href="">Stocks</a></li>
          <li><a href="" class="active">Visualisations</a></li>
          <li><a href="/twitter-sentiment">Twitter Sentiments</a></li>
        </ul>
      </div>
      <div id="region-sidebar" class="region region-sidebar">
        <div class="inner">
          
          <div class="block" id="about">
            <h2>Company Search</h2>
<!--             <p class="teaser">Enter company name to start sentiment analysis</p>
            <input style="width: 70%" /><input type="submit" value="Search">
 --><!--             <ul class="links">
              <li class="read_more"><a href="">Read More</a></li>
            </ul>
 -->      
 			    <form class="form-inline">
    				<input type="text" class="input" placeholder="Type company name">
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
              <li>Lorem ipsum d<strong>olor sit ame</strong>t, consectetur adipiscing elit.</li>
              <li>Praesent eget erat a ipsum sagittis molestie vel sit amet est.</li>
              <li>Vestibulum ac mass<strong>a a urna luctus </strong>aliquet.</li>
              <li>Ut eu<em>ismod lectus iaculis mag</em>na mollis luctus.</li>
              <li>Nulla eu sem risus, et pretium libero.</li>
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
