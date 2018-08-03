/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.main.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Button;

/**
 *
 * @author buthe_s
 */
public class Note implements Serializable{
    
    private String title, parent_ref, noteID,  timestamp, note;
    private SimpleDateFormat sdf;
    private transient Button deleteButton;
    
    private String generateNoteID(){
        double id = Math.random() * 10;
        Date d  = new Date();
        String Noteid = Double.toString(id) + d.toString();
        
        return Noteid;
    }

     public Note(){
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        timestamp = sdf.format(new Date());
        noteID = generateNoteID();
       
    }
    
    public Note(String title){
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        timestamp = sdf.format(new Date());
        noteID = generateNoteID();
        
        this.title = title;
    }
    
    public Note(String title, String note) {
        sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        timestamp = sdf.format(new Date());
        noteID = generateNoteID();
       
        this.title = title;
        this.note = note;
    }

    public String getParent_ref() {
        return parent_ref;
    }

    public void setParent_ref(String parent_ref) {
        this.parent_ref = parent_ref;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public void snapTime(){
        sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        timestamp = sdf.format(new Date());
    }
    
    public void saveNote(){
        
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton;
    }
    
    public void createDeleteButton(){
        
        if(deleteButton == null){
            deleteButton = new Button("Delete Note");
            System.out.println("Delet button created in Note.java class");
        }
    }

    @Override
    public String toString() {
        return "Note{" + "title=" + title + ", parent_ref=" + parent_ref + ", noteID=" + noteID + ", timestamp=" + timestamp + ", note=" + note + ", sdf=" + sdf + ", deleteButton=" + deleteButton + '}';
    }
    
    
    
    
}
