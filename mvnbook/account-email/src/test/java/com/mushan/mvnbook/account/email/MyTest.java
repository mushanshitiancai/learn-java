package com.mushan.mvnbook.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by mazhibin on 16/4/21
 */
public class MyTest {
    public static void main(String[] args) throws MessagingException, IOException {
        System.out.println("hello");

        GreenMail mailServer = new GreenMail(new ServerSetup(12000, null, "smtp"));
        mailServer.start();

        MyTest.javaMailTest();

        System.out.println(mailServer.getReceivedMessages()[0].getContent());

        mailServer.stop();

//        MyTest.greenMailTest();

//        GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP);
//        Session smtpSession = greenMail.getSmtp().createSession();
//
//        Message msg = new MimeMessage(smtpSession);
//        msg.setFrom(new InternetAddress("foo@example.com"));
//        msg.addRecipient(Message.RecipientType.TO,
//                new InternetAddress("bar@example.com"));
//        msg.setSubject("Email sent to GreenMail via plain JavaMail");
//        msg.setText("Fetch me via IMAP");
//        Transport.send(msg);
    }

    public static void greenMailTest(){
        GreenMail greenMail = new GreenMail(ServerSetupTest.SMTP_IMAP); //uses test ports by default
        greenMail.start();
        GreenMailUtil.sendTextEmailTest("mushanmail@126.com", "from@localhost.com", "some subject",
                "some body"); // --- Place your sending code here
        System.out.println((GreenMailUtil.getBody(greenMail.getReceivedMessages()[0])));
        greenMail.stop();
    }

    public static void javaMailTest(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService)ctx.getBean("accountEmailService");

        accountEmailService.sendMail("mushanmail@126.com","test","test");
    }
}
