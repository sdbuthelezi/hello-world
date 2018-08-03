/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectadmintool.uinterface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import za.co.main.pojo.ProjectItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import project.main.controller.Controller;
import project.main.controller.FactoryClass;

/**
 *
 * @author buthe_s
 */
public class ProjectTableView extends BorderPane {

    private TableView<ProjectItem> table;
    private ObservableList<ProjectItem> projectItems;
    private Controller controller;
    private int totalComplete, totalNew, totalInProgress, totalUnknown, totalTesting;


    public ProjectTableView() throws InterruptedException {
        this.getStylesheets().add("ProjectTableView-style.css");
        controller = FactoryClass.getController();
        projectItems = controller.getObservableProjectList();
        table = new TableView<>();
        loadTable();
        calculateGroupings();
        plotChart();
        this.setCenter(table);
    }

    public void calculateGroupings() {
        System.out.println("Size of arraylist - " + projectItems.size());
        for (ProjectItem projectItem : projectItems) {
            if (projectItem.getPhase().equalsIgnoreCase("Deployed")) {
                totalComplete++;
            } else if (projectItem.getPhase().equalsIgnoreCase("New")) {
                totalNew++;
            } else if (projectItem.getPhase().equalsIgnoreCase("In Progress") || projectItem.getPhase().equalsIgnoreCase("Initiated")) {
                totalInProgress++;
            }else if (projectItem.getPhase().equalsIgnoreCase("Testing")) {
                totalTesting++;
            
            }
        }
    }

    public void loadTable() {
        TableColumn projectRefCol = new TableColumn("Project No.");
        projectRefCol.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("projectRef"));
        TableColumn subProjectRef = new TableColumn("Sub-Project No.");
        subProjectRef.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("subProjRef"));
        TableColumn engagementRef = new TableColumn("Engagement No.");
        engagementRef.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("engagementRef"));
        TableColumn changeRef = new TableColumn("Change No.");
        changeRef.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("changeRef"));
        TableColumn projectTitle = new TableColumn("Project Title.");
        projectTitle.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("projectTitle"));
        TableColumn phase = new TableColumn("Phase.");
        phase.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("phase"));
        TableColumn status = new TableColumn("Status.");
        status.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("status"));
        TableColumn system = new TableColumn("System.");
        system.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("system"));
        TableColumn description = new TableColumn("Description.");
        description.setCellValueFactory(new PropertyValueFactory<ProjectItem, String>("statusDescription"));

        table.setTableMenuButtonVisible(true);

        table.getColumns().addAll(projectRefCol, subProjectRef, engagementRef, changeRef, projectTitle, phase, status, system, description);
        table.setItems(projectItems);

//        
    }

    public void testTable() {
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

//        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
        this.setCenter(vbox);
    }

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    public void plotChart() {

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("New " + totalNew, totalNew),
                        new PieChart.Data("In Progress " + totalInProgress, totalInProgress),
                        new PieChart.Data("Complete " + totalComplete, totalComplete),
                        new PieChart.Data("Testing " + totalTesting, totalTesting));
        PieChart pieChart = new PieChart(pieChartData);

        pieChart.setTitle("MNP Port Ratio");
        pieChart.setId("pieChart");
        this.setTop(pieChart);

    }
    
    public void plotBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Employee");
        yAxis.setLabel("Sales Total");
        
       
        BarChart barChart = new BarChart(xAxis, yAxis);
        barChart.setId("barChart");
        barChart.setTitle("Employee Performance Chart");
        barChart.setId("barChart");
        XYChart.Series series = new XYChart.Series();
        series.setName("Employee Sales Performance");
        //populating the series with data
        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 13));
//        series.getData().add(new XYChart.Data("Mar", 17));
//        series.getData().add(new XYChart.Data("Apr", 20));
//        series.getData().add(new XYChart.Data("May", 19));
//        series.getData().add(new XYChart.Data("Jun", 20));

//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("Sales Report 2017");
//
//        series2.getData().add(new XYChart.Data("Jan", 33));
//        series2.getData().add(new XYChart.Data("Feb", 34));
//        series2.getData().add(new XYChart.Data("Mar", 25));
//        series2.getData().add(new XYChart.Data("Apr", 20));
//        series2.getData().add(new XYChart.Data("May", 21));
//        series2.getData().add(new XYChart.Data("Jun", 16));
         barChart.getYAxis().setTickLabelRotation(90);
        barChart.getData().addAll(series);
//        
//        Timeline tl = new Timeline();
//        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
//            new EventHandler<ActionEvent>() {
//                @Override public void handle(ActionEvent actionEvent) {
//                for (XYChart.Series<Number, String> series : barChart.getData()) {
//                    for (XYChart.Data<Number, String> data : series.getData()) {
//                        data.setXValue(Math.random() * 100);
//                    }
//                }
//            }
//        }));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.play();
        this.setCenter(barChart);
    }

}
