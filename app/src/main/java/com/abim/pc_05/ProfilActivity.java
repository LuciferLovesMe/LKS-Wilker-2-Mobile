package com.abim.pc_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class ProfilActivity extends AppCompatActivity {
    TextView tv_profilename, tv_profilenik, tv_ttl, tv_hp;
    Session s;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ctx = this;
        s = new Session(ctx);
        tv_profilename = findViewById(R.id.tv_profilename);
        tv_profilenik = findViewById(R.id.tv_profilenik);
        tv_ttl = findViewById(R.id.tv_ttl);
        tv_ttl = findViewById(R.id.tv_hp);

        tv_profilename.setText(s.getNama());
        tv_profilenik.setText(s.getNik());
        tv_ttl.setText(s.getTtl());
        tv_hp.setText(s.getNohp());
    }
}