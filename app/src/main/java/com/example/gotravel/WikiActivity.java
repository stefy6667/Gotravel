package com.example.gotravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

import java.io.IOException;

public class WikiActivity extends AppCompatActivity {
    private TextView wikiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki);
        final Global gs = (Global) getApplicationContext();
        TextView name = findViewById(R.id.d_name);
        TextView objNm = findViewById(R.id.objNm);


        String userName = gs.getName();
        String objectiveName = gs.getObiectiveName();


        name.setText(userName);
        objNm.setText(objectiveName);
        wikiText = findViewById(R.id.wiki_text);
        if (gs.getObiectiveType().equals("tourist+attraction")) {
            wiki(objectiveName);
        } else {

             mapsRev();
        }


    }

    public void wiki(String objName) {
        StringBuilder build = new StringBuilder();
        for (int i = 0; i < objName.length(); i++) {
            if (objName.charAt(i) == ' ') {
                build.append('_');

            } else {
                build.append(objName.charAt(i));
            }
        }
        String finName = build.toString();
        System.out.println(finName);


        RequestQueue mQueue = Volley.newRequestQueue(this);
        String url = "https://ro.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&=&explaintext=&titles=" + finName;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("query");

                            JSONObject pages = data.getJSONObject("pages");
                            String key = pages.keys().next();
                            System.out.println("Key= "+key);
                            if (key.equals("-1")) {
                                mapsRev();

                            } else {
                            JSONObject pageID = pages.getJSONObject(key);
                            String content = pageID.getString("extract");
                            System.out.println("Content" +content);
                                wikiText.setText(content);

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


        mQueue.add(request);


    }

    public void mapsRev() {
        Global gs = (Global) getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(this);
        String placeID = gs.getReferenceOBJ();


        String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + placeID + "&key=AIzaSyC9as130thlnbvCZ1XIRPUuQ_50JDzEI6I";
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jsonObject = response.getJSONObject("result");
                            JSONArray jsonArray = jsonObject.getJSONArray("reviews");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject restaurant = jsonArray.getJSONObject(i);

                                String name = restaurant.getString("text");
                                wikiText.setText(name);


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

    public void setText(String t) {
        LinearLayout layout = findViewById(R.id.llMain);

        TextView myButton = new TextView(getApplicationContext());

        myButton.setText(t);

        layout.addView(myButton);
    }
}
