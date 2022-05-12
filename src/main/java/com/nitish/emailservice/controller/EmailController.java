package com.nitish.emailservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitish.emailservice.model.FileEmail;
import com.nitish.emailservice.model.TextEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Date;
import java.util.Objects;

@RestController
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private static void logFun(String text , File file) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        text = text + " at " + new Date().toString() + "\n";
        bw.write(text);
        bw.close();
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    public String sendEmail() throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("nitish102000@gmail.com");
        helper.setSubject("Testing on 11-05-2022");
        helper.setText("Kya Aj Hoga!!!!!!");

        javaMailSender.send(message);

        try{
            File obj = new File("PreBuildLogs.txt");
            if (!obj.exists())
                obj.createNewFile();
            logFun("Email(Text Preset) send to nitish102000@gmail.com",obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Email send to {} ", "nitish102000@gmail.com");
        return "Email Sent Successfully";
    }

    @RequestMapping(value = "/sendEmailAtt", method = RequestMethod.GET)
    public String sendEmailAtt() throws MessagingException {
        String path = "testImg.jpg";
        File file = new File(path);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("nitish102000@gmail.com");
        helper.setSubject("Testing on 11-05-2022");
        helper.setText("Kya Aj Hoga!!!!!!");
        helper.addAttachment("MyPic",file);

        javaMailSender.send(message);
        try{
            File obj = new File("PreBuildLogs.txt");
            if (!obj.exists())
                obj.createNewFile();
            logFun("Email(Image Preset) send to nitish102000@gmail.com",obj);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
        try{
            File obj = new File("Logs.txt");
            if (!obj.exists())
                obj.createNewFile();
            logFun("Email(Text Body) send to nitish102000@gmail.com",obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Email send to {} ", textEmail.getRecipientEmailId());
        return "Email Send Successfully";

    }

    @RequestMapping(value = "/sendFile", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String sendEmailFlBdy(@ModelAttribute FileEmail fileEmail) throws MessagingException {
        Assert.notNull(fileEmail, "Email must not be null");

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(fileEmail.getRecipientEmailId());
        helper.setSubject(fileEmail.getEmailSubject());
        helper.setText(fileEmail.getEmailMessage(), true); // true indicates html
        helper.addAttachment(Objects.requireNonNull(fileEmail.getFile().getOriginalFilename()),fileEmail.getFile());

        javaMailSender.send(message);
        try{
            File obj = new File("Logs.txt");
            if (!obj.exists())
                obj.createNewFile();
            logFun("Email(Image Body) send to nitish102000@gmail.com",obj);

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("Email send to {} ",fileEmail.getRecipientEmailId() );
        return "Email Send Successfully";

    }


}
