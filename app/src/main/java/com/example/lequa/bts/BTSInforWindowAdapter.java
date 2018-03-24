package com.example.lequa.bts;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by lequa on 21/03/2018.
 */

public class BTSInforWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private Bitmap btmp;
    public BTSInforWindowAdapter(Bitmap bitmap){
        this.btmp=bitmap;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View v=((Activity)MapBTS.getInstance().mContext).getLayoutInflater().inflate(R.layout.bts_info,null);
        ImageView imgStation=(ImageView) v.findViewById(R.id.imgStation);
        TextView tvStationName=(TextView)v.findViewById(R.id.tvStationName);
        TextView tvManagerName=(TextView)v.findViewById(R.id.tvManagerName);
        imgStation.setImageBitmap(btmp);
        tvStationName.setText(marker.getTitle());
        tvManagerName.setText(marker.getSnippet());
        return v;
    }
}
