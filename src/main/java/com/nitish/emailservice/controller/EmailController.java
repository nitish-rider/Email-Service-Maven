package com.nitish.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/sendEmail")
    public String sendEmail(){

        MimeMessage msg = javaMailSender.createMimeMessage();
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
