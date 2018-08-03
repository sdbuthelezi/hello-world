package za.co.main.pojo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.sun.rowset.internal.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import org.apache.poi.hssf.model.Sheet;

import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;

/**
 *
 * @author buthe_s
 */
public class ExcelManagement {

    private HSSFWorkbook workBook;
    private ArrayList<ProjectItem> projectsList;

    public ExcelManagement() {
        workBook = new HSSFWorkbook();
        projectsList = new ArrayList<>();
    }

    public boolean plotSheet(String sheetName, String columns[]) {
        HSSFSheet sheet = workBook.createSheet(sheetName);
        HSSFRow topRow = sheet.createRow(0);
        short cellColumnCount = 0;

        for (String colName : columns) {
            HSSFCell cell = topRow.createCell(cellColumnCount);
            HSSFCellStyle c = setStyle();
            cell.setCellStyle(c);
            cell.setCellValue(colName);

            ++cellColumnCount;
        }
        writeToFile(sheetName);
        return false;
    }

    public void loadProjects() throws InterruptedException {
        FileInputStream inputStream = null;
        try {
            String excelFilePath = "C:\\Users\\buthe_s\\Documents\\TestProjects.xls";
            inputStream = new FileInputStream(new File(excelFilePath));
            workBook = new HSSFWorkbook(inputStream);

            HSSFSheet firstSheet = workBook.getSheetAt(0);
            Iterator<HSSFRow> iterator = firstSheet.rowIterator();
            int count = 0;
            ProjectItem pItem = new ProjectItem();
            while (iterator.hasNext()) {

                HSSFRow nextRow = iterator.next();

                Iterator<HSSFCell> cellIterator = nextRow.cellIterator();
                pItem = new ProjectItem();
                while (cellIterator.hasNext()) {
                    HSSFCell cell = cellIterator.next();
                    StringBuffer cellValue = new StringBuffer();
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_STRING:
                            cellValue.append(cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            cellValue.append(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            cellValue.append(cell.getNumericCellValue());
                            break;
                    }

                    switch (cell.getCellNum()) {
                        case 0:
                            pItem.setProjectRef(cellValue.toString());
                            break;
                        case 1:
                            pItem.setSubProjRef(cellValue.toString());
                            break;
                        case 2:
                            pItem.setEngagementRef(cellValue.toString());
                            break;
                        case 3:
                            pItem.setChangeRef(cellValue.toString());
                            break;
                        case 4:
                            pItem.setProjectTitle(cellValue.toString());
                            break;
                        case 5:
                            pItem.setPhase(cellValue.toString());

                            break;
                        case 6:
                            pItem.setStatus(cellValue.toString());
                            break;
                        case 7:

                            pItem.setTargetDate(cellValue.toString());
                            break;

                        case 8:
                            pItem.setSystem(cellValue.toString());
                            break;
                        case 9:

                            break;
                        case 10:
                            pItem.setStatusDescription(cellValue.toString());
                            break;
                        default:
                            break;
                    }

                }
                count++;
                if (count > 1) {
                    projectsList.add(pItem);
                }
            }
            loadProjectNotes("RICA – Port Out Reversal (107254)");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
//                for(ProjectItem ite: projectsList){
//                    System.out.println(ite.toString() + "\n");
//                }
            } catch (IOException ex) {
                Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadProjectNotes() {

        FileInputStream fis = null;
        ObjectInputStream ois = null;
        InputStream is = null;
        ArrayList<Note> noteList = null;

        for (int x = 0; x < projectsList.size(); x++) {

            try {
                fis = new FileInputStream("C:\\Users\\buthe_s\\Documents\\Notes\\" + projectsList.get(x).getProjectTitle() + ".ser");
                ois = new ObjectInputStream(fis);
                noteList = (ArrayList<Note>) ois.readObject();
                projectsList.get(x).setNotes(noteList);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int x = 0; x < projectsList.size(); x++) {

            for (int y = 0; y < projectsList.get(x).getNotes().size(); y++) {
                System.out.println("Project: " + projectsList.get(x).getProjectTitle() + "\nNotes\n" + projectsList.get(x).getNotes().get(y).getNote());
            }
        }
    }

    public void loadProjectNotes(String projectName) {

        FileInputStream fis = null;
        File file = null;
        ObjectInputStream ois = null;
        InputStream is = null;
        ArrayList<Note> noteList = null;

        for (int x = 0; x < projectsList.size(); x++) {

            if (projectsList.get(x).getProjectTitle().equalsIgnoreCase(projectName)) {
                try {
                    file = new File("C:\\Users\\buthe_s\\Documents\\Notes\\" + projectName + ".ser");

                    if (file.exists()) {
                        fis = new FileInputStream(file.getAbsolutePath());
                        ois = new ObjectInputStream(fis);
                        noteList = (ArrayList<Note>) ois.readObject();
                        projectsList.get(x).setNotes(noteList);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
                }catch(NullPointerException npe){
                    npe.printStackTrace();
                }
            }

        }

        for (int x = 0; x < projectsList.size(); x++) {

            for (int y = 0; y < projectsList.get(x).getNotes().size(); y++) {
           projectsList.get(x).getNotes().get(y).createDeleteButton();
                System.out.println("Delete Buttons created");
            }
        }
    }

    public HSSFCellStyle setStyle() {

        HSSFCellStyle cellStyle = workBook.createCellStyle();
        cellStyle.setFillBackgroundColor(new HSSFColor.MAROON().getIndex());
        HSSFFont font = workBook.createFont();
        font.setColor(new HSSFColor.WHITE().getIndex());
        font.setBoldweight(new Short("2"));
        cellStyle.setFont(font);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return cellStyle;
    }

    private void writeToFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\buthe_s\\Documents\\Netbeans Test Files\\Report.xls"));
            workBook.write(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ProjectItem> getProjectsList() {
        return projectsList;
    }

    public static void main(String[] args) throws InterruptedException {
        ExcelManagement excel = new ExcelManagement();
        excel.loadProjects();

    }
}
