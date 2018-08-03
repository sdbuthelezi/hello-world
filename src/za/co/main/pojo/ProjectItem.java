package za.co.main.pojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author buthe_s
 */
public class ProjectItem {
    private String projectRef;
    private String engagementRef;
    private String projectTitle;
    private String subProjRef;
    private String changeRef;
    private String targetDate;
    private String actualDate;
    private String cabTarget, cabActual;
    private String system, phase, status, statusDescription;
    private ArrayList<String> impactSystems;
    private ArrayList<Note> notes;

    public ProjectItem() {
        notes = new ArrayList<>();
    }
    
    

    public String getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(String projectRef) {
        this.projectRef = projectRef;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getSubProjRef() {
        return subProjRef;
    }

    public void setSubProjRef(String subProjRef) {
        this.subProjRef = subProjRef;
    }

    public String getChangeRef() {
        return changeRef;
    }

    public void setChangeRef(String subProjTitle) {
        this.changeRef = subProjTitle;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getCabTarget() {
        return cabTarget;
    }

    public void setCabTarget(String cabTarget) {
        this.cabTarget = cabTarget;
    }

    public String getCabActual() {
        return cabActual;
    }

    public void setCabActual(String cabActual) {
        this.cabActual = cabActual;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public ArrayList<String> getImpactSystems() {
        return impactSystems;
    }

    public void setImpactSystems(ArrayList<String> impactSystems) {
        this.impactSystems = impactSystems;
    }

    public String getEngagementRef() {
        return engagementRef;
    }

    public void setEngagementRef(String engagementRef) {
        this.engagementRef = engagementRef;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
    
    public void addNote(String newNote){
        
        Note note = new Note(newNote);
        notes.add(note);
    }
    
    public void addNote(String newNote, String title){
        
        Note note = new Note(title, newNote);
        notes.add(note);
        
    }
    
   
    @Override
    public String toString() {
        return projectTitle;
    }
    
    
    
    
    
}
