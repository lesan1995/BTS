package com.example.lequa.bts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lequa on 21/03/2018.
 */

public class ImageLoadTask extends AsyncTask<String,Void,Bitmap> {
    private String url;
    boolean isExist =false;
    boolean isNull=false;
    public ImageLoadTask(){
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        this.url=urls[0];
        try{
            if(ImageRepo.getInstance().getImage(url)==null){

                URL urlConnection=new URL(url);
                HttpURLConnection connection= (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                //Đọc dữ liệu
                InputStream input=connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(input);
                if(myBitmap==null) return null;
                return myBitmap;
            }
            else{
                isExist=true;
                return null;
            }

        }
        catch (Exception e) {
            isNull=true;
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(!(isNull&&isExist))
            ImageRepo.getInstance().putImage(url,bitmap);

    }
}
