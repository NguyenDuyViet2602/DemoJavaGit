package Model;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, String body, String attachmentPath) {
        // Thiết lập thông tin máy chủ email
        String from = "nguyenduyviet246@gmail.com"; // Địa chỉ email của bạn
        final String username = "nguyenduyviet246@gmail.com"; // Tên đăng nhập email
        final String password = "lesb xawh xhty aons"; // Mật khẩu email

        // Thiết lập thuộc tính cho phiên làm việc
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên làm việc
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Tạo đối tượng MimeMessage
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Tạo đối tượng MimeBodyPart cho nội dung email
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Tạo đối tượng MimeBodyPart cho tệp đính kèm
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("HoaDon.pdf");

            // Tạo Multipart và thêm các phần vào đó
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Thiết lập nội dung cho message
            message.setContent(multipart);

            // Gửi email
            Transport.send(message);
            System.out.println("Email đã được gửi thành công!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
