package com.gnanavad.utils.bday_wisher.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.gnanavad.utils.bday_wisher.cfg.ExcelConfig;
import com.gnanavad.utils.bday_wisher.model.StudentDetails;

public class DailyJobScheduler {

    public static void runJob() {
        Calendar today = Calendar.getInstance();

        // every night at 2am you run your task
        Timer timer = new Timer();
        timer.schedule(new BDayMailerTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES)); // period:
                                                                                                                   // 5
                                                                                                                   // minutes
    }

}

class BDayMailerTask extends TimerTask {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("dd.MMM.YYYY hh:mm:ss");

    @Override
    public void run() {
        System.out.println("Batch Job has STARTED : " + fmt.format(new Date()));
        try {
            ExcelStudentDetailsReader reader = new ExcelStudentDetailsReader();
            List<StudentDetails> allStudents = reader.read();
            if (allStudents != null && !allStudents.isEmpty()) {
                List<StudentDetails> todaysBirthdayBabies = BirthdayFinder.getTodaysBirthdayBabies(allStudents);
                if (todaysBirthdayBabies != null && !todaysBirthdayBabies.isEmpty()) {
                    for (StudentDetails student : todaysBirthdayBabies) {
                        String fromEmailId = ExcelConfig.getMailerEmailId();
                        String fromEmailPassword = EncoderDecoder.decode(ExcelConfig.getMailerEncodedPassword());
                        String studentName = student.getName();
                        String subject = "Happy Birthday to you, " + studentName + "!";
                        String bodySalutation = "Dear " + studentName + ",\n\n";
                        String bodyMainContent = "\tWish you a happy birthday to you.";
                        String bodyThanksContent = "\n\nWith Regards,\nTeam HumanResources.";
                        StringBuilder body = new StringBuilder();
                        body.append(bodySalutation);
                        body.append(bodyMainContent);
                        body.append(bodyThanksContent);
                        String recepient = student.getEmail();
                        MailingUtility.sendEmail(fromEmailId, fromEmailPassword, recepient, subject, body.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR : Exception occurred : " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        } finally {
            System.out.println("Batch Job has ENDED : " + fmt.format(new Date()));
        }

    }

}
