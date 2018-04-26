package com.example.a49479.captureviewdemo;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final CaptureView captureView = (CaptureView)findViewById(R.id.captureView);
        captureView.setTxt("123456");
        PermissionHelper.checkPermission(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE});
        captureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureView.captureBitmap();
            }
        });
    }


}
