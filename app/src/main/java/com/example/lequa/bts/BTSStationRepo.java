package com.example.lequa.bts;

import java.util.ArrayList;
import java.util.List;

public class BTSStationRepo {
    private static BTSStationRepo btsStationRepo;
    private BTSStationRepo(){}
    public static BTSStationRepo getInstance(){
        if(btsStationRepo==null){
            btsStationRepo=new BTSStationRepo();
            btsStationRepo.init();
        }
        return btsStationRepo;
    }
    public List<BTSStation> listBTSStation;
    private void init(){
        listBTSStation=new ArrayList<BTSStation>();
        BTSStation BTS1=new BTSStation().ID("BTS1").position(16.069563,108.154690).BTSName("BTS1").BTSManagerName("Bùi Toàn").RadiusCover(50)
                .BTSUrlImage("http://hanoimoi.com.vn/Uploads/anhthu/2015/1/26/bts.jpg");
        BTSStation BTS2=new BTSStation().ID("BTS2").position(16.070205,108.153570).BTSName("BTS2").BTSManagerName("Lê Tam").RadiusCover(100)
                .BTSUrlImage("https://mangphuyen.com/wp-content/uploads/2017/07/tram-thu-phat-song-vien-thong.jpg");
        listBTSStation.add(BTS1);
        listBTSStation.add(BTS2);

    }
    public BTSStation getBTSStation(String id){
        for (BTSStation btsStation:listBTSStation) {
            if (btsStation.getID().equals(id)) return btsStation;
        }
        return null;
    }
}
