package com.radtech;

import com.google.gson.Gson;


public class JsonObject {
    JsonChart chart;
    JsonEntry[] data;

    public JsonObject(JsonChart chart, String datainput) {
        this.chart = chart;
        String[] array = datainput.split(";");
        data = new JsonEntry[array.length];
        for(int i = 0; i<array.length; i++){
            String[] str = array[i].split(",");
            data[i] = new JsonEntry(str[0], str[1]);
        }
    }    
    public String toJson(){
        Gson gson = new Gson();
        return this.toJson();
    }
}
