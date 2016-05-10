package com.ttjjttjj.piecharttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PieData> mData = new ArrayList<PieData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //数据
        PieData pieData = new PieData("1", 1);
        PieData pieData2 = new PieData("2", 2);
        PieData pieData3 = new PieData("3", 3);
        PieData pieData4 = new PieData("4", 4);
        mData.add(pieData);
        mData.add(pieData2);
        mData.add(pieData3);
        mData.add(pieData4);

        //初始化
        PieView pieView = (PieView)findViewById(R.id.pie);
        pieView.setStartAngle(0);
        pieView.setData(mData);
    }
}
