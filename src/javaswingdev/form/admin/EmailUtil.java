/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaswingdev.form.admin;

/**
 *
 * @author ACER
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    public static void sendEmail(String toEmail, String subject, String messageText) {
        final String fromEmail = "abdillahaziz56@gmail.com"; // Ganti dengan email kamu
        final String password = "zrrt anag gdht dscn"; // Gunakan App Password (bukan password biasa!)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });
        session.setDebug(true); // Aktifkan debug
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(messageText);

            Transport.send(msg);
            System.out.println("Email terkirim ke " + toEmail);
        } catch (MessagingException e) {
            System.out.println("Gagal mengirim email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
