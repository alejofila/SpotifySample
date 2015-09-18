package com.example.alejofila.spotifysample.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by alejofila on 17/09/15.
 */
public class AlbumDeserializer implements JsonDeserializer<Album[]>{
    public static final String TAG = AlbumDeserializer.class.getSimpleName();
    @Override
    public Album[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Log.i(TAG, "This is the jsonString: " + json.toString());
        JsonElement element = json.getAsJsonObject().get("items").getAsJsonArray();
        Log.i(TAG,"This the partially processed jsonString: "+element.toString());
        return new Gson().fromJson(element, Album[].class);
    }
}
