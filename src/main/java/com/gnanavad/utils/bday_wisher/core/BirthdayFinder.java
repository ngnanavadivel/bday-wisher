package com.gnanavad.utils.bday_wisher.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gnanavad.utils.bday_wisher.model.StudentDetails;


public class BirthdayFinder {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");

    public static int getDayOfMonth(Date birthday) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(birthday);
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static Date parseDate(String birthday) throws ParseException {
        return fmt.parse(birthday);
    }

    public static int getMonth(Date birthday) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(birthday);
        return instance.get(Calendar.MONTH);
    }

    public static boolean isTodayABirthday(Date birthday) {
        int day = getDayOfMonth(birthday);
        int month = getMonth(birthday);
        Date today = new Date();
        int todaysDay = getDayOfMonth(today);
        int todaysMonth = getMonth(today);
        return day == todaysDay && month == todaysMonth;
    }

    public static List<StudentDetails> getTodaysBirthdayBabies(List<StudentDetails> students) {
        return students.stream().filter(e -> {
            try {
                return isTodayABirthday(parseDate(e.getDob()));
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
    }
    
    public static void main(String[] args) throws ParseException {
        System.out.println(isTodayABirthday(parseDate("10.10.2018")));
    }
}
