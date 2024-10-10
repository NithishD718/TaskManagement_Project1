package com.emailflow.email_service.Contoller;

import com.emailflow.email_service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/email")
@RestController
public class EmailController {
    @Autowired
    EmailSenderService emailSenderService;
    @PostMapping("/send")
     public String sendEmail(@RequestParam String toMail ,@RequestParam String subject ,@RequestParam String body)
     {
        emailSenderService.sendMail(toMail,subject,body);
         return "OKK";
     }
}
