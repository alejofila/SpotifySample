package com.example.alejofila.spotifysample.model;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by alejofila on 16/09/15.
 *
 * GSON Custom Deserializer for the search service call
 */
public class ArtistDeserializer implements JsonDeserializer<Artist[]> {
    @Override
    public Artist[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonElement content = json.getAsJsonObject().get("artists");
        JsonElement element = content.getAsJsonObject().get("items").getAsJsonArray();

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(element, Artist[].class);

    }
}
