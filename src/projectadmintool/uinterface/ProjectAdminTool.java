/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectadmintool.uinterface;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.main.controller.DatabaseDAO;

/**
 *
 * @author buthe_s
 */
public class ProjectAdminTool extends Application {

    private Scene scene;
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        

        mainStage = primaryStage;

        VBox root = new VBox();
        TabPane tabbedPane = new TabPane();
        tabbedPane.getTabs().addAll(loadHomeTab(), loadMNPTab(), loadNotesTab());

        root.getChildren().addAll(loadMenuBar(),tabbedPane);

        scene = new Scene(root, 1900, 900);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getFileBrowser() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);
//        if (selectedFile != null) {
//            fileChooser.display(selectedFile);
//        }

    }

    public MenuBar loadMenuBar() {
        final Menu menu1 = new Menu("File");
        MenuItem select_File = new MenuItem("Select File");
        menu1.getItems().add(select_File);
        final Menu menu2 = new Menu("Options");
        final Menu menu3 = new Menu("Help");
        
        select_File.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               getFileBrowser();
            }
        });

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        return menuBar;
    }

    public Tab loadHomeTab() throws InterruptedException {
        ProjectTableView projectTableView = new ProjectTableView();

        Tab tab = new Tab();
        tab.setText("Project View");
        tab.setContent(projectTableView);

        return tab;
    }
    
    public void loadNotes(){
        
    }

    public Tab loadMNPTab() throws InterruptedException {
        MNPReportView projectTableView = new MNPReportView();

        Tab tab = new Tab();
        tab.setText("MNP Reports");
        tab.setContent(projectTableView);

        return tab;
    }
    
      public Tab loadNotesTab() throws InterruptedException {
        NoteView noteView = new NoteView();

        Tab tab = new Tab();
        tab.setText("Add Note");
        tab.setContent(noteView);

        return tab;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        launch(args);
      launch(args);
    }

}
