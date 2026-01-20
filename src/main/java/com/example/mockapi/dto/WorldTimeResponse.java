package com.example.mockapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldTimeResponse {
    private String datetime;
    private String timezone;
}
