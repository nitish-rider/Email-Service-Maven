# Email-Service-Maven

### Reference Documentation

* [Backend Code](https://github.com/nitish-rider/Email-Service-Backend)
* [Encryption](https://www.codejava.net/frameworks/spring-boot/spring-boot-password-encryption)

### Running the Project 
Simple Spring Boot rest service to send Email using Gmail(Encrypted Username and Password)

Run project as Spring Boot App hit the service using rest client like Postman

Rest Service URL for Text(Preset): http://localhost:8081/sendEmail

Rest Service URL for Text and Attachment(Preset): http://localhost:8081/sendEmailAtt

Rest Service URL for Text (Post Request): http://localhost:8081/send

Request Body:
```json
{
  "recipientEmailId": "Receiver's email ID",
  "emailSubject": "Subject",
  "emailMessage": "Body of Email"
}
```

####Note:Replace the username with your Gmail id and password in application.properties file