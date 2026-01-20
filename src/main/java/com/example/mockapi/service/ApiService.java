package com.example.mockapi.service;

import com.example.mockapi.dto.WorldTimeResponse;
import com.example.mockapi.messaging.QueueProducer;
import com.example.mockapi.model.SampleData;
import com.example.mockapi.repository.SampleDataRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final SampleDataRepository sampleDataRepository;
    private final QueueProducer queueProducer;
    private static final String EXTERNAL_API_URL = "http://worldtimeapi.org/api/timezone/America/Argentina/Buenos_Aires";

    public ApiService(RestTemplate restTemplate, SampleDataRepository sampleDataRepository, QueueProducer queueProducer) {
        this.restTemplate = restTemplate;
        this.sampleDataRepository = sampleDataRepository;
        this.queueProducer = queueProducer;
    }

    public Map<String, Object> getCombinedData() {
        // Fetch data from external API
        WorldTimeResponse externalData = null;
        String externalApiError = null;
        try {
            externalData = restTemplate.getForObject(EXTERNAL_API_URL, WorldTimeResponse.class);
        } catch (ResourceAccessException e) {
            externalApiError = "Error al conectar con la API externa: " + e.getMessage();
            // Log the error for debugging purposes
            System.err.println(externalApiError);
            e.printStackTrace();
        }

        // Fetch data from database
        List<SampleData> databaseData = sampleDataRepository.findAll();

        // Combine data
        Map<String, Object> combinedData = new HashMap<>();
        if (externalData != null) {
            combinedData.put("externalTimeInfo", externalData);
        } else {
            combinedData.put("externalTimeInfo", externalApiError);
        }
        combinedData.put("databaseInfo", databaseData);

        // Send a message to the queue
        queueProducer.sendMessage("Llamada a /api/data procesada a las " + LocalDateTime.now());

        return combinedData;
    }
}
