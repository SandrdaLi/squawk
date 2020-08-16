package com.squawk.notification.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    AWSCredentialsProvider awsCreds = new ClasspathPropertiesFileCredentialsProvider();

    private AmazonSimpleEmailService sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
            .withCredentials(awsCreds)
            .withRegion(Regions.US_EAST_1)
            .build();


    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        String[] splitted = message.split("###");
        String email = splitted[0];
        String data = splitted[1];

        Content subject = new Content().withData("Spring email");
        Content textBody = new Content().withData(data);
        Body body = new Body().withText(textBody);

        Message message1 = new Message().withSubject(subject).withBody(body);
        Destination destination = new Destination().withToAddresses(email);

        SendEmailRequest emailRequest = new SendEmailRequest()
                .withReplyToAddresses("sreekar.dhaduvai2@gmail.com")
                .withSource("sreekar.dhaduvai2@gmail.com")
                .withDestination(destination)
                .withMessage(message1);

        sesClient.sendEmail(emailRequest);
        logger.info(String.format("#### -> Consumer sent email -> %s", message));
    }
}