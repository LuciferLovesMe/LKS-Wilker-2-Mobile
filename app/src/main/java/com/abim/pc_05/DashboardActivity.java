package com.abim.pc_05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.card_lihat){
            Intent i = new Intent(DashboardActivity.this, VaksinActivity.class);
            startActivity(i);
        }
        else if (id == R.id.card_profil){
            Intent i = new Intent(DashboardActivity.this, VaksinActivity.class);
            startActivity(i);
        }
        else if (id == R.id.card_info){
            Intent i = new Intent(DashboardActivity.this, VaksinActivity.class);
            startActivity(i);
        }
        else if (id == R.id.card_logout){
            Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}