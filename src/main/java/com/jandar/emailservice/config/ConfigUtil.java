package com.jandar.emailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ConfigUtil {
    @Value("${myEmailSMTPHost}")
    private String myEmailSMTPHost;
    @Value("${myEmailAccount}")
    private String myEmailAccount;
    @Value("${myEmailPassword}")
    private String myEmailPassword;

    private static String MY_EMAIL_SMTP_HOST;
    private static String MY_EMAIL_ACCOUNT;
    private static String MY_EMAIL_PASSWORD;

    public ConfigUtil() {
        init();
    }
    @PostConstruct
    public void init() {
        MY_EMAIL_SMTP_HOST = myEmailSMTPHost;
        MY_EMAIL_ACCOUNT = myEmailAccount;
        MY_EMAIL_PASSWORD = myEmailPassword;
    }

    public static String getMyEmailSmtpHost() {
        return MY_EMAIL_SMTP_HOST;
    }

    public static String getMyEmailAccount() {
        return MY_EMAIL_ACCOUNT;
    }

    public static String getMyEmailPassword() {
        return MY_EMAIL_PASSWORD;
    }
}
