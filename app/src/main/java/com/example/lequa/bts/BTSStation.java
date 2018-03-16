package com.example.lequa.bts;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by lequa on 15/03/2018.
 */

public class BTSStation {

    private double latitude;
    private double longitude;
    private String title;
    private String snippet;

    /**
     * Set position for bts station
     * @param latitude
     * @param longitude
     * @return
     */
    public BTSStation position(double latitude,double longitude){
        this.latitude=latitude;this.longitude=longitude;
        return this;
    }

    /**
     * Set title for bts station
     * @param title
     * @return
     */
    public BTSStation title(String title){
        this.title=title;
        return this;
    }

    /**
     * Set snippet for bts station
     * @param snippet
     * @return
     */
    public BTSStation snippet(String snippet){
        this.snippet=snippet;
        return this;
    }

    /**
     * Get position of this bts station
     * @return position
     */
    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }

    /**
     * Get title of bts station
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get snippet of bts station
     * @return snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     * Get info of
     * @return infor
     */
    public MarkerOptions getInfo(){
        MarkerOptions inforBTS=new MarkerOptions()
                .position(getPosition())
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.8f);
        return  inforBTS;
    }
}
