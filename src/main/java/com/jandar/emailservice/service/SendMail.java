package com.jandar.emailservice.service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jandar.emailservice.config.ConfigUtil;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SendMail {
    private static void handleAddress(String[] to, Address[] addresses) {
        if (to.length > 0) {
            for (int i = 0; i < to.length; i++) {
                String toName = to[i].split(":")[0];
                String toMail = to[i].split(":")[1];
                try {
                    addresses[i] = new InternetAddress(toMail, toName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static MimeMessage createMimeMessage(Session session, String subject, String content,
                                                String fromMail, String fromName,
                                                String[] to,
                                                String[] cc,
                                                String[] bcc
    ) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String date;
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(fromMail, fromName, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        if (to != null && to.length > 0) {
            Address[] toAddresses = new Address[to.length];
            handleAddress(to, toAddresses);
            message.addRecipients(Message.RecipientType.TO, toAddresses);
        }

        if (cc != null && cc.length > 0) {
            Address[] ccAddresses = new Address[cc.length];
            handleAddress(cc, ccAddresses);
            message.addRecipients(Message.RecipientType.CC, ccAddresses);
        }

        if (bcc != null && bcc.length > 0) {
            Address[] bccAddresses = new Address[bcc.length];
            handleAddress(bcc, bccAddresses);
            message.addRecipients(Message.RecipientType.BCC, bccAddresses);
        }

        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());


        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    public static Boolean sendEmail(String subject, String content,
                                    String fromName,
                                    String[] to,
                                    String[] cc,
                                    String[] bcc) {
        String myEmailAccount = ConfigUtil.getMyEmailAccount();
        String myEmailPassword = ConfigUtil.getMyEmailPassword();
        String myEmailSMTPHost = ConfigUtil.getMyEmailSmtpHost();
        try {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties(); // 参数配置
            // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.transport.protocol", "smtp");
            // 发件人的邮箱的 SMTP服务器地址
            props.setProperty("mail.smtp.host", myEmailSMTPHost);
            // 需要请求认证
            props.setProperty("mail.smtp.auth", "true");

            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
//			session.setDebug(true);
            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, subject, content, myEmailAccount, fromName, to, cc, bcc);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
            // 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
            System.out.println("邮件发送成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
            return false;
        }

    }

}
