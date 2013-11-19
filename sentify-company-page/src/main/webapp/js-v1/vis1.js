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

var margin = {top: 20, right: 80, bottom: 30, left: 50},
    width = 800 - margin.left - margin.right,
    height = 400 - margin.top - margin.bottom;

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

var svg = d3.select("div#vis").append("svg")
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

d3.json("./GetSentimentTimeline?from="+fromDate+"&to="+toDate+"&stock=$AAPL", function(data) {
	
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
        
  
  
//  city.append("text")
//      .datum(function(d) { return {name: d.name, value: d.values[d.values.length - 1]}; })
//      .attr("transform", function(d) { return "translate(" + x(d.value.date) + "," + y(d.value.temperature) + ")"; })
//      .attr("x", 3)
//      .attr("dy", ".35em")
//      .attr("class", "chart-text")
//      .text(function(d) { return d.name; });
});