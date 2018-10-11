package com.gnanavad.utils.bday_wisher.core;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailingUtility {
    public static void sendEmail(String fromEmailId, String fromEmailPassword, String recepient, String subject,
            String body) {

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");

        /* SSL */
        /// Uncomment the below one and comment the TLS section if mails didn't go via SSL.
        /// props.put("mail.smtp.socketFactory.port", "465");
        /// props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        /// props.put("mail.smtp.port", "465");

        /* TLS */
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmailId,
                                                  fromEmailPassword);
            }
        });

        try {

            InternetAddress[] toAddresses = InternetAddress.parse(recepient);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmailId));
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Mail successfully sent to " + recepient);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        final String fromEmailId = ""; // Put correct email id from which bday wishes will be sent to students.
        final String fromEmailPassword = ""; // Put correct password here to test.
        String recepient = "nataraj.gnanavadivel@gmail.com";
        String subject = "Test Mail!!!";
        String body = "Happy Birthday, buddy!";
        MailingUtility.sendEmail(fromEmailId, fromEmailPassword, recepient, subject, body);
    }
}
