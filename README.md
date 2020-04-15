Application : ShortestPathService
Purpose     : To find out the Shortest Path between two planets (source and distance)
========================================================================================

Package Structure
---------------------
com.discovery.shortestpathservice.config --> Algorithm to find out shortest path
com.discovery.shortestpathservice.controller --> Rest Controller for import the file and
					to load the UI and deligate the request for shortestpath to the service and repository.
com.discovery.shortestpathservice.service --> Service Interfaces.
com.discovery.shortestpathservice.service.impl --> Service Implementation classes.
com.discovery.shortestpathservice.repositoy --> Repository Interfaces
						and CRUD operational methods for graph domain object goes here.
com.discovery.shortestpathservice.entity --> Entity classes goes here.

com.discovery.shortestpathservice.model --> Model classes goes here.

Import Files
===========
/resources/graph folder

Application Port
================
application port available in the below path:
/rsources/application.yml
application start with 8084 port

View pages
==============
static resource files place under the resources/templates


URLs to the load the UI page
============================
http://localhost:8084/ --> it loads the frondend page where you can select source and destination planet

on submission, the URI /shortestpath will be called and displays the result.

Click on Back button to submit the page with difference planets.

Logs:
=====
PATH: /target/logs/shortest-path-service.log


how to run the application.
---------------------------

run:  mvn clean install --> it generate the .jar in the below path  /target/shortest-path-service-0.0.1-SNAPSHOT.jar

to start the application
========================

Execute command: java -jar target\shortest-path-service-0.0.1-SNAPSHOT.jar

ShotestPathServiceApplication.java will run automatically.

ShotestPathServiceApplication.java ---> This is starting point of the service and this class reads the Import File and persist with H2 Database on application start up.
so that data is ready after start up.



