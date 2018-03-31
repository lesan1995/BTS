package com.example.lequa.bts;

import android.graphics.Bitmap;
import java.util.HashMap;

/**
 * Created by lequa on 28/03/2018.
 */

public class ImageRepo {
    private static ImageRepo imageRepo;
    private ImageRepo(){}
    public static ImageRepo getInstance(){
        if(imageRepo==null){
            imageRepo=new ImageRepo();
        }
        return imageRepo;
    }
    private HashMap<String,Bitmap> repo=new HashMap<String,Bitmap>();
    public ImageRepo putImage(String url,Bitmap bm){
        repo.put(url,bm);
        return this;
    }
    public Bitmap getImage(String url){
        return repo.get(url);
    }

}
