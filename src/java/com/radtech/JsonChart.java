package com.radtech;

public class JsonChart {
    String caption, subCaption, xAxisName, yAxisName, numberPrefix, theme;

    public JsonChart(String caption, String xAxisName, String yAxisName, String numberPrefix) {
        this.caption = caption;
        subCaption = "Animal Station";
        this.xAxisName = xAxisName;
        this.yAxisName = yAxisName;
        this.numberPrefix = numberPrefix;
        theme = "fint";
    }
}
