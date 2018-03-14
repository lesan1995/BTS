package com.example.lequa.bts;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mapBTS;//Map BTS
    ProgressDialog mapProgress;// progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        showProgressLoadMap();
        mapBTS = googleMap;
        mapBTS.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                closeProgressLoadMap();
                showBTSMap();
            }
        });
    }
    //Show progress bar when load map
    public void showProgressLoadMap(){
        mapProgress=new ProgressDialog(this);
        mapProgress.setTitle("Đang tải map");
        mapProgress.setMessage("Vui lòng chờ");
        mapProgress.setCancelable(true);
        mapProgress.show();
    }
    //close progress bar after loaded map
    public void closeProgressLoadMap(){
        mapProgress.dismiss();
    }
    public MarkerOptions BTSStation(double latitude,double longitude,String title,String snippet){
        LatLng BTS = new LatLng(latitude, longitude);
        MarkerOptions inforBTS=new MarkerOptions()
            .position(BTS)
            .title(title)
            .snippet(snippet)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            .alpha(0.8f);
        return inforBTS;
    }
    //Show all bts in map
    public void showBTSMap(){
        //Khai bao bts station
        MarkerOptions BTS1=BTSStation(16.069563,108.154690,"BTS1","Mobifone");
        MarkerOptions BTS2=BTSStation(16.070205,108.153570,"BTS2","Viettel");

        //Add BTS vao map
        mapBTS.addMarker(BTS1);
        mapBTS.addMarker(BTS2);


        //Move camera to BTS1
        mapBTS.moveCamera(CameraUpdateFactory.newLatLngZoom(BTS1.getPosition(),15));
        mapBTS.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
    public void myLocation(){
        LocationManager locationManager= (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria=new Criteria();
        @SuppressLint("MissingPermission")
        Location lastLocation=locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria,false));
        if(lastLocation!=null){
            LatLng latLng=new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());

        }
    }
}
