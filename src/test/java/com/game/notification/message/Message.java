package com.game.notification.message;

import com.game.notification.constant.NotificationType;

/**
 * Notification Message Model
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
public class Message {

    private String from;

    private String[] to;

    private String subject;

    private String body;

    private NotificationType type;

    public Message(String from, String[] to, String subject, String body, NotificationType type) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.type = type;
    }

    public Message() {
    }

    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public NotificationType getType() {
        return type;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
