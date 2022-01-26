package com.abim.pc_05;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;
import java.util.prefs.Preferences;

public class Session {
    SharedPreferences prefs;

    public Session(Context ctx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setUser(String nama, String nik, String tempat_lahir, String noHp, String tanggal_lahir){
        prefs.edit().putString("nama", nama).commit();
        prefs.edit().putString("nik", nik).commit();
        prefs.edit().putString("tempat_lahir", tempat_lahir).commit();
        prefs.edit().putString("noHp", noHp).commit();
        prefs.edit().putString("tgl_lahir", tanggal_lahir.toString()).commit();
    }

    public String getNama(){
        return prefs.getString("nama", "");
    }
    public String getNik(){
        return prefs.getString("nik", "");
    }
    public String getTtl(){
        return prefs.getString("tempat_lahir", "") + ", "+prefs.getString("tanggal_lahir", "");
    }
    public String getNohp(){
        return prefs.getString("noHp", "") ;
    }
}
