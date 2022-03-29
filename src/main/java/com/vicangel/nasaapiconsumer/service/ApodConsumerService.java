package com.vicangel.nasaapiconsumer.service;

import com.vicangel.nasaapiconsumer.model.DownloadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedHashMap;

@Service
@Slf4j
public class ApodConsumerService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private final RestTemplate restTemplate;

    public ApodConsumerService() {
        this.restTemplate = restTemplate();
    }

    public ResponseEntity<LinkedHashMap> getNasaImage() {
        log.info("Calling " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return restTemplate.getForEntity("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY", LinkedHashMap.class);
    }

    public DownloadResponse downloadImage() throws IOException {
        log.info("Calling " + Thread.currentThread().getStackTrace()[1].getMethodName());
        String hdUrl = (String) this.getNasaImage().getBody().get("hdurl");
        URL url = new URL(hdUrl);
        byte[] imageBytes = null;
        try (InputStream in = new BufferedInputStream(url.openStream())) {
            imageBytes = in.readAllBytes();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new DownloadResponse(imageBytes, headers);
    }
}
