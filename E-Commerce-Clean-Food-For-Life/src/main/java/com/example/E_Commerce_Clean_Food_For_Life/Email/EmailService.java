package com.example.E_Commerce_Clean_Food_For_Life.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otpCode, String Usename) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            // Create a MimeMessageHelper
            helper = new MimeMessageHelper(message, true); // true for multipart

            // Set email properties
            helper.setTo(toEmail);
            helper.setSubject("Your OTP Code");
            helper.setText(generateOtpEmailBody(otpCode, Usename), true); // true for HTML content

            // Send the email
            mailSender.send(message);


        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
    }

    private String generateOtpEmailBody(String otpCode, String Usename) {
        // HTML content for the email body
        return "<html>" +
                "<body>" +
                "<h1>Your OTP Code</h1>" +
                "<p>Dear" + Usename + ",</p>" +
                "<p>Your OTP code is: <strong>" + otpCode + "</strong></p>" +
                "<p>Please enter this code to verify your account.</p>" +
                "<p>Thank you!</p>" +
                "</body>" +
                "</html>";
    }
}
