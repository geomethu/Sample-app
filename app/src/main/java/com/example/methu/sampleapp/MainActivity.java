package com.example.methu.sampleapp;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TableLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.methu.sampleapp.adapters.SampleAdapter;
import com.example.methu.sampleapp.globals.GlobalVariables;
import com.example.methu.sampleapp.globals.MySingleton;
import com.example.methu.sampleapp.models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue _requestQueue;
    TableLayout tl;
    SampleAdapter adapter;
    ListView lv;
    ArrayList<DataModel> _sampleDataArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _requestQueue = Volley.newRequestQueue(this);
        lv = (ListView) findViewById(R.id.lv_main) ;


        fetchJsonResponse();


    }

    private void fetchJsonResponse() {
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, GlobalVariables.path, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("Tag", ""+response);
                        _sampleDataArray = new ArrayList<>();

                        if (response != null) {
                            for (int i=0;i<response.length();i++){
                                try {
                                    JSONObject jobj = response.getJSONObject(i);
                                    DataModel dm = new DataModel();
                                    dm.setId(jobj.getString("id"));
                                    dm.setName(jobj.getString("name"));
                                    dm.setPicture(jobj.getString("picture"));
                                    _sampleDataArray.add(dm);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }


                        adapter = new SampleAdapter(MainActivity.this, _sampleDataArray);
                        lv.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

		/* Add your Requests to the RequestQueue to execute */
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(req);
    }

    void drawLayout(){
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;

        Log.e("$$$$$$$$$$$$$$4", "   "+width);

    }
}
