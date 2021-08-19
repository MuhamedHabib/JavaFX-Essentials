package Helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class javaMail {
    public static void sendMail(String receveursList, String Object, String Corps) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String MonEmail = "inovvat@gmail.com";
        String password = "InnovaTech123";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(MonEmail, password);
            }

        });

        Message message = prepareMessage(session,MonEmail,receveursList, Object, Corps
        );

        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.err.println("Message envoyé avec succès");
    }

    private static Message prepareMessage(Session session, String email, String receveursList, String Object, String Corps)
    {
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email));

            message.setSubject(Object);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receveursList));
            message.setText(Corps);

            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}




