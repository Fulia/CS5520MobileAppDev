package com.example.numad22sp_xuebai;

import android.view.View;

public class Link{
    private String linkName;
    private String url;

    public Link(String nameTxt, String urlTxt){
        linkName = nameTxt;
        url = urlTxt;
    }

    public String getLinkName(){
        return linkName;
    }

    public String getUrl(){
        return url;
    }


}
