package com.suleymangunduz.casestudyapp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FeedBackDto {
    @JsonProperty("computed_browser")
    private BrowserDto browserDto;
    @JsonProperty("geo")
    private GeoLocationDto geoLocationDto;
    @JsonProperty("computed_location")
    private String computedLocation;
    private List<String> labels;
    private Integer rating;

    public BrowserDto getBrowserDto() {
        return browserDto;
    }

    public void setBrowserDto(BrowserDto browserDto) {
        this.browserDto = browserDto;
    }

    public GeoLocationDto getGeoLocationDto() {
        return geoLocationDto;
    }

    public void setGeoLocationDto(GeoLocationDto geoLocationDto) {
        this.geoLocationDto = geoLocationDto;
    }

    public String getComputedLocation() {
        return computedLocation;
    }

    public void setComputedLocation(String computedLocation) {
        this.computedLocation = computedLocation;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
