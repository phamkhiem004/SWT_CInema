package dal;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class EmailHelper {

    public static final String EMAIL_USERNAME = "ducnthe151031@fpt.edu.vn";
    public static final String EMAIL_PASSWORD = "hetl oeqs krau sldk";
    public static final String TITLE_PROJECT = "SWP391 - TEAM5 - SE1874-JS - Agency Project";

    public static void sendEmailRequestContractSuccess(String toEmail, String subject, String fullName, String contractCode, String content) {
        final String username = "fcareinsurance@gmail.com";
        final String password = "cifxowsnfwdnywed";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session s = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        String message = "<!DOCTYPE html>\n"
                + "<html> \n"
                + "<head><meta charset='utf-8'></head> \n"
                + "<body>\n"
                + "<h1 style=\"font-weight: bold;\">CBD Cinema</h1>\n"
                + "<b><h5>Xin chào " + fullName + " Bạn vừa đặt vé thành công</h5></b>\n"
                + "<h5>Sử dụng mã bên dưới để tra cứu thông tin vé.</h5>\n"
                + "<h5>Đây là <b>Mã hợp đồng</b> của bạn: " + contractCode + "<br></h5>\n"
                + "<p>" + content + " </p>\n"
                + "<hr>"
                + "<i>Nếu bạn có thắc mắc, vui lòng tạo yêu cầu tư vấn <a href=\"http://localhost:9999/SWP391-Team5-SE1874-JS \">tại đây</a></i>\n"
                + "<p>Chúc xem phim vui vẻ!</p>\n"
                + "<b>CBD Cinema</b>\n"
                + "</body>\n"
                + "</html>";
        try {
            Message msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(username));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B")); // Encode the subject
            msg.setContent(message, "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendEmail(String recipientEmail, String subject, String body) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send the email to " + recipientEmail);
        }
    }

    // Define the characters to choose from (64 unique characters)
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

    // Length of the OTP code
    private static final int OTP_LENGTH = 8;

    public static String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);

        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            otp.append(randomChar);
        }

        return otp.toString();
    }

    public static void main(String[] args) {
        sendEmailRequestContractSuccess("maususu11@gmail.com", "MUA VÉ PHIM THÀNH CÔNG", "NGUYỄN THẾ ĐỨC", "ABCDEFGHIKOK123", "Hello test email");
    }
}
