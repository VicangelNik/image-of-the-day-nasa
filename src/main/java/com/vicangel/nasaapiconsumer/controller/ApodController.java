package com.vicangel.nasaapiconsumer.controller;

import com.vicangel.nasaapiconsumer.model.DownloadResponse;
import com.vicangel.nasaapiconsumer.service.ApodConsumerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;

@RestController
@Slf4j
@RequestMapping("/apod")
public class ApodController {

    private final ApodConsumerService apodServive;

    @Autowired
    public ApodController(@NonNull ApodConsumerService apodServive) {
        this.apodServive = apodServive;
    }

    @GetMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LinkedHashMap> getImageInfo() {
        log.info("Calling " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return apodServive.getNasaImage();
    }

    @GetMapping(value = "/image/download")
    public HttpEntity<byte[]> downloadImage() throws IOException {
        log.info("Calling " + Thread.currentThread().getStackTrace()[1].getMethodName());
        DownloadResponse resp = apodServive.downloadImage();
        return new HttpEntity<>(resp.getImageBytes(), resp.getHeaders());
    }
}
