package com.nitish.emailservice.model;

import org.springframework.web.multipart.MultipartFile;

public class FileEmail {
    private String recipientEmailId;
    private String emailSubject;
    private String emailMessage;
    private MultipartFile file;

    public String getRecipientEmailId() {
        return recipientEmailId;
    }

    public void setRecipientEmailId(String recipientEmailId) {
        this.recipientEmailId = recipientEmailId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
