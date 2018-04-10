package com.game.notification.controller;

import com.game.notification.constant.HTTPCode;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler<E> implements io.vertx.core.Handler<E>{

    private static final Logger logger = LoggerFactory.getLogger(Handler.class);

    protected void addSecurityHeader(HttpServerResponse response){
        // do not allow proxies to cache the data
        response .putHeader("Cache-Control", "no-store, no-cache")
                // prevents Internet Explorer from MIME - sniffing a
                // response away from the declared content-type
                .putHeader("X-Content-Type-Options", "nosniff")
                // Strict HTTPS (for about ~6Months)
                .putHeader("Strict-Transport-Security", "max-age=" + 15768000)
                // IE8+ do not allow opening of attachments in the context of this resource
                .putHeader("X-Download-Options", "noopen")
                // enable XSS for IE
                .putHeader("X-XSS-Protection", "1; mode=block")
                //Application JSON
                .putHeader("Content-Type", "application/json")
                // deny frames
                .putHeader("X-FRAME-OPTIONS", "DENY")
                .putHeader("Access-Control-Allow-Origin", "*");

    }

    protected void getResponse(HttpServerResponse res, HTTPCode code, Object payload){
        addSecurityHeader(res);
        JsonObject jsonRes = new JsonObject();
        jsonRes.put("status", new JsonObject()
        .put("code", code.getCode()).put("message", code.getMessage()))
        .put("payload", payload);
        logger.debug("Sending Rest API Response:" + jsonRes);
        res.setStatusCode(code.getCode());
        res.setStatusMessage(code.getMessage());
        logger.info("Common Payload: " +payload.toString());
        res.end(jsonRes.toString());
    }

    protected void publish(RoutingContext ctx, HTTPCode code, String key, Object payload){
        JsonObject response = new JsonObject();
        response.put("status", new JsonObject()
                .put("code", code.getCode()).put("message", code.getMessage()))
                .put("payload", payload);
        ctx.vertx().eventBus().publish(key, Json.encode(response));
    }
}
