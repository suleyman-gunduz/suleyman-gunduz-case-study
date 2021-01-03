package com.suleymangunduz.casestudyapp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrowserDto {
    @JsonProperty("Browser")
    private String browserName;
    @JsonProperty("Version")
    private Double browserVersion;
    @JsonProperty("Platform")
    private String platform;

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public Double getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(Double browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
