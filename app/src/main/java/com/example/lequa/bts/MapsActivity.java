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
        BTSStation BTS1=new BTSStation().position(16.069563,108.154690).BTSName("BTS1").BTSManagerName("Bùi Toàn");
        BTSStation BTS2=new BTSStation().position(16.070205,108.153570).BTSName("BTS2").BTSManagerName("Lê Tam");
        MapBTS.getInstance().addBTSStation(BTS1).addBTSStation(BTS2);
        ImageLoadTask imgTask=new ImageLoadTask("http://hanoimoi.com.vn/Uploads/anhthu/2015/1/26/bts.jpg");
        imgTask.execute();
    }
}
