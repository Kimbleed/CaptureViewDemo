package com.example.a49479.captureviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by 49479 on 2018/4/24.
 */

public class CaptureView extends View {

    private float mHeight;
    private float mWidth;

    private Paint mPaint;

    private Bitmap mBgBitmap;

    private String mTxt="";

    public CaptureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        initLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rect = new Rect(0,0,(int)mWidth,(int)mHeight);
        canvas.drawBitmap(mBgBitmap,null,rect,mPaint);
        mPaint.setColor(Color.WHITE);
        float value = mPaint.measureText(mTxt);
        canvas.drawText(mTxt,mWidth/2-value/2,mHeight/2,mPaint);
    }

    public void initLayout() {
        mHeight = getHeight();
        mWidth = getWidth();
        mBgBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.bg);
        mPaint = new Paint();
        mPaint.setTextSize(mWidth/10);
        mPaint.setAntiAlias(true);
    }

    public void setTxt(String txt){
        mTxt = txt;
        postInvalidate();
    }

    public void captureBitmap(){
        setDrawingCacheEnabled(true);
        Bitmap srcBitmap  = getDrawingCache();
        File fileDir = new File(getExternalStorageDirectory().getAbsolutePath()+File.separator+"captureView");
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        File dstFile = new File(fileDir,System.currentTimeMillis()+".jpeg");
        try {
            FileOutputStream fos = new FileOutputStream(dstFile);
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();
        }
        catch (Exception e){

        }

    }

}

