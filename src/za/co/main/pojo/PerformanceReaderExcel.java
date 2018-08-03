/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.main.pojo;



import com.sun.rowset.internal.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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


public class PerformanceReaderExcel {
    
     private HSSFWorkbook workBook;
    private ArrayList<ProjectItem> projectsList;

    public PerformanceReaderExcel() {
        workBook = new HSSFWorkbook();
        projectsList = new ArrayList<>();
    }
    
    
     public void readFromExcelFile() throws InterruptedException {
        FileInputStream inputStream = null;
        try {
            String excelFilePath = "C:\\Users\\buthe_s\\Documents\\MTN Billing\\KPI\\Capacity Plans\\Book1.xls";
            inputStream = new FileInputStream(new File(excelFilePath));
            workBook = new HSSFWorkbook(inputStream);

            HSSFSheet firstSheet = workBook.getSheetAt(0);
            Iterator<HSSFRow> iterator = firstSheet.rowIterator();
            int count = 0;
            PerformanceReaderExcel pItem = new PerformanceReaderExcel();
            while (iterator.hasNext()) {
                
                
                HSSFRow nextRow = iterator.next();
                
               
                Iterator<HSSFCell> cellIterator = nextRow.cellIterator();
                pItem = new PerformanceReaderExcel();
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
//                            pItem.setProjectRef(cellValue.toString());
                            System.out.println(cellValue.toString());
                            break;
                        case 1:
//                            pItem.setSubProjRef(cellValue.toString());
                            System.out.println(cellValue.toString());
                            break;
                        case 2:
//                             pItem.setEngagementRef(cellValue.toString());
                            System.out.println(cellValue.toString());
                            break;
                        
                        default:
                            break;
                    }
                   

                    count++;
                }
//                if (count > 0) {
//                    projectsList.add(pItem);
//                }
            }

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
     
     public static void main(String[] args) {
         try {
             new PerformanceReaderExcel().readFromExcelFile();
         } catch (InterruptedException ex) {
             Logger.getLogger(PerformanceReaderExcel.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
