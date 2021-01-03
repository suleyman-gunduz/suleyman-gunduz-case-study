package com.suleymangunduz.casestudyapp.util;

import android.graphics.Color;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.List;

public class ChartUtils {

    public static PieData generatePieData(List<PieEntry> pieEntries, String label) {

        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueLinePart1OffsetPercentage(80.f);
        pieDataSet.setValueLinePart1Length(0.2f);
        pieDataSet.setValueLinePart2Length(0.4f);

        return new PieData(pieDataSet);
    }

    public static BarData generateBarData(List<BarEntry> barEntries, String label) {

        BarDataSet barDataSet = new BarDataSet(barEntries, label);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        return new BarData(barDataSet);
    }
}
