package com.example.lequa.bts;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lequa on 21/03/2018.
 */

public class ImageLoadTask extends AsyncTask<Void,Void,Bitmap> {
    private String url;
    private boolean isCompleted=false;
    public boolean isCompleted(){
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted){
        this.isCompleted=isCompleted;
    }
    public ImageLoadTask(String url){
        this.url=url;
    }
    @Override
    protected Bitmap doInBackground(Void... params) {
        try{
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
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        MapBTS.getInstance().mapGoogle.setInfoWindowAdapter(new BTSInforWindowAdapter(bitmap));
    }
}
