package com.suleymangunduz.casestudyapp.model.chart;

import com.github.mikephil.charting.data.BarData;

import java.util.List;

public class BarChartDto {
    private BarData barData;
    private List<String> labels;

    public BarChartDto(BarData barData, List<String> labels) {
        this.barData = barData;
        this.labels = labels;
    }

    public BarData getBarData() {
        return barData;
    }

    public void setBarData(BarData barData) {
        this.barData = barData;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
