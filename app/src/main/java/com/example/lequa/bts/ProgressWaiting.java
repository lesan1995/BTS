package com.example.lequa.bts;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Manager progress waiting
 */

public class ProgressWaiting {
    private static ProgressWaiting progressWaiting;
    ProgressDialog mapProgress;// progress bar
    private String title;
    private String message;
    private ProgressWaiting(){}
    public static ProgressWaiting getInstance(){
        if(progressWaiting==null){
            progressWaiting=new ProgressWaiting().title("Đang tải").message("Vui lòng chờ");
        }
        return progressWaiting;
    }

    /**
     * Set title when waiting
     * @param title
     * @return this
     */
    public ProgressWaiting title(String title){
        this.title=title;
        return this;
    }

    /**
     * Set message when waiting
     * @param title
     * @return this
     */
    public ProgressWaiting message(String title){
        this.message=message;
        return this;
    }

    /**
     * Show dialog waiting on context
     * @param context
     * @return this
     */
    public ProgressWaiting show(Context context){
        mapProgress=new ProgressDialog(context);
        mapProgress.setTitle(title);
        mapProgress.setMessage(message);
        mapProgress.setCancelable(true);
        mapProgress.show();
        return this;
    }

    /**
     * Close dialog waiting
     */
    public void close(){
        mapProgress.dismiss();
    }
}
