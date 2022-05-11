package com.nitish.emailservice.controller;

import com.nitish.emailservice.model.TextEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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
    }

    @RequestMapping(value = "/sendEmailAtt")
    public String sendEmailAtt() throws MessagingException {
        String path = "C:\\Users\\nitis\\Desktop\\MOM_3196.jpg";
        File file = new File(path);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("nitish102000@gmail.com");
        helper.setSubject("Testing on 11-05-2022");
        helper.setText("Kya Aj Hoga!!!!!!");
        helper.addAttachment("MyPic",file);

        javaMailSender.send(message);
        logger.debug("Email send to {} ", "nitish102000@gmail.com");
        return "Email Sent Successfully";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendEmailBdy(@RequestBody TextEmail textEmail) throws MessagingException {
        Assert.notNull(textEmail, "Email must not be null");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(textEmail.getRecipientEmailId());
        helper.setSubject(textEmail.getEmailSubject());
        helper.setText(textEmail.getEmailMessage(), true); // true indicates html

        javaMailSender.send(message);
        logger.debug("Email send to {} ", textEmail.getRecipientEmailId());
        return "Email Send Successfully";

    }


}
