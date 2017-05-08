package com.radtech;

public class JsonLineChart {
    String caption, subcaption, yaxisname, xaxisname, yaxisminvalue, yaxismaxvalue, pixelsPerPoint, pixelsPerLabel, lineThickness, compactdatamode, dataseparator, labelsHeight, theme;

    public JsonLineChart(){
        caption = "Pet Statistics";
        subcaption = "Temperature and Weight";
        yaxisname = "levels";
        xaxisname = "Date";
        yaxismaxvalue = "200";
        yaxisminvalue = "0";
        pixelsPerPoint = "1";
        pixelsPerLabel = "30";
        compactdatamode = "1";
        dataseparator = "|";
        labelsHeight = "30";
        theme = "fint";
    }
}
