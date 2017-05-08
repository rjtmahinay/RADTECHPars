package com.radtech;

public class JsonLineObject{
    JsonLineChart chart;
    JsonLineCategory[] categories;
    JsonLineEntry[] dataset;
    
    public JsonLineObject(JsonLineChart chart, String categString, String datasetString){
        this.chart = chart;
        toCategories(categString);
        toDataSet(datasetString);
    }
    
    public void toDataSet(String input){
        String[] array = input.split(";");
        dataset = new JsonLineEntry[array.length];
        for(int i = 0; i<array.length; i++){
            String[] str = array[i].split(",");
            dataset[i] = new JsonLineEntry(str[0], str[1]);
        }
    }

    @Override
    public String toString() {
        return "JsonLineChart{" + "categories=" + categories + ", dataset=" + dataset + '}';
    }
    
    public void toCategories(String input){
        String[] array = input.split(";");
       categories = new JsonLineCategory[array.length];
        for(int i = 0; i<array.length; i++){
            categories[i] = new JsonLineCategory(array[i]);
        }
    }
    
}
