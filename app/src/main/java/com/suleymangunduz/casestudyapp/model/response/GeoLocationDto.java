package com.suleymangunduz.casestudyapp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocationDto {
    private String country;
    private String city;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("lon")
    private Double longitude;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
