package com.micro.user.service.service.impl;

import com.micro.user.service.payload.MailRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailSender {

    @Value("${spring.mail.username}")
    private String sendMailId;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(MailRequestBody request){

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(sendMailId);
        mailMessage.setTo(request.to());
        mailMessage.setText(request.body());
        mailMessage.setSubject(request.subject());
        javaMailSender.send(mailMessage);
    }

}
