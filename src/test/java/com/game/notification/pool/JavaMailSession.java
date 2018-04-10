package com.game.notification.pool;

import io.vertx.core.json.JsonObject;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
import static com.game.notification.constant.Constant.*;

public class JavaMailSession{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(JavaMailSession.class);

    public Session getSession(final JsonObject config){
        Session session = Session.getDefaultInstance(getProperty(config), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.getString(SMTP_AUTH_USERNAME, USERNAME), config.getString(SMTP_AUTH_PASSWORD, PASSWORD));
            }
        });
        return session;
    }

    private Properties getProperty(JsonObject config){
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.port", config.getString(SMTP_TLS_PORT_KEY));
        props.setProperty("mail.smtp.socketFactory.port", config.getString(SMTP_TLS_PORT_KEY));
        props.setProperty("mail.smtp.host", config.getString(SMTP_SERVER_ADDRESS_KEY));
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.auth", "true");
        return  props;
    }
}
