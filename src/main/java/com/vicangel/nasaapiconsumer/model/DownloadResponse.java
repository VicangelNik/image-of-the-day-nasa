package com.vicangel.nasaapiconsumer.model;

import lombok.Getter;
import org.springframework.http.HttpHeaders;

@Getter
public class DownloadResponse {

    private final byte[] imageBytes;
    private final HttpHeaders headers;

    public DownloadResponse(byte[] imageBytes, HttpHeaders headers) {
        this.imageBytes = imageBytes;
        this.headers = headers;
    }
}
