//browserDetailTest = window.location.href.replace(/#.*/,'') + "vaadin-test/";
//browserDetailTest = "http://localhost:8080/sentify-company-page/vaadin-test/?name=john";
if (window.location.pathname.length == 1 + window.location.pathname.lastIndexOf("/")) {
	length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.length  
} else {
	length = window.location.href.lastIndexOf(window.location.pathname) + window.location.pathname.lastIndexOf("/") + 1;
}
currentPath = window.location.href.substr(0, length)
rest =  window.location.href.substr(length, 1000)

browserDetailTest = currentPath + "vaadin-test/" + rest;

console.log(browserDetailTest);
if (!window.vaadin) alert("Failed to load the bootstrap javascript: ./VAADIN/vaadinBootstrap.js");
vaadin.initApplication("vaadintest-2022010683",{
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
    "serviceUrl": "./vaadin-test/",
    "standalone": true,
    "theme": "mytheme",
    "vaadinDir": "./VAADIN/",
    "browserDetailsUrl": browserDetailTest,
    "versionInfo": {"vaadinVersion": "9.9.9.INTERNAL-DEBUG-BUILD"},
    "widgetset": "eu.first.sentify.companypage.AppWidgetSet"
});
