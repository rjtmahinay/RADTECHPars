package com.radtech;

public class JsonData {
    JsonEntry[] data;
    public JsonData(String input){
        String[] array = input.split(";");
        data = new JsonEntry[array.length];
        for(int i = 0; i<array.length; i++){
            String[] str = array[i].split(",");
            data[i] = new JsonEntry(str[0], str[1]);
        }
    }
}
