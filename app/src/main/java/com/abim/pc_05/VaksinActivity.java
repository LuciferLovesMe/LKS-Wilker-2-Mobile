package com.abim.pc_05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VaksinActivity extends AppCompatActivity {
    Session s;
    Context ctx;
    RecyclerView recyclerView;
    Adapter adapter;
    RequestQueue queue;
    List<DetailVaksin> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaksin);

        ctx = this;
        s = new Session(ctx);
        recyclerView = findViewById(R.id.rv);
        queue = Volley.newRequestQueue(ctx);
        list = new ArrayList<>();

        getdata();

    }

    void getdata(){
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, MyRequest.getVaksinURL(), null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject obj = response.getJSONObject(i);
                        list.add(new DetailVaksin(obj.getString("tanggal_vaksin"), obj.getString("nama"), obj.getString("jenis_vaksin"), obj.getInt("periode")));

                    }

                    adapter = new Adapter(list, ctx);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ctx));
                    recyclerView.setAdapter(adapter);
                }
                catch (Exception ex){
                    Toast.makeText(ctx, ""+ex, Toast.LENGTH_SHORT);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_SHORT);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nik", s.getNik());
                return params;
            }
        };

        queue.add(request);
    }
}