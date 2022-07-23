package com.me.dashboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sentence")
    private String sentence;

    @JsonProperty("confidence")
    private Double confidence;

    @JsonProperty("negative")
    private Double negative;

    @JsonProperty("positive")
    private Double positive;

    @JsonProperty("neutral")
    private Double neutral;

    @JsonProperty("sentiment")
    private String sentiment;

    @JsonProperty("creationDate")
    private String creationDate;
}
