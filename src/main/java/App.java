import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class App {
    public static void main(String[] args) throws MessagingException {
        String message="Hello everybody this is a test mail for security check!";
        String subject="Project";
        String to="mrigraj.s@vvdntech.in";
        String from="wondersprogramming@gmail.com";

        sendMail(message, subject,to,from);
    }

    private static void sendMail(String message, String subject, String to, String from) throws MessagingException {
        //variable for gmail
        String host="smtp.gmail.com";
        Properties properties=System.getProperties();
        System.out.println("Properties"+properties);

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth","true");

        //step1: get the session object
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("wondersprogramming@gmail.com","yqjqztqkhslhbggc");
            }
        });
        session.setDebug(true);

        //compose the message

        MimeMessage mimeMessage=new MimeMessage(session);

        //from email
        mimeMessage.setFrom(from);
        //add recipient
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //add subject
        mimeMessage.setSubject(subject);

        //add message(text)
        mimeMessage.setText(message);

        //step3: send message using Transport Class
        Transport.send(mimeMessage);
    }
}
