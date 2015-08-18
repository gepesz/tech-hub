

DROP TABLE hub.ACCESS1;
DROP TABLE hub.DIFFICULTY;
DROP TABLE hub.LOCALE;
DROP TABLE hub.PRIORITY;
DROP TABLE hub.PROJECT;
DROP TABLE hub.TASK;
DROP TABLE hub.PROJECTACCESS;
DROP TABLE hub.TASKSTATUS;
DROP TABLE hub.TASKTYPE;
DROP TABLE hub.USER1;
DROP TABLE hub.USERTYPE;


CREATE TABLE hub.ACCESS1 (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.DIFFICULTY (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.LOCALE (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  `LOC`   varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.USERTYPE (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.PRIORITY (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.PROJECT (
  `ID` smallint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.TASKSTATUS (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.TASKTYPE (
  `ID` tinyint NOT NULL AUTO_INCREMENT,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM;

CREATE TABLE hub.USER1 (
  `ID`             smallint NOT NULL AUTO_INCREMENT,
  `USERNAME`       varchar(100) default NULL,
  `PASSWORD`       varchar(100) default NULL,
  `EMAIL`          varchar(100) default NULL,
  `LASTLOGIN`      datetime     default NULL,
  `NOFLOGINS`      smallint unsigned default NULL,
  `LASTPROJECT_ID` smallint default NULL,
  `USERTYPE_ID`    tinyint  default NULL,
  `LOCALE_ID`      tinyint  default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM; 

CREATE TABLE hub.PROJECTACCESS (
  `PROJECTID` smallint NOT NULL,
  `USERID`    smallint NOT NULL,
  `ACCESS_ID` tinyint  default NULL,
  PRIMARY KEY  (`PROJECTID`,`USERID`)
) TYPE=MyISAM;

CREATE TABLE hub.TASK (
  `ID`             int NOT NULL AUTO_INCREMENT,
  `TITLE`          varchar(250) default NULL,
  `SUMMARY`        varchar(250) default NULL,
  `PLANNEDDATE`    varchar(250) default NULL,
  `CREATEDATE`     varchar(250) default NULL,
  `COMMENT1`       varchar(250) default NULL,
  `PROGRESS`       varchar(250) default NULL,
  `PROJECT_ID`     smallint     default NULL,
  `CREATEDBY_ID`   smallint     default NULL,
  `TASKTYPE_ID`    tinyint      default NULL,
  `DIFFICULTY_ID`  tinyint      default NULL,
  `WHO_ID`         smallint     default NULL,
  `TASKSTATUS_ID`  tinyint      default NULL,
  `PRIORITY_ID`    tinyint      default NULL,
  PRIMARY KEY  (`ID`)
) TYPE=MyISAM;

ALTER TABLE hub.TASK AUTO_INCREMENT=1000;
