/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectadmintool.uinterface;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import project.main.controller.Controller;
import project.main.controller.FactoryClass;
import za.co.main.pojo.*;

/**
 *
 * @author buthe_s
 */
public class NoteView extends VBox implements EventHandler<ActionEvent> {

    private Note note;
    private Label bannerLabel, projectLabel, subProjectLabel, changeLabel;
    private TextArea notesText;
    private Button saveNoteButton, backButton, deleteNoteButton,viewDetailButton;
    private HBox projectListHBox;
    private Controller controller;
    private ArrayList<ProjectItem> projectsArrayList;
    private TableView<Note> table;
    private String projectName;
    

    private HBox bannerBox, textBox, buttonBox, optionBox;

    public NoteView() throws InterruptedException {
        this.getStylesheets().add("ProjectTableView-style.css");
        loadListOfProjects();

    }

    public void createProjectOption() {
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("item1", "item2", "item3");
    }

    private void setBannerLabel(String projectName) {
        this.projectName = projectName;
        bannerLabel = new Label(projectName);
    }

    private void setNotesText() {
        notesText = new TextArea();
        notesText.setText("");
    }

    private void setButton() {
        saveNoteButton = new Button("Save note");
        saveNoteButton.setOnAction(this);
        backButton = new Button("Back");
        backButton.setOnAction(this);

    }

    private void loadListOfProjects() {
        try {
            projectListHBox = new HBox();
            controller = FactoryClass.getController();
            projectsArrayList = controller.getProjectListArray();
            this.getChildren().remove(0, this.getChildren().size());
            for (int x = 0; x < projectsArrayList.size(); x++) {

                String title = projectsArrayList.get(x).getProjectTitle();

                if (title.length() > 1) {

                    Button projectButton = new Button(projectsArrayList.get(x).getProjectTitle());
                    projectButton.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            String project = projectButton.getText();
                            loadProjectNotePad(project);
                        }
                    });

                    projectListHBox = new HBox(projectButton);

                    this.getChildren().add(projectListHBox);

                }

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(NoteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void loadProjectNotePad(String projectName) {
        setBannerLabel(projectName);
        bannerBox = new HBox(bannerLabel);
        setNotesText();
        textBox = new HBox(notesText);
        setButton();
        buttonBox = new HBox(saveNoteButton, backButton);
        
        
        for(int x = 0; x < projectsArrayList.size(); x++){
            
            
        }
        
        this.getChildren().remove(0, this.getChildren().size());
        this.getChildren().addAll(bannerBox, textBox, buttonBox, loadListNotes(projectName));
        
    }
    
    private Pane loadListNotes(String projectName){
        
         ObservableList<Note> notesList = null;
         HBox hbox = new HBox();
         hbox.setSpacing(20);
          
          for(int x = 0; x < projectsArrayList.size();x++){
              if (projectsArrayList.get(x).getProjectTitle().equalsIgnoreCase(projectName)){
                  notesList = FXCollections.observableList(projectsArrayList.get(x).getNotes());
                  
                  for(int y = 0; y < notesList.size(); y++){
                      hbox.getChildren().add(tinyNotePanel(notesList.get(y), projectName));
                  }
            
              }
          }
        return hbox;
    }
    
    private VBox tinyNotePanel(Note noteObj, String project){
        VBox topLayer = new VBox();
        
        GridPane buttonGridPane = new GridPane();
        Label date = new Label(noteObj.getTimestamp());
        TextArea txtArea = new TextArea(noteObj.getNote());
        viewDetailButton = new Button("Detail");
        viewDetailButton.setId(project + "#" + noteObj.getNoteID());
        deleteNoteButton = new Button("Delete Note");
        deleteNoteButton.setId(project + "#" + noteObj.getNoteID());
        deleteNoteButton.setOnAction(this);
        viewDetailButton.setOnAction(this);
        
        buttonGridPane.add(deleteNoteButton, 0, 0);
        buttonGridPane.add(viewDetailButton, 1, 0);
        BorderPane borderPane = new BorderPane(txtArea, date, null, buttonGridPane, null);
        topLayer.getChildren().addAll(borderPane);
        return topLayer;
    }

    @Override
    public void handle(ActionEvent event) {
        Object o = event.getSource();

        if (o.equals(backButton)) {
            loadListOfProjects();
        }

        if (o.equals(saveNoteButton)) {
            boolean saveResult = controller.saveNote(projectName, notesText.getText());
            
            if(saveResult){
                notesText.clear();
            }
            
            
        }
        
         if (o.equals(deleteNoteButton)) {
             String projectToDelete = deleteNoteButton.getId();
             StringTokenizer stoken = new StringTokenizer(projectToDelete, "#");
             String projectToken = stoken.nextToken();
             String noteIDToken = stoken.nextToken();
             System.out.println("Tokens" + projectToken + "" + noteIDToken);
             for (ProjectItem project : projectsArrayList) {
                 if(project.getProjectTitle().equalsIgnoreCase(projectToDelete)){
                      for (Note note : project.getNotes()){
                          if(note.getNoteID().equalsIgnoreCase(noteIDToken)){
                              project.getNotes().remove(note);
                              System.out.println("Note removed");
                          }
                      }
                 }
             }
         }
         
          if (o.equals(viewDetailButton)) {
              String projectToDelete = deleteNoteButton.getId();
             StringTokenizer stoken = new StringTokenizer(projectToDelete, "#");
             String noteIDToken = stoken.nextToken();
             String projectToken = stoken.nextToken();
              System.out.println("Tokens" + projectToken + "" + noteIDToken);
          }

       
    }

}
