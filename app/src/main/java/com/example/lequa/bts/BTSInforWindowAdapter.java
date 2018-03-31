package com.example.lequa.bts;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.lequa.bts.databinding.BtsInfoBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by lequa on 21/03/2018.
 */

public class BTSInforWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private String TAG="BTSInforWindowAdapter";
    public BTSInforWindowAdapter(){

    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        BTSStation btsStation=BTSStationRepo.getInstance().getBTSStation(marker.getTitle());
        btsStation.loadBTSImage();
        LayoutInflater inflater=((Activity)MapBTS.getInstance().mContext).getLayoutInflater();
        BtsInfoBinding binding=DataBindingUtil.inflate(inflater,R.layout.bts_info,null,false);
        binding.setBtsStation(btsStation);
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
