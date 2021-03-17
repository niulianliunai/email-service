package com.jandar.emailservice.controller;

<<<<<<< HEAD
import com.jandar.emailservice.util.SendMailUtil;
=======
import com.jandar.emailservice.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c87462d49677c7010e1707b6831bfc90d55cd1aa
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/sendMail")
public class SendMailController {
<<<<<<< HEAD

=======
    @Autowired
    SendMail sendMail;
>>>>>>> c87462d49677c7010e1707b6831bfc90d55cd1aa
    @PostMapping("/send")
    public boolean send(String subject ,String content,
                        String fromName,
                        String[] to,
                        String[] cc,
                        String[] bcc) {
<<<<<<< HEAD
        return SendMailUtil.sendEmail(subject, content, fromName, to, cc, bcc);
=======
        return sendMail.sendEmail(subject, content, fromName, to, cc, bcc);
>>>>>>> c87462d49677c7010e1707b6831bfc90d55cd1aa
    }
}
