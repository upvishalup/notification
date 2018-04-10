package com.game.notification.message;


/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */

public interface Notification {

    void send(Message message) throws Exception;

}
