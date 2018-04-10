package com.game.notification.controller;

import com.game.notification.constant.HTTPCode;
import com.game.notification.constant.NotificationType;
import com.game.notification.mail.MailJobManager;
import com.game.notification.message.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationHandler extends Handler<RoutingContext>{

    private static final Logger logger = LoggerFactory.getLogger(NotificationHandler.class);

    public static NotificationHandler create(){
        return new NotificationHandler();
    }

    @Override
    public void handle(RoutingContext ctx) {
        try{
            if(StringUtils.isEmpty(ctx.getBodyAsString())){
                getResponse(ctx.response(), HTTPCode.BAD_REQUEST_400, new JsonObject().put("message", "Json is empty"));
                return;
            }
            Message message = Json.decodeValue(ctx.getBodyAsString(), Message.class);
            if(message.getType().ordinal() != NotificationType.EMAIL.ordinal()){
                getResponse(ctx.response(), HTTPCode.NOT_IMPLEMENTED_501, new JsonObject().put("message", "Not Implemented"));
                return;
            }

            // from field not needed for notification type EMAIL
            if((message.getFrom() ==  null || StringUtils.isEmpty(message.getFrom()))
                    && !(NotificationType.EMAIL.ordinal() == message.getType().ordinal())){
                getResponse(ctx.response(), HTTPCode.UNPROCCESABLE_ENTITY_422, new JsonObject().put("message", "field from is empty"));
                return;
            }

            if(message.getTo() == null || message.getTo().length == 0){
                getResponse(ctx.response(), HTTPCode.UNPROCCESABLE_ENTITY_422, new JsonObject().put("message", "field from is empty"));
                return;
            }

            MailJobManager.getInstance().makeRequest(message);
            getResponse(ctx.response(),
                    HTTPCode.OK_200, new JsonObject().put("message", "Message Sent"));
        }catch (Throwable t){
            logger.error("",t);
            getResponse(ctx.response(),
                    HTTPCode.INTERNAL_SERVER_ERROR_500, new JsonObject().put("message", t.getMessage()));
        }
    }

}
