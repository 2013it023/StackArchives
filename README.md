## How to clone and setup for local development environment.

  1. Clone this project using following link [https://github.com/2013it023/StackArchives.git] or you can download this project directly 
from github as a ZIP.

  2. Now Open your Windows command prompt and moves to the files location until the root directory. For example if your file located under 
following location like (), move to until the before /src directory.

  3. Now run the following commands in Windows command prompt. Before running the command’s please ensure whether you are installed latest
version of maven in your system. To check use mvn –version command in Command prompt and you need to start and configure the Postgresql 
server in your machine before running these two commands. How to setup and configure the postgresql is explained configuration.

    Command’s:
      1. mvn clean install
      2. mvn eclipse:clean eclipse:eclipse
      
  4. After run the above command’s, Please run following commands to start the front end server. Normally the front end will run 
[localhost:8080]
      1. mvn spring-boot:run
      
## ABOUT DATABASE CONFIGURATION:

1. Please download and install the latest version of Postgresql and Start the server using Pg Admin. You will get this interface after 
successful installation of postgresql. Create a database and assign the logingroup level’s for that database. After this you need to 
mention Database name and Login credential’s into application.properties file. You can find this file in following location 
[DIR/StockArchiveBackEnd/StockArchives/src/main/resources/application.properties]

2. We no need to create a any table when you are running mvn clean install table will be automatically created into the database.
