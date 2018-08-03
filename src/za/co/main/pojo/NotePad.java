/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.main.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author buthe_s
 */
public class NotePad implements Serializable{

    private ArrayList<Note> Notes;

    private ArrayList<String> titles;
    private ArrayList<String> parents;

    public NotePad() {

    }

    public Note getNoteByID(String noteID) {

        for (Note n : Notes) {
            if (n.getNoteID().equalsIgnoreCase(noteID)) {
                return n;
            }
        }
        return null;
    }

    public Note getNoteByTitle(String title) {

        for (Note n : Notes) {
            if (n.getTitle().equalsIgnoreCase(title)) {
                return n;
            }
        }
        return null;
    }

    public void createNewNote(String title) {

        Note n1 = new Note(title);

    }

    public void addNote(String noteID, String notes) {
        Note n1 = this.getNoteByID(noteID);
        n1.setNote(notes);
    }
    
    
    public void persistNote(){
        
    }

}
