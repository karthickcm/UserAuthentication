Required Softwares
------------------
Spring Boot 2.3.1.RELEASE
JDK 1.8 
Maven - 3.2+
IDE - Eclipse or Spring Tool Suite (STS)
MYSQL
PostMan


----------------------------------------------------------------------------------------------------------------------
Download the Source from github repository
Change the properties under below file

File Name : authentication\src\main\resources\application.properties

Changes :
----------
server.port=8080
spring.datasource.url: jdbc:mysql://<mysqlipaddress>:<mysqlport>/authentication
spring.datasource.username: <Username>
spring.datasource.password: <Password>


Create below Schema under with above user access privilage in the my sql server
------------------------------------------------------------------------------
authentication

------------------------------------------------------------------------------------------------------------------------------
Run the Application
-------------------

AuthenticationApplication



Test Case
----------
To store default Entry Run the below Java file
com.km.authentication.repo.UserMasterRepoTests

To run Junit test case run the below Java file
com.km.authentication.service.AuthenticationServiceTest

-----------------------------------------------------------------------------------------------------------------------------
To test in postman
------------------
POST Method url = http://localhost:8080/user/authenticate

change Meadia Type as "x-www-form-urlencoded"
Key as userName and password