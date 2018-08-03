/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.main.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projectadmintool.uinterface.*;
import za.co.main.pojo.*;

/**
 *
 * @author buthe_s
 */
public class Controller {

    private ExcelManagement excel;
    private static ArrayList<ProjectItem> projects;
    private File file;

    public Controller() throws InterruptedException {
        excel = new ExcelManagement();
        excel.loadProjects();

    }

    public ObservableList getObservableProjectList() {

        return FXCollections.observableList(excel.getProjectsList());
    }

    public ArrayList<ProjectItem> getProjectListArray() {
        projects = excel.getProjectsList();
        return excel.getProjectsList();
    }

    public boolean saveNote(String projectTitle, String notes) {

        
        for (int i = 0; i < projects.size(); i++) {

            String project = projects.get(i).getProjectTitle();
            if (projects.get(i).getProjectTitle().equalsIgnoreCase(projectTitle)) {
                 projects.get(i).addNote(notes,projectTitle);
            }

        }
        
        persistNotes(projectTitle);
        
        return true;
    }

    public String createDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        return sdf.format(c.getTime());
    }

    public void persistNotes() {

    }

    public void persistNotes(String projectName) {

        ObjectOutputStream fw = null;
        OutputStream os = null;
        FileOutputStream fos = null;
        try {
            file = new File("C:\\Users\\buthe_s\\Documents\\Notes\\" + projectName + ".ser");
            fos = new FileOutputStream(file);
            fw = new ObjectOutputStream(fos);

            for (int i = 0; i < projects.size(); i++) {

                if (projects.get(i).getProjectTitle().equalsIgnoreCase(projectName)) {

                    System.out.println("Notes-----" + projects.get(i).getNotes().get(0).getNote() );
                    fw.writeObject(projects.get(i).getNotes());
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
