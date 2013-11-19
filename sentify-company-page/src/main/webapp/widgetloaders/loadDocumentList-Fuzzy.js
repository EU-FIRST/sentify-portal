if (window.location.pathname.length == 1 + window.location.pathname.lastIndexOf("/")) {
	length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.length  
} else {
	length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.lastIndexOf("/") + 1;
}currentPath = window.location.href.substr(0, length)
rest =  window.location.href.substr(length, 1000)

browserDetailTest = currentPath + "sentify-company-newslist-fuzzy/" + rest;

//console.log(browserDetailTest);

//browserDetailTest = "http://localhost:8080/sentify-company-page/sentify-company-newslist/";
	
if (!window.vaadin) alert("Failed to load the bootstrap javascript: ./VAADIN/vaadinBootstrap.js");
vaadin.initApplication("news-list-fuzzy-202201068412",{
    "authErrMsg": {
        "caption": "Authentication problem",
        "message": "Take note of any unsaved data, and <u>click here<\/u> to continue."
    },
    "comErrMsg": {
        "caption": "Communication problem",
        "message": "Take note of any unsaved data, and <u>click here<\/u> to continue."
    },
    "debug": false,
    "heartbeatInterval": 300,
    "serviceUrl": "./sentify-company-newslist-fuzzy/",
    "standalone": true,
    "theme": "mytheme",
    "vaadinDir": "./VAADIN/",
    "browserDetailsUrl": browserDetailTest,
    "versionInfo": {"vaadinVersion": "7.1.7"},
    "widgetset": "eu.first.sentify.companypage.AppWidgetSet"
});
