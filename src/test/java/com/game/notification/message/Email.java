package com.game.notification.message;

import com.game.notification.constant.Constant;
import com.game.notification.mail.MailerFactory;
import org.apache.commons.lang3.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */

public class Email implements Notification {

    @Override
    public void send(Message message) throws MessagingException {
        message.setFrom(Constant.USERNAME);
        MimeMessage mimeMessage = new MimeMessage(MailerFactory.getInstance().getMailSession());
        mimeMessage.setFrom(new InternetAddress(message.getFrom()));
        mimeMessage.addRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(StringUtils.join(message.getTo())));
        mimeMessage.setSubject(message.getSubject());
        mimeMessage.setText(message.getBody(), "utf-8", "html");
        Transport.send(mimeMessage);
    }
}
