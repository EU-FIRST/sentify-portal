/*
 * This file contains all logic of a sentiment object resumé page.
 * The purpose of this code is to handle dynamic page refresh without actually
 * reloading the whole page.
 * 
 * It consists of the following major classes:
 * - news list controller (for dynamic news list changes)  
 * - sentiment chart controller (for dynamically repainting the sentiment timeline)
 * - date picker controller (for changing time span of reported sentiments) 
 * - reputation topic buttons controller (for switching reputation topics)
 * 
 * The following components trigger dynamic page "refresh":
 * - date picker
 * - sentiment chart (on clicking the sentiment time series)
 * - reputation buttons
 * 
 * The following components are updated on page "refresh":
 * - news list 
 * - sentiment chart
 * 
 * 
 */

function ChartManager() {
	this.companyName = params["companyName"];
	this.so = params["so"];
	this.topic = params["topic"];
	this.fromDate = params["from-date"];
	this.toDate = params["to-date"];
	this.indicator = params["sentiment"];
	
	var names = [{'url': this.so, 'name': 'positive sentiment'}, {'url': this.so, 'name': 'negative sentiment'}],
	//, 'comp_APPLE_INC', 'comp_AMAZON_COM_INC'],
	colors = Highcharts.getOptions().colors,
	seriesCounter = 0,
	seriesOptions = [],
	yAxisOptions = [];

	colors [0] = '#1f77b4';
	colors [1] = '#ff7f0e';
	colors [2] = '#2ca02c';
	colors [3] = '#d62728';

	this.setChartProperties = function(object) {
		companyName = object["companyName"];
		so = object["so"];
		//names = [{'url': so, 'name': companyName + ' sentiment'}];
		
		this.fromDate = params["from-date"];
		this.toDate = params["to-date"];
		this.topic = params["topic"];
	};
	
	this.refresh = function() {
		this.setChartProperties(params);
		this.createChart();
		
	}
	
	this.createChart = function() {
		
		var seriesCounter = 0;
		var startDate = "";
		var endDate = "";
		var topic = "";
			
		//prepare end date
		if (typeof this.toDate == "undefined" || this.toDate == "") {
			var d = new Date();
			var curr_date = d.getDate();
			var curr_month = d.getMonth() + 1;
			var curr_year = d.getFullYear();
			
			var endDate = curr_year + '-' + curr_month + '-' + curr_date;
		} else {
			var endDate = this.toDate;
		}
		
		//prepare start date
		if (typeof this.fromDate == "undefined") {
			var startDate = "";
		} else {
			var startDate = this.fromDate;
		}
		
		//prepare topic param
		if (typeof this.topic == "undefined") {
			var topic = "";
		} else {
			var topic = this.topic;
		}
		
		$.each(names, function(i, name) {
			seriesOptions[i] = {
					name: name.name,
					data: [],
					color: colors[i],
					//showInLegend: false,
					marker: {
		                	enabled: false
		            },
		            type: 'area',
		            point: {
		            	events: {
		            		click: function() {
		            			//console.info(this.y);
		            			//console.info(this.x);
		            			//console.info(Highcharts.dateFormat('%Y-%m-%d', this.x));
		            			params["date-from"] = Highcharts.dateFormat('%Y-%m-%d', this.x);
			            		params["date-to"] = Highcharts.dateFormat('%Y-%m-%d', this.x);
			            		
			            		sentifyURLController.refresh();
		            		}
		            	}
		            }
				};
		});
		
		seriesOptions[names.length] = {
				name: "difference",
				data: [],
				color: colors[names.length],
				//showInLegend: false,
				marker: {
	                	enabled: false
	            },
	            type: 'line',
				yAxis: 0
			};
		/*
		seriesOptions[3] = {
				name: "difference",
				data: [],
				color: colors[1],
				//showInLegend: false,
				marker: {
	                	enabled: false
	            },
	            type: 'area',
				yAxis: 1
			};
		*/
		createChart();
		
		var indicator = this.indicator;
		//$.each(names, function(i, name) {
			var timeSerieURL = './timeseriefuzzy?so='+ names[0].url +'&startdate='+startDate+'&enddate='+endDate+'&topic='+topic+'&indicator='+indicator+'&callback=?';

			$.getJSON(timeSerieURL,	function(data) {
				var positive = [];
				var negative = [];
				var volume = [];
				
				var positiveVol = [];
				var negativeVol = [];
				
				var difference = [];
				var differenceVol = [];
				
				data.map(function(elem) {
					positive.push([elem[0], elem[1]]);
					negative.push([elem[0], -elem[2]]);
					volume.push([elem[0], elem[3]]);
					positiveVol.push([elem[0], elem[1]*elem[3]]);
					negativeVol.push([elem[0], -elem[2]*elem[3]]);
					difference.push([elem[0], elem[1]-elem[2]]);
					differenceVol.push([elem[0], elem[1]*elem[3]-elem[2]*elem[3]]);
				});
				
				var maximumPos = Math.max.apply(Math, positive.map(function(array) {
					return array[1];											
				}));
				var maximumNeg = Math.max.apply(Math, negative.map(function(array) {
					return array[1];											
				}));
					
				var hc = $('#container').highcharts();
				
				hc.series[0].setData(positiveVol);
				hc.series[1].setData(negativeVol);
				hc.series[2].setData(differenceVol);
				//hc.series[3].setData(volume);
				
				//hc.yAxis[0].update({max: maximumPos * 1});
				//hc.yAxis[0].update({min: maximumNeg * 1});
				
				//hc.yAxis[1].max = maximum * 2;
				//hc.yAxis[1].update({max: maximum * 1.5})
				

				
				// As we're loading the data asynchronously, we don't know what order it will arrive. So
				// we keep a counter and create the chart when all the data is loaded.
				seriesCounter++;

				if (seriesCounter == names.length) {
					//createChart();
				}
			});
		//});
		
//		var volumeTimeSerieURL = './timeserievolume?so='+this.so+'&startdate='+startDate+'&enddate='+endDate+'&topic='+topic+'&callback=?';
//
//		$.getJSON(volumeTimeSerieURL,	function(data) {
//		
//			var hc = $('#container').highcharts();
//			
//			var max = 0;
//			var maximum = Math.max.apply(Math, data.map(function(array) {
//				return array[1];											
//			}));
//			
//			//hack for missing timestamps
////			var serie1 = hc.series[0].data;
////			var serie1len = serie1.length;
////			var lastTimeStamp1 = serie1[serie1len-1].x;
////			var lastTimeStamp2 = data[data.length - 1][0];
////			if (lastTimeStamp1 != lastTimeStamp2 ) {
////				console.info("different");
////				//data.push([lastTimeStamp1, 1234]);
////			}
//			
//			hc.series[names.length].setData(data);
//			//hc.yAxis[1].max = maximum * 2;
//			hc.yAxis[1].update({max: maximum * 2})
//			
//			//console.info(hc.yAxis[1].max);
//			//console.info("Loaded.");
//			//console.info(maximum);
//		});
		
		function createChart() {
		
			Highcharts.setOptions({
			    global : {
			        useUTC : false
			    }
			});
	        $('#container').highcharts({
	        	
	        	credits: {
	        		enabled: false
	        	},
	            chart: {
	                type: 'line',
	                //marginRight: 130,
	                marginBottom: 25,
	                marginTop: 10,
	                backgroundColor: '#f3f3f3', 
	                
	                marginRight: 70,
	                marginLeft: 50,
	                events: {
		            	
		            	click: function(e) {
		            		//console.info(e);
		            		//console.info(Highcharts.dateFormat('%Y-%m-%d', e.xAxis[0].value)); 
		            		console.info(e.yAxis[0].value);
		            		//console.info(e.xAxis[0].value);
		            		//console.info(this.y);
		            		params["from-date"] = Highcharts.dateFormat('%Y-%m-%d', e.xAxis[0].value);
		            		params["to-date"] = Highcharts.dateFormat('%Y-%m-%d', e.xAxis[0].value);
		            		sentifyURLController.refresh();
		            	}
	                }
	            },
	            
	            title: {
	                text: 'News and blogs fuzzy sentiment chart',
	                x: -10, //center
	                style: {
	                    fontSize: '14px',
	                    fontFamily: 'Arial, sans-serif',
	                    fontWeight: 'bold'
	                }
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
	                //min: -1, 
	                //max: 1
	            }, {
	            	/*
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
					*/
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
	}
}

function NewsListManager() {
	//default crips sentiment app id
	this.currentAppId = "news-list-crisp-2022010684";
	if (typeof params["newslist-app"] != "undefined" && params["newslist-app"] != "") {
		this.currentAppId = params["newslist-app"];
	}
	
	//default
	this.serviceURL = "sentify-company-newslist/";
	if (typeof params["sentiment"] != "undefined") {
		if (params["sentiment"] == "crips") {
			this.serviceURL = "sentify-company-newslist/";
		}
		if (params["sentiment"] == "fuzzy") {
			this.serviceURL = "sentify-company-newslist-fuzzy/";
		}
		
	}
	
	
	this.refresh = function() {
		this.reloadNewsList();
	}
	
	this.reloadNewsList = function () {
		appid = this.currentAppId;
		// take out the old news list app.
		elem = $("div#"+appid)
		//elem.children().remove();
		newId = "vaadinapp-" + Math.floor(Math.random() * 10000000);
		elem.attr("id", newId);
		elem.append("<div class=\"loading\"> </div>");
		this.currentAppId = newId;
		
		if (window.location.pathname.length == 1 + window.location.pathname.lastIndexOf("/")) {
			length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.length;  
		} else {
			length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.lastIndexOf("/") + 1;
		}
		currentPath = window.location.href.substr(0, length);
		rest =  window.location.href.substr(length, 1000);

		browserDetailTest = currentPath + this.serviceURL + rest;

		//console.log(browserDetailTest);

		//browserDetailTest = "http://localhost:8080/sentify-company-page/sentify-company-newslist/";
			
		if (!window.vaadin) alert("Failed to load the bootstrap javascript: ./VAADIN/vaadinBootstrap.js");
		vaadin.initApplication(newId ,{
		    "authErrMsg": {
		        "caption": "Authentication problem",
		        "message": "Take note of any unsaved data, and <u>click here<\/u> to continue."
		    },
		    "comErrMsg": {
		        "caption": "Communication problem",
		        "message": "Take note of any unsaved data, and <u>click here<\/u> to continue."
		    },
		    "debug": true,
		    "heartbeatInterval": 300,
		    "serviceUrl": "./" + this.serviceURL,
		    "standalone": true,
		    "theme": "mytheme",
		    "vaadinDir": "./VAADIN/",
		    "browserDetailsUrl": browserDetailTest,
		    "versionInfo": {"vaadinVersion": "9.9.9.INTERNAL-DEBUG-BUILD"},
		    "widgetset": "eu.first.sentify.companypage.AppWidgetSet"
		});
		
	}

}

function URLController() {
	
	this.refresh = function() {
    	urlBase = location.href.substring(0, location.href.lastIndexOf("?")+1)
    	
    	if (typeof params != "undefined") {
    		urlBase = urlBase.concat("so="+params["so"]+"&")
    		if (typeof params["topic"] != "undefined" && params["topic"] != "") {
	    		urlBase = urlBase.concat("topic="+params["topic"]+"&")
    		}
    		if (typeof params["from-date"] != "undefined" && params["from-date"] != "") {
	    		urlBase = urlBase.concat("from-date="+params["from-date"]+"&");	    			
    		}
    		if (typeof params["to-date"] != "undefined" && params["to-date"] != "") {
		    	urlBase = urlBase.concat("to-date="+params["to-date"]+"&");	    			
    		}
    	}
    	
    	$(".datepicker").each(function() {
    		var name = $(this).attr("name");
    		$(this).val(params[name]);
    	});
    	
    	window.history.pushState(params, "The Sentify Portal -- project FIRST", urlBase);
    	
    	this.refreshAllComponents();
    }
	
	this.refreshAllComponents = function() {
		//check if we need chart
		if (typeof params["from-date"] != "undefined" && typeof params["to-date"] != "undefined") {
			if (params["from-date"] != "" && params["to-date"] != "") {
				if (params["from-date"] == params["to-date"]) {
					$("div#container").remove();
				}
			}
		}
		//refresh all components on the page: newslist, chart, documents available (?)
		if (typeof newsListManager != "undefined") {
			newsListManager.refresh();
		}
		
		if (typeof chartManager != "undefined") {
			chartManager.refresh();
		}
	}
}


$(document).ready(function() {
	//check default values for param object
	//if no topic is set, we assume that it shouldn't be set.
	//otherwise it is bb (Business Behaviour) as default
	if (typeof params["topic"] != "undefined" && typeof params["sentiment"] != "undefined" && params["sentiment"] != "price") {
		if (params["topic"] == "") {
			params["topic"] = "bb";	
		}
	}
	
	//check if we need chart
	if (typeof params["from-date"] != "undefined" && typeof params["to-date"] != "undefined") {
		if (params["from-date"] != "" && params["to-date"] != "") {
			if (params["from-date"] == params["to-date"]) {
				//$("div#container").remove();
			}
		}
	}
	if ($("div#container").length != 0) {
		//chart controller
		chartManager = new ChartManager();
		chartManager.createChart();

	}
	

	
	
	//news list manager
	newsListManager = new NewsListManager();
	
	//url controlles (refresher)
	sentifyURLController = new URLController();
	
	//initialize reputation topics buttons
	if (typeof params != "undefined") {
		
		if (params["topic"] && params["topic"] != "") {
			//console.info(params["topic"]);
			activeButtonId = "#btn-reputation-" + params["topic"]; 
			$('button[name="reputation"]').each(function() {
				$(this).removeClass("btn-info");
				$(this).removeClass("active");
			});
			//set active button according to the URL params
			$(activeButtonId).addClass("btn-info");
			$(activeButtonId).addClass("active");
		} else {
			//no topic in param list, setting to default (business behaviour)
			$('#btn-reputation-bb').addClass("btn-info");
			$('#btn-reputation-bb').addClass("active");
			//params["topic"] = $('#btn-reputation-bb').val();
		}
	}
	
	//switch button event (refresh page)
	$('button[name="reputation"]').each(function(){
		$(this).click(function(e) {
			$('button[name="reputation"].btn-info').removeClass("btn-info");
	    	$(e.target).addClass("btn-info");
	    	
	    	//newsListManager.reloadNewsList();
	    	params["topic"] = $(e.target).val();
	    	//console.info( $(e.target).val());
	    	//reloadNewsList(currentAppId);
	    	sentifyURLController.refresh();
		})
	});
	
	//date picker definition
	$(".datepicker").datepicker({
	    dateFormat: 'yy-mm-dd',
	    inline: true,
	    //minDate: new Date(2010, 1 - 1, 1),
	    //maxDate:new Date(2010, 12 - 1, 31)
			
	});
	
	//initialize fields with values (if exist)
	$(".datepicker").each(function() {
		var name = $(this).attr("name");
		$(this).val(params[name]);
	});
	
	//date range select event
	$("#date-accept-btn").click(function(e) {
		
		//console.info("date selected");
		dateSpan = {};
		
		$(".datepicker").each(function() {
			//console.info($(this).datepicker("getDate"));
			if ($(this).datepicker("getDate") != null) {
				var isodate = $.datepicker.formatDate("yy-mm-dd", $(this).datepicker("getDate"));
	    		
	    		name = $(this).attr("name");
	    		
	    		params[name] = isodate;
	    		dateSpan[name] = isodate;
			}
		});
		
		//console.info(dateSpan);
	
		if (dateSpan["from-date"] && dateSpan["to-date"]) {
			//console.info("ready to reload!");
			sentifyURLController.refresh();
		}
	});
	


});


