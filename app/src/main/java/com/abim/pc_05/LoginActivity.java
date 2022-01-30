package com.abim.pc_05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText et_nik, et_no_hp;
    Button btn;
    Context ctx;
    RequestQueue queue;
    Session s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        ctx = this;
        btn = findViewById(R.id.btn_login);
        et_nik = findViewById(R.id.et_nik);
        et_no_hp = findViewById(R.id.et_nohp);
        s = new Session(ctx);

        queue = Volley.newRequestQueue(ctx);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_nik.getText().toString().equalsIgnoreCase("") || et_no_hp.getText().toString().equalsIgnoreCase("")){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                    dialog.setMessage("Semua Field Wajib Diisi!");
                    dialog.setTitle("Terjadi Kesalahan");
                    dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                else
                {
                    if (et_nik.getText().toString().length() != 16){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                        dialog.setMessage("NIK Wajib 16 Digit!");
                        dialog.setTitle("Terjadi Kesalahan");
                        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                    else if (et_no_hp.getText().toString().length() < 11){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                        dialog.setMessage("No HP Minimal 11 Digit!");
                        dialog.setTitle("Terjadi Kesalahan");
                        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                    else{
                        doLogin();
//                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                }
            }
        });
    }

    void doLogin(){
        String nik = et_nik.getText().toString();
        String noHp = et_no_hp.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, MyRequest.getLoginURL(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    s.setUser(object.getString("nama"),object.getString("nik"), object.getString("tempat_lahir"), object.getString("noHp"), object.getString("tanggal_lahir"));
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exception) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                    dialog.setMessage("Tidak Dapat menemukan User!");
                    dialog.setTitle("Terjadi Kesalahan");
                    dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                dialog.setMessage(""+error);
                dialog.setTitle("");
                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("nik", nik);
                params.put("noHp", noHp);

                return params;
            }
        };

//        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, MyRequest.getLoginURL(), null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    JSONObject object = response.getJSONObject(0);
//                    s.setUser(object.getString("nama"),object.getString("nik"), object.getString("tempat_lahir"), object.getString("noHp"), object.getString("tanggal_lahir"));
//                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                catch (Exception  ex){
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
//                    dialog.setMessage("Tidak Dapat menemukan User!");
//                    dialog.setTitle("Terjadi Kesalahan");
//                    dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    dialog.show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
//                dialog.setMessage(""+error);
//                dialog.setTitle("");
//                dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String > params = new HashMap<>();
//                params.put("nik", nik);
//                params.put("noHp", noHp);
//
//                return params;
//            }
//        };

        queue.add(request);
    }
}