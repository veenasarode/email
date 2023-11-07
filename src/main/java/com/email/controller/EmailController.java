package com.email.controller;

import com.email.model.EmailRequest;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping("/welcome")
    public String welcome()
    {

        return "hello this is my email api";
    }
   // @GetMapping("/mail")
    //api to send email
   @RequestMapping(value = "/sendemail" , method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){

        System.out.println(emailRequest);
       boolean result = this.emailService.sendEmail(emailRequest.getSubject(),emailRequest.getMessage(),emailRequest.getTo());
       if(result) {
           return ResponseEntity.ok("Email sent successfully");
       }else{
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent....");
       }
    }
}
