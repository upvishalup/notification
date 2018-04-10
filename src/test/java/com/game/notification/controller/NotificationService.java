package com.game.notification.controller;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationService extends AbstractVerticle {

    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        vertx.executeBlocking(future -> {
            HttpServer server = vertx.createHttpServer();
            logger.info("Setting up routes");
            Router router = Router.router(vertx);
            server.requestHandler(router::accept);
            router.route().handler(CookieHandler.create());
            router.route().handler(BodyHandler.create());
            router.route("/static/*").handler(StaticHandler.create().setWebRoot("static"));

            router.route().handler(CorsHandler.create("*")
                    .allowedMethod(HttpMethod.GET)
                    .allowedMethod(HttpMethod.POST)
                    .allowedMethod(HttpMethod.OPTIONS)
                    .allowedHeader("access_token")
                    .allowedHeader("Access-Control-Allow-Origin")
                    .allowedHeader("Content-Type"));

            try {
                router.post().path("/notification/send").handler(NotificationHandler.create());
            } catch (Throwable e) {
                future.fail(e);
                return;
            }

            logger.error("Starting server");
            server.listen(8080, "0.0.0.0", httpServerAsyncResult -> {
                if (httpServerAsyncResult.succeeded()) {
                    logger.error("Webserver is listening");
                    future.complete();
                } else {
                    future.fail(httpServerAsyncResult.cause());
                }
            });
        }, result -> {
            if (result.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(result.cause());
            }
        });


    }

}
