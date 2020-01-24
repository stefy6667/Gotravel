package com.example.gotravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntermediarActivity extends AppCompatActivity {
    private TextView name;
    private String userName;
    private String image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediar);
        final Global gs = (Global)getApplicationContext();
        name = findViewById(R.id.d_name);

        userName = gs.getName();
        ImageView profileImage = findViewById(R.id.image_view);
        image = gs.getRef();

        String url ="https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+image+"&key=AIzaSyC9as130thlnbvCZ1XIRPUuQ_50JDzEI6I";

        Glide.with(this).load(url).into(profileImage);

        name.setText(userName);

        Button btn = findViewById(R.id.wiki);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IntermediarActivity.this,WikiActivity.class);
                startActivity(intent);
            }
        });

    }






}
