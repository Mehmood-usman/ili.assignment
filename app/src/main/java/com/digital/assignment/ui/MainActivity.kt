package com.digital.assignment.ui

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.digital.assignment.utils.DayAxisValueFormatter
import com.digital.assignment.utils.DemoBase
import com.digital.assignment.R
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
        cardView.setBackgroundResource(R.drawable.card_background)

        chart.setDrawBarShadow(false)
        chart.getDescription().setEnabled(false)
        chart.setPinchZoom(true)
        chart.setBorderColor(ContextCompat.getColor(this, R.color.borderColor))
        val xAxisFormatter:IAxisValueFormatter =
            DayAxisValueFormatter(chart)

        val xAxis: XAxis = chart.getXAxis()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.typeface = tfLight
        xAxis.setDrawGridLines(true)
        xAxis.setDrawAxisLine(true)
        xAxis.axisLineColor= ContextCompat.getColor(this, R.color.white)
        xAxis.gridColor = ContextCompat.getColor(this, R.color.white)
        xAxis.granularity = 1f

        xAxis.labelCount = 7
        xAxis.textColor = ContextCompat.getColor(this, R.color.white)
        xAxis.setValueFormatter(xAxisFormatter)
//


        chart.axisRight.setCenterAxisLabels(false)
        chart.axisLeft.setDrawGridLines(false)
        chart.axisLeft.setDrawLabels(false)
        chart.legend.isEnabled = false;
        val rightAxis: YAxis = chart.getAxisRight()
        rightAxis.setDrawGridLines(true)
        rightAxis.gridColor=ContextCompat.getColor(this, R.color.white)
        rightAxis.typeface = tfLight
        rightAxis.spaceBottom=0.0f
        rightAxis.textColor = ContextCompat.getColor(this, R.color.white)
        rightAxis.axisMaxLabels = 0
        rightAxis.spaceTop = 15f // this replaces setStartAtZero(true)

        setData(7,200.0f)
        for (set in chart.data.dataSets){
            (set as BarDataSet).barBorderWidth = 2f
            (set as BarDataSet).barBorderColor = ContextCompat.getColor(this, R.color.borderColor)
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
        val barDataSet: BarDataSet
        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            barDataSet = chart.data.getDataSetByIndex(0) as BarDataSet
            barDataSet.values = values
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else {
            barDataSet = BarDataSet(values, "")
            barDataSet.setDrawIcons(false)
            barDataSet.setDrawValues(false)
            val barColor = ContextCompat.getColor(this, R.color.barColor)
            val gradientFills: MutableList<Fill> = ArrayList<Fill>()
            gradientFills.add(Fill(barColor, barColor))
            barDataSet.fills = gradientFills
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(barDataSet)
            val data = BarData(dataSets)
            data.barWidth = 0.5f
            chart.data = data
        }
    }
}