package com.backend.agendacrista.demo.service;

import com.backend.agendacrista.demo.model.PushFcmAbstract;
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
    public void sendNotification(PushFcmAbstract pushFcmAbstract) {
        try {
            if (pushFcmAbstract.isEmptyDestination())
                throw new RestClientException("Destination is empty");
            HttpEntity<PushFcmAbstract> httpEntity = new HttpEntity<PushFcmAbstract>(pushFcmAbstract, setHeaders());
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("/send", httpEntity, String.class);
            logger.info("Sent message. Destination:" + pushFcmAbstract.getDestination() + " Status Code: " + responseEntity.getStatusCode());
        } catch (RestClientException e) {
            logger.error("Sent message. Destination:" + pushFcmAbstract.getDestination() + " Error: " + e.getMessage());
        }
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=" + serverKey);
        return headers;
    }

}
