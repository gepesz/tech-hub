-- MySQL dump 10.10
--
-- Host: localhost    Database: hub
-- ------------------------------------------------------
-- Server version	5.0.20-Debian_1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ACCESS1`
--

DROP TABLE IF EXISTS `ACCESS1`;
CREATE TABLE `ACCESS1` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ACCESS1`
--


/*!40000 ALTER TABLE `ACCESS1` DISABLE KEYS */;
LOCK TABLES `ACCESS1` WRITE;
INSERT INTO `ACCESS1` VALUES (1,'Read'),(2,'Write');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ACCESS1` ENABLE KEYS */;

--
-- Table structure for table `DIFFICULTY`
--

DROP TABLE IF EXISTS `DIFFICULTY`;
CREATE TABLE `DIFFICULTY` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DIFFICULTY`
--


/*!40000 ALTER TABLE `DIFFICULTY` DISABLE KEYS */;
LOCK TABLES `DIFFICULTY` WRITE;
INSERT INTO `DIFFICULTY` VALUES (1,'1 Major'),(2,'2 Hard'),(3,'3 Normal'),(4,'4 Easy'),(5,'5 Trivial');
UNLOCK TABLES;
/*!40000 ALTER TABLE `DIFFICULTY` ENABLE KEYS */;

--
-- Table structure for table `LOCALE`
--

DROP TABLE IF EXISTS `LOCALE`;
CREATE TABLE `LOCALE` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  `LOC` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `LOCALE`
--


/*!40000 ALTER TABLE `LOCALE` DISABLE KEYS */;
LOCK TABLES `LOCALE` WRITE;
INSERT INTO `LOCALE` VALUES (1,'English','en_US'),(2,'Magyar','hu_HU'),(3,'Polski','pl_PL'),(4,'Turkce','tr_TR');
UNLOCK TABLES;
/*!40000 ALTER TABLE `LOCALE` ENABLE KEYS */;

--
-- Table structure for table `PRIORITY`
--

DROP TABLE IF EXISTS `PRIORITY`;
CREATE TABLE `PRIORITY` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PRIORITY`
--


/*!40000 ALTER TABLE `PRIORITY` DISABLE KEYS */;
LOCK TABLES `PRIORITY` WRITE;
INSERT INTO `PRIORITY` VALUES (1,'1 Hi'),(2,'2 Md'),(3,'3 Lo');
UNLOCK TABLES;
/*!40000 ALTER TABLE `PRIORITY` ENABLE KEYS */;

--
-- Table structure for table `PROJECT`
--

DROP TABLE IF EXISTS `PROJECT`;
CREATE TABLE `PROJECT` (
  `ID` smallint(6) NOT NULL auto_increment,
  `DESC1` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PROJECT`
--


/*!40000 ALTER TABLE `PROJECT` DISABLE KEYS */;
LOCK TABLES `PROJECT` WRITE;
INSERT INTO `PROJECT` VALUES (1,'Budapest'),(2,'Amsterdam');
UNLOCK TABLES;
/*!40000 ALTER TABLE `PROJECT` ENABLE KEYS */;

--
-- Table structure for table `PROJECTACCESS`
--

DROP TABLE IF EXISTS `PROJECTACCESS`;
CREATE TABLE `PROJECTACCESS` (
  `PROJECTID` smallint(6) NOT NULL,
  `USERID` smallint(6) NOT NULL,
  `ACCESS_ID` tinyint(4) default NULL,
  PRIMARY KEY  (`PROJECTID`,`USERID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PROJECTACCESS`
--


/*!40000 ALTER TABLE `PROJECTACCESS` DISABLE KEYS */;
LOCK TABLES `PROJECTACCESS` WRITE;
INSERT INTO `PROJECTACCESS` VALUES (1,1,2),(1,2,2),(1,3,2),(1,4,2),(1,5,2),(2,1,2),(2,3,2),(2,4,2),(2,5,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `PROJECTACCESS` ENABLE KEYS */;

--
-- Table structure for table `TASK`
--

DROP TABLE IF EXISTS `TASK`;
CREATE TABLE `TASK` (
  `ID` int(11) NOT NULL auto_increment,
  `TITLE` varchar(250) default NULL,
  `SUMMARY` varchar(250) default NULL,
  `PLANNEDDATE` varchar(250) default NULL,
  `CREATEDATE` varchar(250) default NULL,
  `COMMENT1` varchar(250) default NULL,
  `PROGRESS` varchar(250) default NULL,
  `PROJECT_ID` smallint(6) default NULL,
  `CREATEDBY_ID` smallint(6) default NULL,
  `TASKTYPE_ID` tinyint(4) default NULL,
  `DIFFICULTY_ID` tinyint(4) default NULL,
  `WHO_ID` smallint(6) default NULL,
  `TASKSTATUS_ID` tinyint(4) default NULL,
  `PRIORITY_ID` tinyint(4) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TASK`
--


/*!40000 ALTER TABLE `TASK` DISABLE KEYS */;
LOCK TABLES `TASK` WRITE;
INSERT INTO `TASK` VALUES (1000,'asdf','(peter):\nasfd\r\n\r\nasdf\r\nasdf\r\n','2006-07-18','2006-07-03',NULL,'0',1,1,1,1,1,1,1),(1001,'Sima liba','(peter):\r\nsdf asdf asdf \r\n\r\nasdf asdf\r\nasfd\r\nasdf\r\nasdf\r\n  asdfjsaflkjsaldfjsdlfls\r\n\r\nasdf\r\ndsaf\r\ndsaf\r\n','2006-07-26','2006-07-12','(peter)ssafdasdfdaf(peter)(peter)(peter)','100',1,1,4,4,1,3,2),(1002,'aaaaaaaa','(peter):\nbbbbbbbb','2006-07-28','2006-07-12',NULL,'0',1,1,1,1,1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `TASK` ENABLE KEYS */;

--
-- Table structure for table `TASKSTATUS`
--

DROP TABLE IF EXISTS `TASKSTATUS`;
CREATE TABLE `TASKSTATUS` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TASKSTATUS`
--


/*!40000 ALTER TABLE `TASKSTATUS` DISABLE KEYS */;
LOCK TABLES `TASKSTATUS` WRITE;
INSERT INTO `TASKSTATUS` VALUES (1,'Open'),(2,'Resolved'),(3,'Verified');
UNLOCK TABLES;
/*!40000 ALTER TABLE `TASKSTATUS` ENABLE KEYS */;

--
-- Table structure for table `TASKTYPE`
--

DROP TABLE IF EXISTS `TASKTYPE`;
CREATE TABLE `TASKTYPE` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TASKTYPE`
--


/*!40000 ALTER TABLE `TASKTYPE` DISABLE KEYS */;
LOCK TABLES `TASKTYPE` WRITE;
INSERT INTO `TASKTYPE` VALUES (1,'New func'),(2,'Test'),(3,'Bug'),(4,'Install'),(5,'Other');
UNLOCK TABLES;
/*!40000 ALTER TABLE `TASKTYPE` ENABLE KEYS */;

--
-- Table structure for table `USER1`
--

DROP TABLE IF EXISTS `USER1`;
CREATE TABLE `USER1` (
  `ID` smallint(6) NOT NULL auto_increment,
  `USERNAME` varchar(100) default NULL,
  `PASSWORD` varchar(100) default NULL,
  `EMAIL` varchar(100) default NULL,
  `LASTLOGIN` datetime default NULL,
  `NOFLOGINS` smallint(5) unsigned default NULL,
  `LASTPROJECT_ID` smallint(6) default NULL,
  `USERTYPE_ID` tinyint(4) default NULL,
  `LOCALE_ID` tinyint(4) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USER1`
--


/*!40000 ALTER TABLE `USER1` DISABLE KEYS */;
LOCK TABLES `USER1` WRITE;
INSERT INTO `USER1` VALUES (1,'peter','51dc30ddc473d43a6011e9ebba6ca770','peter@zlocker.com','2011-12-03 11:25:31',16,1,2,1),(2,'leszek','8393fb7a5da0164cd424ec95d81657ef','leszek@serwer.3miasto.net',NULL,0,1,1,1),(3,'fatso','e9249b1acd08ebc1b24f41fb9243f82e','fatso@sch.bme.hu',NULL,0,1,1,1),(4,'lacko','e5300e06ca720f1f35029896ff195abe','sixsex@freemail.hu',NULL,0,1,1,1),(5,'petek','1ffce621348fb27cd715f11ef470df04','petek@zlocker.com',NULL,0,1,1,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `USER1` ENABLE KEYS */;

--
-- Table structure for table `USERTYPE`
--

DROP TABLE IF EXISTS `USERTYPE`;
CREATE TABLE `USERTYPE` (
  `ID` tinyint(4) NOT NULL auto_increment,
  `DESC1` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `USERTYPE`
--


/*!40000 ALTER TABLE `USERTYPE` DISABLE KEYS */;
LOCK TABLES `USERTYPE` WRITE;
INSERT INTO `USERTYPE` VALUES (1,'Basic User'),(2,'Admin');
UNLOCK TABLES;
/*!40000 ALTER TABLE `USERTYPE` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

