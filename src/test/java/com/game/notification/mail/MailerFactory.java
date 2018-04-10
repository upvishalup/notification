package com.game.notification.mail;

import com.game.notification.constant.Constant;
import com.game.notification.pool.JavaMailSession;
import com.game.notification.pool.ObjectPool;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Session;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.game.notification.constant.Constant.*;

/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
public class MailerFactory {

    private static Logger logger = LoggerFactory.getLogger(MailerFactory.class);

    private JsonObject config;
    private ObjectPool<Session> mailSession;

    private static MailerFactory instance;
    private static AtomicBoolean init = new AtomicBoolean(false);

    public static MailerFactory getInstance() {
        if(!init.compareAndSet(false, true)) return instance;
        instance = new MailerFactory();
        return instance;
    }

    private MailerFactory() {
        this.config = new JsonObject("{\n" +
                "  \"smtp.server.address\":\"smtp.gmail.com\",\n" +
                "  \"smtp.port.tls\":\"587\",\n" +
                "  \"smtp.port.ssl\":\"465\",\n" +
                "  \"smtp.tls.ssl.required\":\"true\"\n" +
                "}");
    }

    public javax.mail.Session getMailSession(){
        mailSession = new ObjectPool<Session>(EMAIL_MIN_IDLE, EMAIL_MAX_IDLE, EMAIL_VALIDATE_TIMEUNIT) {
            @Override
            protected Session createObject() {
                JavaMailSession mailSession = new JavaMailSession();
                return mailSession.getSession(config);
            }
        };
        return mailSession.get();
    }


}
