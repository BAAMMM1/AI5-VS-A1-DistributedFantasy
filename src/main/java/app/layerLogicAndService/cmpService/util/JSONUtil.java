package app.layerLogicAndService.cmpService.util;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris on 09.12.2017
 */
public class JSONUtil {

    private static Gson gson = new Gson();

    public static <T>T getObject(String body, String field, Class<T> tClass){

        if(tClass == String.class){
            return (T)new JSONObject(body).get(field).toString();
        }

        return gson.fromJson(new JSONObject(body).get(field).toString(), tClass);
    }

    public static <T>List<T> getObjectList(String body, String field, Class<T> tClass){

        List<T> adventurerList = new ArrayList<T>();
        JSONArray jsonArray = new JSONObject(body).getJSONArray(field);

        for (int i = 0; i < jsonArray.length(); i++) {
            adventurerList.add(gson.fromJson(jsonArray.get(i).toString(), tClass));
        }

        return adventurerList;

    }

    public static <T>T getObjectFirst(String body, String field, Class<T> tClass){

        return gson.fromJson(new JSONObject(body).getJSONArray(field).get(0).toString(), tClass);

    }

}
