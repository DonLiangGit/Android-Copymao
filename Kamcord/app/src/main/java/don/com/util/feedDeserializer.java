package don.com.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import don.com.pojo.videoClass;

/**
 * Created by new on 1/31/15.
 */
public class feedDeserializer implements JsonDeserializer<List<videoClass>> {

    @Override
    public List<videoClass> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException{

        JsonElement reJson = je.getAsJsonObject().get("response");
        JsonArray videoList = reJson.getAsJsonObject().getAsJsonArray("video_list");
        ArrayList<videoClass> vList = new ArrayList<videoClass>();

        for(JsonElement e : videoList) {
            vList.add((videoClass)jdc.deserialize(e, videoClass.class));
        }

        return vList;
    }
}
