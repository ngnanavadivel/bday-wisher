package com.gnanavad.utils.bday_wisher.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.gnanavad.utils.bday_wisher.cfg.ExcelConfig;
import com.gnanavad.utils.bday_wisher.model.StudentDetails;

public class ExcelStudentDetailsReader {

    private static final DataFormatter DATA_FORMATTER = new DataFormatter();

    public List<StudentDetails> read() {
        InputStream fstream = null;
        List<StudentDetails> details = new ArrayList<StudentDetails>();
        Workbook workbook = null;
        try {
            String excelSheetFile = ExcelConfig.getExcelSheetFileName();
            System.out.println("Excel Sheet File Name : " + excelSheetFile);
            fstream = new FileInputStream(new File(excelSheetFile));
            if (fstream != null) {
                workbook = WorkbookFactory.create(fstream);
                Integer workSheetIndex = ExcelConfig.getWorkSheetIndex();
                System.out.println("WorkSheet Index : " + workSheetIndex);
                if (workSheetIndex != null) {
                    Sheet masterDatabaseSheet = workbook.getSheetAt(0);
                    if (masterDatabaseSheet != null) {

                        Integer nameColumnIndex = ExcelConfig.getNameColumnIndex();
                        System.out.println("Name column -> " + nameColumnIndex);

                        Integer dobColumnIndex = ExcelConfig.getDOBColumnIndex();
                        System.out.println("DOB column -> " + dobColumnIndex);

                        Integer emailColumnIndex = ExcelConfig.getEmailColumnIndex();
                        System.out.println("Email column -> " + emailColumnIndex);

                        Integer phoneColumnIndex = ExcelConfig.getStudentPhoneNumberColumnIndex();
                        System.out.println("Phone column -> " + phoneColumnIndex);

                        Integer batchColumnIndex = ExcelConfig.getBatchColumnIndex();
                        System.out.println("Batch column -> " + batchColumnIndex);

                        Integer branchCodeColumnIndex = ExcelConfig.getBranchCodeColumnIndex();
                        System.out.println("Branch Code column -> " + branchCodeColumnIndex);

                        Iterator<Row> iterator = masterDatabaseSheet.iterator();
                        int rowIndex = 1;
                        while (iterator.hasNext()) {
                            Row row = iterator.next();
                            if (rowIndex == 1) { // skipping the excel header.
                                rowIndex++;
                                continue;
                            } else {
                                StudentDetails student = new StudentDetails();
                                /* NAME */
                                if (nameColumnIndex != null) {
                                    Cell nameCell = row.getCell(nameColumnIndex);
                                    student.setName(nameCell.getStringCellValue());
                                }

                                /* DATE OF BIRTH */
                                if (dobColumnIndex != null) {
                                    Cell dobCell = row.getCell(dobColumnIndex);
                                    student.setDob(dobCell.getStringCellValue());
                                }

                                /* EMAIL */
                                if (emailColumnIndex != null) {
                                    Cell emailCell = row.getCell(emailColumnIndex);
                                    student.setEmail(emailCell.getStringCellValue());
                                }

                                /* PHONE NUMBER */
                                if (phoneColumnIndex != null) {
                                    Cell phoneCell = row.getCell(phoneColumnIndex);
                                    student.setPhoneNumber(DATA_FORMATTER.formatCellValue(phoneCell));
                                }

                                /* BATCH */
                                if (batchColumnIndex != null) {
                                    Cell batchCell = row.getCell(batchColumnIndex);
                                    student.setBatch(batchCell.getStringCellValue());
                                }

                                /* BRANCH CODE */
                                if (branchCodeColumnIndex != null) {
                                    Cell branchCodeCell = row.getCell(branchCodeColumnIndex);
                                    student.setBranchCode(DATA_FORMATTER.formatCellValue(branchCodeCell));
                                }

                                details.add(student);
                            }
                        }
                    } else {
                        System.out.println("ERROR: Worksheet index is wrongly given!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fstream != null) {
                    fstream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("Exception occured : " + e.getMessage());
                // just swallow it ;)
            }
        }
        return details;
    }

    public static void main(String[] args) {
        List<StudentDetails> details = new ExcelStudentDetailsReader().read();
        int index = 1;
        for (StudentDetails student : details) {
            System.out.println(index++ + ". " + student);
        }
    }

}
