# Tech Hub

Tech Hub is an issue tracking system.  Similar to JIRA, Bugzilla and many others, it supports ticketing & issue tracking on various projects.  
Demo: [https://tech-hub.herokuapp.com/](https://tech-hub.herokuapp.com/)

Features include:
* issues are tracked under projects
* unlimited projects
* unlimited issues per project
* admin console
* user statistics
* analytics

## Install Dependencies

1. Download Ant: [Apache Ant](https://ant.apache.org/)  
 _On Mac:_ [How to Apache Ant on Mac OS X](https://www.mkyong.com/ant/how-to-apache-ant-on-mac-os-x/)
2. Download TomEE: [Apache TomEE Webprofile](https://tomee.apache.org/download-ng.html)  
 _On Mac:_ ```brew install tomee-webprofile```
3. Download PostgreSQL: [PostgreSQL](https://www.postgresql.org/)  
 _On Mac:_ [Install Postgres.app](https://postgresapp.com/)
4. Configure your *$PATH* to include PostgreSQL command line tools:
```bash
sudo mkdir -p /etc/paths.d &&
echo /Applications/Postgres.app/Contents/Versions/latest/bin | sudo tee /etc/paths.d/postgresapp
```
*Then:* restart your terminal.

5. Download [database driver jar](https://jdbc.postgresql.org/download.html) into the tomee/lib folder.
6. Add datasource to **tomee/conf/tomee.xml**:
```bash
<?xml version="1.0" encoding="UTF-8"?>
<tomee>  
  <Resource id="jdbc/ManchesterDS_Postgre" type="javax.sql.DataSource">
    jdbcDriver org.postgresql.Driver
    jdbcUrl jdbc:postgresql://localhost:5432/manchester
    userName man
    password man
  </Resource>
</tomee>
```

## Build Application

1. Create database:  
```bash
psql postgres
ALTER DATABASE peter RENAME TO manchester;
```
2. Seed database:  
```bash
cd tech-hub/ManchesterEJB/ejbModule/META-INF/postgre/
psql manchester
\i Postgre_Table.ddl
\i Postgre_Sequence.ddl
\i Postgre_Data.ddl
```
3. Create database user:
```bash
psql manchester
CREATE USER man WITH ENCRYPTED PASSWORD 'man';
GRANT ALL PRIVILEGES ON DATABASE manchester TO man;
```
4. Deploy EJB:
```bash
cd $CATALINA_HOME/webapps
ln -s /PATH_TO/tech-hub/ManchesterEJB/ManchesterEJB.jar .
```


## Useful Tips

1. [TODO]

## Creator

[Peter Szocs](http://www.peterszocs.com), Tech Lead at Bloomberg LP.

* http://www.peterszocs.com/
* https://medium.com/@pitanyc
* https://github.com/pitanyc
* https://www.linkedin.com/in/szocspeter
