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
    private String btsName;
    private String btsManagerName;

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
     * @param btsName
     * @return
     */
    public BTSStation BTSName(String btsName){
        this.btsName=btsName;
        return this;
    }

    /**
     * Set snippet for bts station
     * @param btsManagerName
     * @return
     */
    public BTSStation BTSManagerName(String btsManagerName){
        this.btsManagerName=btsManagerName;
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
    public String getBTSName() {
        return btsName;
    }

    /**
     * Get snippet of bts station
     * @return snippet
     */
    public String getBtsManagerName() {
        return btsManagerName;
    }

    /**
     * Get info of
     * @return infor
     */
    public MarkerOptions getInfo(){
        MarkerOptions inforBTS=new MarkerOptions()
                .position(getPosition())
                .title(btsName)
                .snippet(btsManagerName)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.8f);
        return  inforBTS;
    }
}
