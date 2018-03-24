package com.example.lequa.bts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by lequa on 15/03/2018.
 */

public class MapBTS {
    public GoogleMap mapGoogle;//Map BTS
    private static MapBTS mapBTS;
    private LatLng position;
    public Context mContext;
    private int mZoom;
    private int mMapType;
    CameraPosition mCameraPosition;
    private MapBTS(){}
    public static MapBTS getInstance(){
        if(mapBTS==null){
            mapBTS=new MapBTS().position(new LatLng(16.069563,108.154690)).Zoom(16).Type(GoogleMap.MAP_TYPE_NORMAL);
        }
        return mapBTS;
    }
    /**
     * Init map BTS
     * @param mapGoogle
     * @param context
     * @return this
     */
    @SuppressLint("MissingPermission")
    public MapBTS initMap(GoogleMap mapGoogle, Context context){
        ProgressWaiting.getInstance().show(context);
        this.mapGoogle = mapGoogle;
        this.mContext=context;
        mapGoogle.setMapType(mMapType);
        mapGoogle.getUiSettings().setZoomControlsEnabled(true);
//        mapGoogle.setMyLocationEnabled(true);
        mCameraPosition=new CameraPosition.Builder()
                .target(position)
                .zoom(mZoom)
                .bearing(90)
                .build();
        return this;
    }
    /**
     * Show map bts
     * @return this
     */
    public MapBTS showMap(){
        this.mapGoogle.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                ProgressWaiting.getInstance().close();
                mapGoogle.animateCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));

            }
        });
        return this;
    }

    /**
     * Set position to camera
     * @param position
     * @return
     */
    public MapBTS position(LatLng position){
        this.position=position;
        return this;
    }

    /**
     * Set level zoom to position camera move
     * @param mZoom
     * @return this
     */
    public MapBTS Zoom(int mZoom){
        this.mZoom=mZoom;
        return this;
    }

    /**
     * Set map type
     * @param mMapType
     * @return this
     */
    public MapBTS Type(int mMapType){
        this.mMapType=mMapType;
        return this;
    }

    /**
     * Add BTS Station to map
     * @param bts
     * @return
     */
    public MapBTS addBTSStation(BTSStation bts){
        mapGoogle.addMarker(bts.getInfo());
        return this;
    }

    /**
     * Move camera to my location
     */
    public void showMyLocation(){
        LocationManager locationManager= (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        Criteria criteria=new Criteria();
        @SuppressLint("MissingPermission")
        Location lastLocation=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,false));
        if(lastLocation!=null){
            BTSStation myLocation=new BTSStation().position(lastLocation.getLatitude(),lastLocation.getLongitude())
                    .BTSName("My Location")
                    .BTSManagerName("Tom Nick");
            addBTSStation(myLocation);
            position(new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude()));
            Log.d("abc","Deo Co 1");
            showMap();
        }
    }
}
