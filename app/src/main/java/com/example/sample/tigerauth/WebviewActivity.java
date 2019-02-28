package com.example.sample.tigerauth;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

public class WebviewActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webview = findViewById(R.id.webView_web);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // Ignore SSL certificate errors
            }
        });
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                String [] requests = request.getResources();
                for (String temp : requests)
                    Log.e("Permission", temp);
                request.grant(new String[]{PermissionRequest.RESOURCE_VIDEO_CAPTURE, PermissionRequest.RESOURCE_AUDIO_CAPTURE});
            }

            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                Toast.makeText(WebviewActivity.this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        });
        webview.loadUrl("https://192.168.43.252:4200/");

        JavascriptInterface javascriptInterface = new JavascriptInterface();
        webview.addJavascriptInterface(javascriptInterface, "Android");

        String response = javascriptInterface.getResponse();
//        ArrayList<String> responses = new ArrayList<>();
        Gson gson = new Gson();
        Response response1 = gson.fromJson(response, Response.class);
//        responses.add(response1.getCallbackurl());
//        responses.add(response1.getDomainName());
//        responses.add(response1.getName());
//        responses.add(response1.getUsername());
        webview.loadUrl(response1.getCallbackurl());
//        Intent intent = new Intent();
//        intent.putStringArrayListExtra("serverResponse",null);
//        setResult(100, intent);
//        finish();
    }
}
