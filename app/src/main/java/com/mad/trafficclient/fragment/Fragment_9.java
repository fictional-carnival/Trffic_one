package com.mad.trafficclient.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.mad.Initapp;
import com.mad.trafficclient.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragment_9 extends Fragment {


    private View view;
    private PieChart pieChart;
    private HorizontalBarChart hBar;
    private List<PieEntry> pieEntries;
    private List<BarEntry> barEntries;
    private BarChart barChart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_layout09, container, false);

        pieChart = view.findViewById(R.id.pieChart);
        hBar = view.findViewById(R.id.hBar);
        barChart = view.findViewById(R.id.bar);
        initPirChart();
        inithBar();
        init();

        return view;
    }

    public void initPirChart(){
        pieChart.clear();
        pieChart.setExtraOffsets(30,15,30,15);
        pieEntries = new ArrayList<>();
        int min = Initapp.random(35,50);
        pieEntries.add(new PieEntry(min,"有违章"));
        pieEntries.add(new PieEntry(100 - min,"无违章"));
        int[] colors = new int[]{Color.RED,Color.BLUE};
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(colors);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueLineWidth(0.1f);
        dataSet.setSliceSpace(1f);
        dataSet.setValueTextSize(20f);
        dataSet.setValueLinePart1OffsetPercentage(80);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("0.00%").format(v/100);
            }
        });

        Legend legend = pieChart.getLegend();
        legend.setFormSize(10);
        legend.setTextSize(20);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);

        Description description = new Description();
        description.setText("有违章车辆和无违章车辆的占比统计");
        description.setTextSize(25f);
        description.setTextColor(Color.BLUE);
        description.setPosition(800f,20f);
        pieChart.setDescription(description);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.setTouchEnabled(false);
        pieChart.setDrawHoleEnabled(false);
    }

    public void inithBar(){
        hBar.clear();
        hBar.setExtraOffsets(30,15,30,15);
        final String[] strs = new String[]{"1-2条违章","3-5条违章","5条以上违章"};
         barEntries = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            barEntries.add(new BarEntry(i, Initapp.random(45,70)));
        }
        int[] colors = new int[]{Color.GREEN,Color.BLUE,Color.RED};
        BarDataSet barDataSet = new BarDataSet(barEntries,"违章车辆的违章次数占比分布统计");
        barDataSet.setColors(colors);
        barDataSet.setValueTextColor(Color.RED);
        barDataSet.setValueTextSize(20);
        barDataSet.setDrawValues(true);
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("0.00%").format(value / 100);
            }
        });
        XAxis xAxis = hBar.getXAxis();
         xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
         xAxis.setTextSize(20f);
         xAxis.setDrawGridLines(false);
         xAxis.setLabelCount(strs.length);
         xAxis.setValueFormatter(new IAxisValueFormatter() {
             @Override
             public String getFormattedValue(float v, AxisBase axisBase) {
                 return strs[(int) v];
             }
         });
        YAxis yAxis = hBar.getAxisRight();
        yAxis.setDrawAxisLine(true);
        yAxis.setDrawGridLines(true);
        yAxis.setTextSize(20);
        yAxis.setAxisMaximum(100);
        yAxis.setAxisMinimum(0);
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return Math.abs(v) + "%";
            }
        });

        Description description = new Description();
        description.setText("违章车辆的违章次数占比分布图统计");
        description.setTextSize(25f);
        description.setTextColor(Color.BLUE);
        description.setPosition(800f,20f);
        hBar.setDescription(description);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);
        hBar.setTouchEnabled(false);
        hBar.setData(barData);
        hBar.getAxisLeft().setEnabled(false);
        hBar.getLegend().setEnabled(false);
    }

    public void init(){
        barChart.setExtraOffsets(50,25,20,25);
        final String[] strings = new String[]{"90后","80后","70后","60后","50后"};
        List<BarEntry> barEntries1 = new ArrayList<>();
        final List<BarEntry> barEntries2 = new ArrayList<>();
        for (int i = 0; i <strings.length ; i++) {
            barEntries1.add(new BarEntry(i, Initapp.random(650,850)));
            barEntries2.add(new BarEntry(i, Initapp.random(300,600)));
        }
        BarDataSet barDataSet1 = new BarDataSet(barEntries1,"有违章");
        barDataSet1.setColor(Color.YELLOW);
        barDataSet1.setValueTextColor(Color.RED);
        barDataSet1.setValueTextSize(25f);
        barDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("##.0").format(value / (barEntries2.get((int) entry.getX()).getY() + value) * 100) + "%";
            }
        });
        BarDataSet barDataSet2 = new BarDataSet(barEntries2,"无违章");
        barDataSet2.setColor(Color.GREEN);
        barDataSet2.setDrawValues(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(strings.length);
        xAxis.setCenterAxisLabels(false);
        xAxis.setGranularity(1f);
        xAxis.setTextSize(25f);
        xAxis.setTypeface(Typeface.DEFAULT_BOLD);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return strings[(int)value];
            }
        });
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(1200);
        yAxis.setAxisMinimum(0);
        yAxis.setTypeface(Typeface.DEFAULT_BOLD);
        yAxis.setTextSize(25f);
        Legend legend = barChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);
        legend.setTextSize(25);
        legend.setFormSize(15);
        Description description = new Description();
        description.setTextSize(25);
        description.setText("年龄群体车辆违章的占比统计");
        description.setTextColor(Color.BLUE);
        description.setPosition(800f,20f);
        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet1,barDataSet2);
        barChart.setData(barData);
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(true);
        barChart.setTouchEnabled(false);
    }



}
