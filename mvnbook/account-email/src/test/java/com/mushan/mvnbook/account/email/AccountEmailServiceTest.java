package com.mushan.mvnbook.account.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * Created by mazhibin on 16/4/20
 */
public class AccountEmailServiceTest {
    private GreenMail greenMail;

    @Before
    public void startMailServer(){
        greenMail = new GreenMail(new ServerSetup(12001,null,"smtp"));
        greenMail.start();
    }

    @Test
    public void testSendMail() throws InterruptedException, MessagingException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        AccountEmailService accountEmailService = (AccountEmailService)ctx.getBean("accountEmailService");

        String subject = "test";
        String htmlText = "<h3>Test</h3>";
        accountEmailService.sendMail("mushanmail@126.com",subject,htmlText);
        greenMail.waitForIncomingEmail(2000, 1);
        Message[] messages = greenMail.getReceivedMessages();
        Assert.assertEquals(1, messages.length);
        Assert.assertEquals(subject,messages[0].getSubject());
        Assert.assertEquals(htmlText, GreenMailUtil.getBody(messages[0]).trim());
    }

    @After
    public void stopMailServer(){
        greenMail.stop();
    }
}
