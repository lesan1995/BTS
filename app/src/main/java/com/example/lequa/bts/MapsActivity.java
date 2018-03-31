package com.example.lequa.bts;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapBTS.getInstance().initMap(googleMap, this);
        showBTSMap();
        MapBTS.getInstance().showMap();
    }

    /**
     * Show some bts station in map
     */
    public void showBTSMap(){
        MapBTS.getInstance().addBTSStation(BTSStationRepo.getInstance().listBTSStation.get(0))
                            .addBTSStation(BTSStationRepo.getInstance().listBTSStation.get(1));
    }


}
