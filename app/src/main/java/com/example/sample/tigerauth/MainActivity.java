package com.example.sample.tigerauth;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 200;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO},1);

        webView = findViewById(R.id.webView_main);
        Button button = findViewById(R.id.signInButton);

//        Intent intent = getIntent();
//        if (intent != null)
//        {
//            Bundle bundle = intent.getBundleExtra("serverResponse");
//            if (bundle != null)
//            {
//                ArrayList<String> stringResponses = bundle.getStringArrayList("response");
//                assert stringResponses != null;
//                String username = stringResponses.get(0);
//                String url = stringResponses.get(1);
//                webView.setVisibility(View.VISIBLE);
//                webView.loadUrl(url);
//            }
//        }
//
//        else
//            webView.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(MainActivity.this, WebviewActivity.class), REQUEST_CODE);
                startActivity(new Intent(MainActivity.this, WebviewActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 100)
        {
            if (requestCode == REQUEST_CODE)
            {
                if (data != null)
                {
                    ArrayList <String> responses = data.getStringArrayListExtra("serverResponse");
                    if (responses != null)
                    {
                        String name = responses.get(3);
                        String username = responses.get(2);
                        String domainName = responses.get(1);
                        String callbackurl = responses.get(0);

                        Log.e("MainActivity: callback", callbackurl);
                        Log.e("MainActivity: domainNam", domainName);
                        Log.e("MainActivity: username", username);
                        Log.e("MainActivity: name", name);

                        if (callbackurl != null)
                        {
                            webView.setVisibility(View.VISIBLE);
                            webView.loadUrl(callbackurl);
                        }
                    }
                    else
                        webView.setVisibility(View.GONE);
                }
                else
                    webView.setVisibility(View.GONE);
            }
        }
    }
}
