<%-- 
    Document   : statistics
    Created on : 09-Mar-2017, 20:39:43
    Author     : Sphere
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/fusioncharts.js"></script>
        <script type="text/javascript" src="js/themes/fusioncharts.theme.fint.js"></script>
        <script type="text/javascript">
            var digits = '<s:property value="%{#session.scores}"/>';
  FusionCharts.ready(function(){
    var revenueChart = new FusionCharts({
        "type": "column2d",
        "renderAt": "chartContainer",
        "width": "500",
        "height": "300",
        "dataFormat": "json",
        "dataSource":  {
          "chart": {
            "caption": "Monthly visits",
            "subCaption": "Animal Station",
            "xAxisName": "Month",
            "yAxisName": "Number of Appointments",
            "theme": "fint"
         },
         "data": [
            {
               "label": "Jan",
               "value": digits[0]
            },
            {
               "label": "Feb",
               "value": digits[1]
            },
            {
               "label": "Mar",
               "value": digits[2]
            },
            {
               "label": "Apr",
               "value": digits[3]
            },
            {
               "label": "May",
               "value": digits[4]
            },
            {
               "label": "Jun",
               "value": digits[5]
            },
            {
               "label": "Jul",
               "value": digits[6]
            },
            {
               "label": "Aug",
               "value": digits[7]
            },
            {
               "label": "Sep",
               "value": digits[8]
            },
            {
               "label": "Oct",
               "value": digits[9]
            },
            {
               "label": "Nov",
               "value": digits[10]
            },
            {
               "label": "Dec",
               "value": digits[11]
            }
          ]
      }

  });
revenueChart.render();
})
</script>
    </head>
    <body>
        <h1>Animal Station statistics!</h1>
    </body>
    <body>
<div id="chartContainer">FusionCharts XT will load here!
    <script>
        revenueChart.render();
        </script>
</div>
</body>
</html>
