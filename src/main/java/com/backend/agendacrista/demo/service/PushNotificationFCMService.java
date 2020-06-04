package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.PushFCM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class PushNotificationFCMService {

    private Logger logger = LoggerFactory.getLogger(PushNotificationFCMService.class);

    @Value("${agenda.fcm.key}")
    private String serverKey;

    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("https://fcm.googleapis.com/fcm")
            .build();


    @Async
    public void sendNotification(PushFCM pushFCM) {
        try {
            HttpEntity<PushFCM> httpEntity = new HttpEntity<PushFCM>(pushFCM, setHeaders());
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("/send", httpEntity, String.class);
            logger.info("Sent message. Topic: " + pushFCM.getTo() + " Status Code: " + responseEntity.getStatusCode());
        } catch (RestClientException e) {
            logger.error("Sent message. Topic: " + pushFCM.getTo() + " Error: " + e.getMessage());
        }
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + serverKey);
        return headers;
    }

}
