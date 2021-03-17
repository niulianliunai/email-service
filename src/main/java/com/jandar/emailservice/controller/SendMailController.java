package com.jandar.emailservice.controller;

import com.jandar.emailservice.util.SendMailUtil;
import com.jandar.emailservice.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/sendMail")
public class SendMailController {
    @Autowired
    SendMail sendMail;
    @PostMapping("/send")
    public boolean send(String subject ,String content,
                        String fromName,
                        String[] to,
                        String[] cc,
                        String[] bcc) {
        return SendMailUtil.sendEmail(subject, content, fromName, to, cc, bcc);
    }
}
