package com.example.dong.news;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;



public class NewDetails extends AppCompatActivity {
    private Toolbar toolbars;
    private WebView webText;
    private ImageView ivImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_details);
        ivImage = (ImageView) findViewById(R.id.webimage);
        webText = (WebView) findViewById(R.id.web_text);
        toolbars = (Toolbar) findViewById(R.id.toolbar);
        toolbars.setTitle("新闻详情");

        setSupportActionBar(toolbars);
//        设置返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbars.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //新页面接收数据
        Intent inte=getIntent();
        //接收name值
        news.NewslistBean detail= (news.NewslistBean) inte.getSerializableExtra("detail");
        Log.d("url",detail.getTitle());


        webText.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        webText.loadUrl(detail.getUrl());
        Log.d("url","--------------------------------------------------------------------------"+detail.getPicUrl());
     Glide.with(this).load(detail.getPicUrl()).error(R.mipmap.ic_launcher).fitCenter().into(ivImage);


    }
}
