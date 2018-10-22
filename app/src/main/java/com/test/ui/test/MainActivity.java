package com.test.ui.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import com.winson.widget.EmptyViewUtils;
import com.winson.widget.ImageUtils;
import com.winson.widget.PinyinTool;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ViewGroup emptyGroup;
    ImageView testRoundIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyGroup = findViewById(R.id.empty_panel);

        findViewById(R.id.load).setOnClickListener(this);
        findViewById(R.id.error).setOnClickListener(this);
        findViewById(R.id.empty).setOnClickListener(this);
        findViewById(R.id.load_c).setOnClickListener(this);
        findViewById(R.id.error_c).setOnClickListener(this);
        findViewById(R.id.empty_c).setOnClickListener(this);

        testRoundIV = findViewById(R.id.iv_test_round);
//        testRoundIV.setImageBitmap(ImageUtils.convertRoundBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.test_round),200));

        testRoundIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bitmap bitmap = ImageUtils.convertRoundBitmap(MainActivity.this, R.mipmap.test_round,
//                        testRoundIV.getWidth(), testRoundIV.getHeight(), 50, ImageView.ScaleType.CENTER_INSIDE);
//
//                testRoundIV.setImageBitmap(bitmap);
            }
        });

        testRoundIV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Bitmap bitmap = ImageUtils.convertRoundBitmap(MainActivity.this, R.mipmap.test_round,
                        testRoundIV.getWidth(), testRoundIV.getHeight(), 50, ImageView.ScaleType.CENTER_CROP);

                testRoundIV.setImageBitmap(bitmap);

                testRoundIV.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.load:
//                EmptyViewUtils.showLoadingView(emptyGroup);

                PinyinTool pt = new PinyinTool();
                String result = pt.toPinYin("文");
                Toast.makeText(this, "result : " + result, Toast.LENGTH_SHORT).show();

                break;
            case R.id.error:
                EmptyViewUtils.showErrorView(emptyGroup, null);
                break;
            case R.id.empty:
                EmptyViewUtils.showEmptyView(emptyGroup, null);
                break;
            case R.id.load_c:
                View load_c = LayoutInflater.from(this).inflate(R.layout.load_c, emptyGroup, false);
                EmptyViewUtils.showLoadingView(emptyGroup, load_c);
                break;
            case R.id.error_c:
                View error_c = LayoutInflater.from(this).inflate(R.layout.error_c, emptyGroup, false);
                EmptyViewUtils.showLoadingView(emptyGroup, error_c);
                break;
            case R.id.empty_c:
                View empty_c = LayoutInflater.from(this).inflate(R.layout.empty_c, emptyGroup, false);
                EmptyViewUtils.showLoadingView(emptyGroup, empty_c);
                break;
        }
    }
}
