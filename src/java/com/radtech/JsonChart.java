package com.radtech;

public class JsonChart {
    String caption, subCaption, xAxisName, yAxisName, numberPrefix, theme;

    public JsonChart(String caption, String subCaption, String xAxisName, String yAxisName, String numberPrefix, String theme) {
        this.caption = caption;
        this.subCaption = subCaption;
        this.xAxisName = xAxisName;
        this.yAxisName = yAxisName;
        this.numberPrefix = numberPrefix;
        this.theme = theme;
    }
}
