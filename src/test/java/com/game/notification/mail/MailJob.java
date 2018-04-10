package com.game.notification.mail;

import com.game.notification.message.Message;
import com.game.notification.message.NotificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
public class MailJob implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(MailJob.class);
    private Message message;

    public MailJob(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        try {
            NotificationFactory.getInstance().getNotification(message.getType()).send(message);
        }catch (Exception e){
            logger.error("run()" , e);
        }
    }

}
