# Squawk

A Spring-Boot application to send emails to clients. Utilizes AWS SES and can send 10,000 free emails in a day. The application is made up of 2 microservices, a Kafka server and a MySQL server which are all containerized using Docker.

Pre-requisites
1) AWS account
2) Docker

Clone this repository <br/>

Create your own AWS account and save the access key and secret access key you get during IAM role creation. Enter the keys in the AwsCredentials.properties file at squawk-consumer/src/main/resources/. Go to Simple Email Service (SES) on the dashboard and authorize an email address to send and receive emails. Replace "sreekar.dhaduvai2@gmail.com" with your authorized email in /squawk-consumer/src/main/java/com/squawk/notification/service/Consumer.java


Run 

```
docker-compose up
```

Endpoint for sending JSON requests - 
http://localhost:8080/send

Sample Request - 

```
{
	"email": "your-email-address@domain.com",
	"message": "Greetings Human, How are things on planet Earth?"
}
```
