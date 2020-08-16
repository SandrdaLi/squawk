# Squawk

A Spring-Boot application to send emails to clients. Utilizes AWS SES and can send 10,000 free emails in a day. The application is made up of 2 microservices, a Kafka server and a MySQL server which are all containerized using Docker.

Pre-requisites
1) AWS account
2) Docker

To get started - 
Create your own AWS account and save the access key and secret access key you get during IAM role creation. Go to Simple Email Service (SES) on the dashboard and authorize an email address to send and receive emails. Enter the access and secret access keys in the AwsCredentials.properties file at squawk-consumer/src/main/resources/

Clone this repository <br/>
Run 

```
docker-compose up
```

Endpoint for sending JSON requests - 
http://localhost:8080/send

Sample Request - 

```
{
	"email": "sreekar.dhaduvai2@gmail.com",
	"message": "Feels good to have done something"
}
```
