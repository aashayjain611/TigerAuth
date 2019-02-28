package com.example.sample.tigerauth;

public class Response
{
    private String callbackurl;
    private String domainName;
    private String username;
    private String name;

    public Response(String callbackurl, String domainName, String username, String name) {
        this.callbackurl = callbackurl;
        this.domainName = domainName;
        this.username = username;
        this.name = name;
    }

    public String getCallbackurl() {
        return callbackurl;
    }

    public void setCallbackurl(String callbackurl) {
        this.callbackurl = callbackurl;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
