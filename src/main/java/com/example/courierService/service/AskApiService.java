package com.example.courierService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class AskApiService {

    public LocalDate askApiAboutDate() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://worldclockapi.com/api/json/utc/now";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root;
            try {
                root = mapper.readTree(Objects.requireNonNull(response.getBody()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
            JsonNode date = root.path("currentDateTime");
            return LocalDate.parse(date.toString().substring(1, 11));
        }
        return null;
    }
}
