package com.example.gotravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("ALL")
public class MapsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final Global gs = (Global)getApplicationContext();
        TextView name = findViewById(R.id.d_name);

        String userName = gs.getName();
        name.setText(userName);

        maps();



    }

    public void maps() {
        Global gs = (Global) getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(this);
        String location = gs.getLocation();
        String type = gs.getObiectiveType();
        final String reference = gs.getRef();



        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius=10000&&type=place&keyword="+type+"&key=AIzaSyC9as130thlnbvCZ1XIRPUuQ_50JDzEI6I";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject restaurant = jsonArray.getJSONObject(i);
                                JSONArray photos = restaurant.getJSONArray("photos");
                                JSONObject refPhoto = photos.getJSONObject(0);
                                final String objref = restaurant.getString("reference");
                                final String name = restaurant.getString("name");
                                final String ref = refPhoto.getString("photo_reference");
                                final Button myButton = new Button(getApplicationContext());
                                System.out.println(ref);

                                myButton.setText(name);
                                myButton.setId(i);
                                final int id_ = myButton.getId();

                                LinearLayout layout = (LinearLayout) findViewById(R.id.llMain);
                                layout.addView(myButton);

                                myButton.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        Global gl = (Global)getApplicationContext();
                                        gl.setRef(ref);
                                        gl.setReferenceOBJ(objref);
                                        gl.setObiectiveName(myButton.getText().toString());
                                        Intent intent =new Intent(MapsActivity.this,IntermediarActivity.class);
                                        startActivity(intent);

                                    }
                                });



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);


    }



}
