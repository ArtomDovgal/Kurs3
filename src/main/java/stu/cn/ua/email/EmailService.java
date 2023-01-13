package stu.cn.ua.email;

public interface EmailService {


    String sendSimpleMail(EmailDetails details);


    String sendMailWithAttachment(EmailDetails details);
}
