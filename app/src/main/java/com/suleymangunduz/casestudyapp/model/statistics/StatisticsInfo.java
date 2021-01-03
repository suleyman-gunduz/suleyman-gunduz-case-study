package com.suleymangunduz.casestudyapp.model.statistics;

public class StatisticsInfo {
    private Integer ratingCount;
    private Double averageRating;
    private String approvedPlatform;
    private String refusedPlatform;
    private String approvedBrowser;
    private String refusedBrowser;

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public String getApprovedPlatform() {
        return approvedPlatform;
    }

    public void setApprovedPlatform(String approvedPlatform) {
        this.approvedPlatform = approvedPlatform;
    }

    public String getRefusedPlatform() {
        return refusedPlatform;
    }

    public void setRefusedPlatform(String refusedPlatform) {
        this.refusedPlatform = refusedPlatform;
    }

    public String getApprovedBrowser() {
        return approvedBrowser;
    }

    public void setApprovedBrowser(String approvedBrowser) {
        this.approvedBrowser = approvedBrowser;
    }

    public String getRefusedBrowser() {
        return refusedBrowser;
    }

    public void setRefusedBrowser(String refusedBrowser) {
        this.refusedBrowser = refusedBrowser;
    }
}
