##Premier League Table API

Simple api for getting premier league table standings

Table information gets updated every 30 mins.  
Table information gets persisted into a databaes at midnight.

[PremierLeagueAPI.com](http://www.premierleagueapi.com)

###For working on the project

To run:  
mvn jetty:run -Dspring.profiles.active=local -Djetty.port=8080  

You need to have Cassandra installed and running.

Lombock should be installed on your IDE.
