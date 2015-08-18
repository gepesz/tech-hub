
How to deploy ManchesterEJB.jar on JBoss 4.2.2?


- install MySQL:
	- mysql-installer-5.5.19.0.msi
	- Default install

- add 'manchester' user:
	- open MySQL Workbench, click on 'Manage Security' link
	- Add Account:
		* un: manchester
		* pw: manchester
		* limit connectivity to hosts matching: localhost
	- Administrative Roles:
		* DBManager
- create schema:
	- open SQL Editor in MySQL Workbench
	- execute: create schema hub<Ctrl+Enter>
	- double click on 'hub' schema on the left (to make it the currently selected schema)
	- open and execute script: E:\repository\Manchester\ManchesterEJB\hub.sql

- edit JBoss CLASSPATH:
	- cp C:\Program Files\MySQL\MySQL Connector J\mysql-connector-java-5.1.15-bin.jar C:\jboss-4.2.2.GA\server\default\lib

- add datasource:
	- cp E:\repository\Manchester\ManchesterEJB\mysql-ds.xml C:\jboss-4.2.2.GA\server\default\deploy

- junction to the EJB:
	- C:\Utils\junction.exe ManchesterEJB.jar E:\repository\Manchester\ManchesterEJB

====

- see also:
	- scripts under:  E:\repository\Manchester\ManchesterEJB\ejbModule\META-INF\mysql
