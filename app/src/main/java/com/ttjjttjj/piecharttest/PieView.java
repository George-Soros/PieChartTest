package com.ttjjttjj.piecharttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class PieView extends View {
    //颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFFFF8C69,
                0xFF808080, 0xFFE6B800, 0xFF7CFC00};
    //初始绘制角度
    private float mStartAngle = 0;
    //数据
    private ArrayList<PieData> mData;
    //宽，高
    private int mWidth,mHeight;
    //画笔
    private Paint mPaint = new Paint();

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//防止锯齿
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null == mData){
            return;
        }
        //当前初始角度
        float currentStartAngle = mStartAngle;
        //将画布坐标移动到中心位置
        canvas.translate(mWidth/2, mHeight/2);
        //饼状图半径
        float r = (float) (Math.min(mWidth, mHeight)/2*0.8);
        RectF rectF = new RectF(-r, -r ,r, r);

        for(int i=0; i <mData.size(); i++){
            PieData pieData = mData.get(i);
            mPaint.setColor(pieData.getColor());
            //化圆
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            //重设开始角度
            currentStartAngle += pieData.getAngle();
        }
    }

    public void setStartAngle(int mStartAngle){
        this.mStartAngle = mStartAngle;
        /**刷新*/
        invalidate();
    }

    public void setData(ArrayList<PieData> mData){
        this.mData = mData;
        Log.d("mdata","mData.size="+mData.size());
        initData();
        invalidate();
    }

    private void initData(){

        if(null == mData || mData.size() == 0){
            return;
        }

        float sumValue = 0;
        for (int i=0; i<mData.size(); i++){
            PieData pie = mData.get(i);
            sumValue += pie.getValue();
            //设置颜色
            int j = i%mColors.length;
            pie.setColor(mColors[j]);
        }

        /** 通过value来设置百分比和角度*/
        for (int i=0; i<mData.size(); i++){

            PieData pie = mData.get(i);
            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度

            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小

            Log.i("angle", "" + pie.getAngle());
        }
    }

}
