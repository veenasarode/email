package com.email.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static java.lang.String.valueOf;

@Service
public class EmailService {

    public boolean sendEmail(String subject,String message,String to){

        boolean flag = false;

        String from = "sarodeveena91@gmail.com";

        //variable for gmail
        String host = "smtp.gmail.com";

        //get the system properties
        Properties properties =  System.getProperties();
        System.out.println("PROPERTIES" + properties);

        //setting important data to properties object
        //set host
        properties.put("mail.smtp.host" , host);
        properties.put("mail.smtp.port" , "465");
        properties.put("mail.smtp.ssl.enable" , "true");
        properties.put("mail.smtp.auth" , "true");

        //step:1 to get the session object....
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sarodeveena91@gmail.com","dlibbmcuaysmbmbt");
            }
        });
        session.setDebug(true);

        //step 2 : compose message
        MimeMessage mimeMessage = new MimeMessage(session);

        //from email
        try {
            mimeMessage.setFrom(from);
            //adding recipient
            mimeMessage.addRecipients(Message.RecipientType.TO, valueOf(new InternetAddress(to)));
            //adding subject to message
            mimeMessage.setSubject(subject);
            //adding text to message
            mimeMessage.setText(message);

            //send
            //step:3 send message using transport class
            Transport.send(mimeMessage);
            System.out.println("Sent Success..........");

            flag = true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }

        return flag;
    }
}
