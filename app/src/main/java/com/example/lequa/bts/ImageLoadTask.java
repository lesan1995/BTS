package com.example.lequa.bts;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lequa on 21/03/2018.
 */

public class ImageLoadTask extends AsyncTask<BTSStation,Void,Void> {
    public ImageLoadTask(){
    }
    @Override
    protected Void doInBackground(BTSStation... btss) {
        try{
            if(btss[0].btsImage==null){
                URL urlConnection=new URL(btss[0].getBtsUrlImage());
                HttpURLConnection connection= (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                //Đọc dữ liệu
                InputStream input=connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(input);
                if(myBitmap==null) return null;
                Drawable drawable=new BitmapDrawable(myBitmap);
                btss[0].btsImage=drawable;

            }
            else{
                return null;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
