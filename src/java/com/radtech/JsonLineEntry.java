package com.radtech;

public class JsonLineEntry {
    String seriesname, data;

    public JsonLineEntry(String seriesname, String data) {
        this.seriesname = seriesname;
        this.data = data;
    }
    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
}
