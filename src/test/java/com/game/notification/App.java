package com.game.notification;

import com.game.notification.controller.NotificationService;
import io.vertx.core.Vertx;

public class App {
    public static void main(String args[]){

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(NotificationService.class.getName());

    }
}
