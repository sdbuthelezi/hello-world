/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectadmintool.uinterface;

import java.io.File;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 *
 * @author buthe_s
 */
public class MNPReportView extends GridPane{
    
    private VBox leftBox, rightBox;
    
    public MNPReportView(){
        this.setId("mnpReport");
        this.getStylesheets().add("MNPReportView.css");
        loadLeftBox();
        loadRightBox();
        this.add(leftBox, 0, 0);
        this.add(rightBox, 1, 0);
    }
    
    
    public void loadLeftBox(){
        leftBox = new VBox(initLineChart());
        leftBox.setId("leftVBox");
    }
    
   
    
      public void loadRightBox(){
        rightBox = new VBox(initAreaChart());
        rightBox.setId("rightVBox");
    }
      
      private Chart initAreaChart(){
        
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Products Sold");
        
        
        XYChart.Series series = new XYChart.Series();
        series.setName("Sales Report 2016");
        //populating the series with data
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 13));
        series.getData().add(new XYChart.Data("Mar", 17));
        series.getData().add(new XYChart.Data("Apr", 20));
        series.getData().add(new XYChart.Data("May", 19));
        series.getData().add(new XYChart.Data("Jun", 20));
        series.getData().add(new XYChart.Data("Jul", 23));
        series.getData().add(new XYChart.Data("Aug", 25));
        series.getData().add(new XYChart.Data("Sept", 28));
        series.getData().add(new XYChart.Data("Oct", 31));
        series.getData().add(new XYChart.Data("Nov", 35));
        series.getData().add(new XYChart.Data("Dec", 40));
        
         XYChart.Series series2 = new XYChart.Series();
        series2.setName("Sales Report 2017");

        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 20));
        series2.getData().add(new XYChart.Data("May", 21));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 32));
        series2.getData().add(new XYChart.Data("Aug", 38));
        series2.getData().add(new XYChart.Data("Sep", 34));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 45));
        
        
        AreaChart areaChart = new AreaChart(xAxis, yAxis);
        areaChart.getData().addAll(series, series2);
        
        return areaChart;
    }

    
    private Chart initLineChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Products Sold");

        LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
        lineChart.setTitle("Stock Fluctuation 2017");
        lineChart.setAnimated(true);
        lineChart.setId("lineChart");
        lineChart.setLegendVisible(true);
        lineChart.setMaxWidth(800);

        XYChart.Series series = new XYChart.Series();
        series.setName("Sales Report 2016");
        //populating the series with data
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 13));
        series.getData().add(new XYChart.Data("Mar", 17));
        series.getData().add(new XYChart.Data("Apr", 20));
        series.getData().add(new XYChart.Data("May", 19));
        series.getData().add(new XYChart.Data("Jun", 20));
        series.getData().add(new XYChart.Data("Jul", 23));
        series.getData().add(new XYChart.Data("Aug", 25));
        series.getData().add(new XYChart.Data("Sept", 28));
        series.getData().add(new XYChart.Data("Oct", 31));
        series.getData().add(new XYChart.Data("Nov", 35));
        series.getData().add(new XYChart.Data("Dec", 40));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Sales Report 2017");

        series2.getData().add(new XYChart.Data("Jan", 33));
        series2.getData().add(new XYChart.Data("Feb", 34));
        series2.getData().add(new XYChart.Data("Mar", 25));
        series2.getData().add(new XYChart.Data("Apr", 20));
        series2.getData().add(new XYChart.Data("May", 21));
        series2.getData().add(new XYChart.Data("Jun", 16));
        series2.getData().add(new XYChart.Data("Jul", 32));
        series2.getData().add(new XYChart.Data("Aug", 38));
        series2.getData().add(new XYChart.Data("Sep", 34));
        series2.getData().add(new XYChart.Data("Oct", 27));
        series2.getData().add(new XYChart.Data("Nov", 37));
        series2.getData().add(new XYChart.Data("Dec", 45));

        lineChart.getData().addAll(series, series2);
        lineChart.setLegendSide(Side.RIGHT);
        return lineChart;
    }

    
}
