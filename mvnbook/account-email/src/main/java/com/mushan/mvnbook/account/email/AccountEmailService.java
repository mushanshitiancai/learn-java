package com.mushan.mvnbook.account.email;

/**
 * Created by mazhibin on 16/4/20
 */
public interface AccountEmailService {
    void sendMail(String to,String subject,String htmlText);
}
