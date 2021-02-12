package com.digital.assignment;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

public class WeekAxisFormatter implements IAxisValueFormatter {
    int count = 0;
    private final String[] weekDays = new String[]{
            "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"
    };

    private final BarLineChartBase<?> chart;

    public WeekAxisFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    private int determineDayOfMonth(int maxValue) {

        if (count < maxValue)
            count++;
        return count;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int day = determineDayOfMonth((int)value);
        if (day < weekDays.length)
            return weekDays[day];
        else
            return "";
    }
}
