package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;

    private static final String COMPLAINTS = "complaints";

    @KafkaListener(topics = {COMPLAINTS}, groupId = "MIZRA")
    public void sendNotification(String message) throws JsonProcessingException {

        JSONObject jsonObject = objectMapper.readValue(message, JSONObject.class);
        String email = (String)jsonObject.get("email");
        String emailMsg = (String) jsonObject.get("message");

        simpleMailMessage.setText(emailMsg);
        simpleMailMessage.setFrom(System.getenv("MAIL_USERNAME"));
        simpleMailMessage.setTo(email);

        javaMailSender.send(simpleMailMessage);

    }

}