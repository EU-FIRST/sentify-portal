The Sentify Portal
==============

Introduction
---------------------

The source code of the portal is structured in a modular way. Each module represents different module of the portal, such as GUI widget or backend service. The description of each module is given below.
Each module of Sentify Portal source code is a Maven  project. Maven is build automation tool that also helps managing project configuration, such as dependency inclusion, multi-modules build scenarios, and many other tasks that facilitate project deployment.

Module Description
-----------------

- sentify-company	- The parent module to compile all subsequent modules and create target deployable WAR file.
- sentify-company-page -	The main module containing definition of the web application (web.xml), all JSP files and servlets. This module is also used to compile Vaadin widgetsets (GUI elements used by Vaadin applications). The complete build of the whole projects results in a deployable WAR file in the target directory of this module.
- first-discoveryws-client-production	- WP5 Service Client module, containing configuration for autogenerating Java code used for invoking FIRST data access services. Every time the service client is regenerated, the code must be cleaned before a new build using maven “clean” goal (mvn clean).
- sentify-configuration	- Common module containing fine-grained configuration of several GUI widgets. It contains some constants such as the number of news to be displayed, max lookup dates, time between GUI refreshes, etc. It is intentionally excluded from the “configuration chapter” as it contains mainly the low-level configuration options for fine tuning certain details that are not relevant from the deployment point of view.
- sentify-search-helpers	- Module containing indexing and search functionalities for retrieving sentiment objects details from the FIRST ontology. It also loads all necessary mappings for 
- sentify-company-newslist	- This module contains the code of the Vaadin GUI widget for displaying list of news articles and blog posts showing knowledge-based sentiment.
- sentify-company-newslist-fuzzy	- This module contains the code of the Vaadin GUI widget for displaying list of news articles and blog posts showing fuzzy sentiment.
- sentify-widgets-documentsanalyzed	- This module contains the code of the Vaadin GUI widget for counting the total number of processed documents.
- sentify-widgets-drilldown	- This module contains the code of the Vaadin GUI widget for for performing drill-down on the selected document.

Building the project
------

In order to download and build the project, issue the following commands

    git clone git://github.com/project-first/sentify-portal.git 
    cd sentify-company
    mvn clean install

