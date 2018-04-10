package com.game.notification.message;

import com.game.notification.constant.NotificationType;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
public class NotificationFactory {

    private Notification notification;

    private static NotificationFactory instance;
    private static AtomicBoolean init = new AtomicBoolean(false);

    private NotificationFactory() {
    }

    public static NotificationFactory getInstance() {
        if(!init.compareAndSet(false, true)) return instance;
        instance = new NotificationFactory();
        return instance;
    }

    public Notification getNotification(NotificationType type) {
        switch (type){
            case SLACK:
                notification = new Slack();
                break;
            case SMS:
                notification = new SMS();
                break;
            case EMAIL:
                notification = new Email();
                break;
        }
        return notification;
    }
}
