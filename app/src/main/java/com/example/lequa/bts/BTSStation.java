package com.example.lequa.bts;

/**
 * Created by lequa on 15/03/2018.
 */

public class BTSStation {
    private float latitude;
    private float longitude;
    private String title;
    private String snippet;

    public BTSStation location(float latitude,float longitude){
        this.latitude=latitude;this.longitude=longitude;
        return this;
    }
    public BTSStation title(String title){
        this.title=title;
        return this;
    }
    public BTSStation snippet(String snippet){
        this.snippet=snippet;
        return this;
    }


}
