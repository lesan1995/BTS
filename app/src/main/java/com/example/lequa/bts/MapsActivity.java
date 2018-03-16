package com.example.lequa.bts;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
        MapBTS.getInstance().initMap(googleMap,this);
        showBTSMap();
        MapBTS.getInstance().showMap();
    }

    /**
     * Show some bts station in map
     */
    public void showBTSMap(){
        BTSStation BTS1=new BTSStation().position(16.069563,108.154690).title("BTS1").snippet("Mobifone");
        BTSStation BTS2=new BTSStation().position(16.070205,108.153570).title("BTS2").snippet("Viettel");
        MapBTS.getInstance().addBTSStation(BTS1).addBTSStation(BTS2);
    }
}
