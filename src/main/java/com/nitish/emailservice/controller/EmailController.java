package com.nitish.emailservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/sendEmail")
    public String sendEmail() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("nitish102000@gmail.com");
        helper.setSubject("Testing on 11-05-2022");
        helper.setText("Kya Aj Hoga!!!!!!");

        javaMailSender.send(message);
        logger.debug("Email send to {} ", "nitish102000@gmail.com");
        return "Email Sent Successfully";
//        boolean result = EmailRepo.sendMail();
//        if(result){
//            return "Email Sent Successfully";
//        }
//        else{
//            return "Email Not Sent";
//        }
    }

//    @RequestMapping(value = "/sendEmailAtt")
//    public String sendEmailAtt(){
//        boolean result = EmailRepo.sendMailWithAttachment();
//        if(result){
//            return "Email Sent Successfully";
//        }
//        else{
//            return "Email Not Sent";
//        }
//    }
}
