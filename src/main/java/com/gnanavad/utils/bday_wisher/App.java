package com.gnanavad.utils.bday_wisher;

import java.util.List;

import com.gnanavad.utils.bday_wisher.core.ExcelStudentDetailsReader;
import com.gnanavad.utils.bday_wisher.model.StudentDetails;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        List<StudentDetails> details = new ExcelStudentDetailsReader().read();
        int index = 1;
        for(StudentDetails student: details) {
            System.out.println(index++ + ". " + student);
        }
    }
}
