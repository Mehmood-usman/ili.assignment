package com.digital.assignment

import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.Fill
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : DemoBase() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        cardView.setBackgroundResource(R.drawable.card_background);
//        barChart.getDescription().setEnabled(false)
//        barChart.setMaxVisibleValueCount(60)
//        barChart.setPinchZoom(false)
//        barChart.setDrawBarShadow(false)
//
//        val xAxis: XAxis = barChart.getXAxis()
//        xAxis.position = XAxisPosition.BOTTOM
//        xAxis.setDrawGridLines(false)
//
//        barChart.getAxisLeft().setDrawGridLines(false)
//
//
//        // add a nice and smooth animation
//        barChart.animateY(1500)
//
//        barChart.getLegend().setEnabled(false)
//
//        val values = ArrayList<BarEntry>()
//
//        for (i in 0 until 7) {
//            val multi: Float = (7 + 1).toFloat()
//            val `val` = (Math.random() * multi).toFloat().plus(multi.div(3))
//            values.add(BarEntry(i.toFloat(), `val`))
//        }
//        val set1: BarDataSet
//        set1 = BarDataSet(values, "Data Set")
//        set1.setColors(*ColorTemplate.VORDIPLOM_COLORS)
//        set1.setDrawValues(false)
//
//        val dataSets = ArrayList<IBarDataSet>()
//        dataSets.add(set1)
//
//        val data = BarData(dataSets)
//        barChart.setData(data)
//        barChart.setFitBars(true)
//
//        barChart.invalidate()
//        chart = findViewById(R.id.chart1)
//        chart.setOnChartValueSelectedListener(this)

        chart.setDrawBarShadow(false)
//        chart.setDrawValueAboveBar(true)
//        chart.set
        chart.getDescription().setEnabled(false)

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
//        chart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(true)
        chart.setBorderColor(ContextCompat.getColor(this,R.color.borderColor))
//        chart.setDrawGridBackground(true)
//        chart.grid
//        chart.setGridBackgroundColor(ContextCompat.getColor(this,R.color.white))
        // chart.setDrawYLabels(false);

        // chart.setDrawYLabels(false);
        val xAxisFormatter:IAxisValueFormatter = DayAxisValueFormatter(chart)

        val xAxis: XAxis = chart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.typeface = tfLight
        xAxis.setDrawGridLines(true)
        xAxis.setDrawAxisLine(true)
        xAxis.axisLineColor= ContextCompat.getColor(this,R.color.white)
        xAxis.gridColor = ContextCompat.getColor(this,R.color.white)
        xAxis.granularity = 1f // only intervals of 1 day

        xAxis.labelCount = 7
        xAxis.textColor = ContextCompat.getColor(this,R.color.white)
        xAxis.setValueFormatter(xAxisFormatter)

//        val yAxis = chart.getAxisLeft()
//
//        yAxis.gridColor = ContextCompat.getColor(this,R.color.white)

        val custom: IAxisValueFormatter = MyAxisValueFormatter()
//
//        val leftAxis: YAxis = chart.getAxisLeft()
//        leftAxis.typeface = tfLight
//        leftAxis.setLabelCount(8, false)
//        leftAxis.setValueFormatter(custom)
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
//        leftAxis.spaceTop = 15f
//        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)


        chart.axisRight.setCenterAxisLabels(false)
        val rightAxis: YAxis = chart.getAxisRight()
        rightAxis.setDrawGridLines(true)
        rightAxis.gridColor=ContextCompat.getColor(this,R.color.white)
        rightAxis.typeface = tfLight
        rightAxis.spaceTop = 15f // this replaces setStartAtZero(true)


//        val l: Legend = chart.getLegend()
//        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
//        l.orientation = Legend.LegendOrientation.HORIZONTAL
//        l.setDrawInside(false)
//        l.form = Legend.LegendForm.SQUARE
//        l.formSize = 9f
//        l.textSize = 11f
//        l.xEntrySpace = 4f

//        val mv = XYMarkerView(this, xAxisFormatter)
//        mv.setChartView(chart) // For bounds control

//        chart.setMarker(mv) // Set the marker to the chart

        setData(7,200.0f)
        for (set in chart.data.dataSets){
            (set as BarDataSet).barBorderWidth = 2f
            (set as BarDataSet).barBorderColor = ContextCompat.getColor(this,R.color.borderColor)
        }

        chart.invalidate()
    }
    private fun setData(count: Int, range: Float) {
        val start = 1f
        val values = ArrayList<BarEntry>()
        var i = start.toInt()
        while (i < start + count) {
            val `val` = (Math.random() * (range + 1)).toFloat()
            if (Math.random() * 100 < 25) {
                values.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
            } else {
                values.add(BarEntry(i.toFloat(), `val`))
            }
            i++
        }
        val set1: BarDataSet
        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(values, "")
            set1.setDrawIcons(false)
            val startColor2 = ContextCompat.getColor(this,R.color.barColor)
            val gradientFills: MutableList<Fill> = ArrayList<Fill>()
            gradientFills.add(Fill(startColor2, startColor2))
            gradientFills.add(Fill(startColor2, startColor2))
            gradientFills.add(Fill(startColor2, startColor2))
            gradientFills.add(Fill(startColor2, startColor2))
            gradientFills.add(Fill(startColor2, startColor2))
            set1.setFills(gradientFills)
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)
            val data = BarData(dataSets)
            data.isHighlightEnabled = false
            data.barWidth = 0.5f
            chart.data = data
        }
    }
}