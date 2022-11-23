# Rewards System
# commands to run the application 
### mvn clean -> cleans target folder
### mvn spring-boot:run  -> runs the application
### app runs on 8080 port -> http://localhost:8080/
### rewards controller is accessed with -> http://localhost:8080/rewards

# Apis supported are
### GET -> http://localhost:8080/rewards/monthly?year=2022&month=12
##### To get rewards for a particular month

### POST -> http://localhost:8080/rewards/transaction/publish
### To update the transaction 
input : {"transactionId":1233,    "transactionAmount":100.0,    "transactionTime": "2022-10-21 10:10:10"}

### POST -> http://localhost:8080/rewards/transaction/bulk/publish
#### To update/record multiple transaction at once
input: [{"transactionId":1233, "transactionAmount":100.0,    "transactionTime": "2022-11-21 10:10:10"    },{    "transactionId":1233,  "transactionAmount":100.0,    "transactionTime": "2022-09-21 10:10:10"}]

### GET -> "http://localhost:8080/rewards/duration?startDate=2022-10-11&endDate=2022-11-22"
#### To get rewards in a particular duration

To Access Db http://localhost:8080/h2.ui
Jdbc url :  jdbc:h2:mem:testdb
user : sa
password : 