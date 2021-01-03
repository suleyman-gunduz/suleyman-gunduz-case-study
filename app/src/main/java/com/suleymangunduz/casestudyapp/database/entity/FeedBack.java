package com.suleymangunduz.casestudyapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "feedback")
public class FeedBack {
    @PrimaryKey(autoGenerate = true)
    public Long feedBackId;

    @ColumnInfo(name = "browser_name")
    public String browserName;

    @ColumnInfo(name = "browser_version")
    public Double browserVersion;

    @ColumnInfo(name = "platform")
    public String platform;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "computed_location")
    public String computedLocation;

    @ColumnInfo(name = "labels")
    public String labels;

    @ColumnInfo(name = "rating")
    public Integer rating;

    @ColumnInfo(name = "latitude")
    public Double latitude;

    @ColumnInfo(name = "longitude")
    public Double longitude;
}
