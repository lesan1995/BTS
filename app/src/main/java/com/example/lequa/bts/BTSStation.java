package com.example.lequa.bts;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by lequa on 15/03/2018.
 */

public class BTSStation {

    private String id;
    private double latitude;
    private double longitude;
    private double radiusCover;
    private String btsName;
    private String btsManagerName;
    private String btsUrlImage;
    public Drawable btsImage;
    public boolean isLoadImage=false;

    /**
     * Set ID for bts Station
     * @param id
     * @return
     */
    public BTSStation ID(String id){
        this.id=id;
        return this;
    }

    /**
     * Get id of bts Station
     * @return
     */
    public String getID(){
        return id;
    }

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
     * Url image of bts station
     * @param url
     * @return
     */
    public BTSStation BTSUrlImage(String url){
        this.btsUrlImage=url;
        return this;
    }

    /**
     * Radius cover of bts station
     * @param radiusCover
     * @return
     */
    public BTSStation RadiusCover(double radiusCover){
        this.radiusCover=radiusCover;
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
     * Get radius cover of bts station
     * @return
     */
    public Double getRadiusCover(){
        return radiusCover;
    }

    /**
     * Get url image of bts station
     * @return
     */
    public String getBtsUrlImage(){
        return btsUrlImage;
    }
    /**
     * Get image of bts station
     * @return
     */
    public void loadBTSImage(){
        if(!isLoadImage) {
            isLoadImage=true;
            new ImageLoadTask().execute(this);
        }
    }


    /**
     * Get info of
     * @return infor
     */
    public MarkerOptions getInfo(){
        MarkerOptions inforBTS=new MarkerOptions()
                .position(getPosition())
                .title(getID())
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .alpha(0.8f);
        return  inforBTS;
    }
}
