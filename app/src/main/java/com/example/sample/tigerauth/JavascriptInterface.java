package com.example.sample.tigerauth;

public class JavascriptInterface {

    private String response;

    /** Instantiate the interface and set the context */
    JavascriptInterface() {
    }

    /** Show a toast from the web page */
    @android.webkit.JavascriptInterface
    public void setResponse(String response) {
        this.response = response;
    }

    String getResponse()
    {
        return response;
    }
}
