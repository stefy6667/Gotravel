package com.example.gotravel;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class Global extends Application {


    private String name;
    private String ObiectiveName;
    private String ObiectiveType;
    private String image;
    private String location;
    private String imageObj;
    private String ref;
    private String referenceOBJ;

    public String getReferenceOBJ() {
        return referenceOBJ;
    }

    public void setReferenceOBJ(String referenceOBJ) {
        this.referenceOBJ = referenceOBJ;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getImageObj() {
        return imageObj;
    }

    public void setImageObj(String imageObj) {
        this.imageObj = imageObj;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObiectiveName() {
        return ObiectiveName;
    }

    public void setObiectiveName(String obiectiveName) {
        ObiectiveName = obiectiveName;
    }

    public String getObiectiveType() {
        return ObiectiveType;
    }

    public void setObiectiveType(String obiectiveType) {
        ObiectiveType = obiectiveType;
    }
}

