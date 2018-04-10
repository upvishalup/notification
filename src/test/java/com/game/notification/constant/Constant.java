package com.game.notification.constant;

import io.vertx.core.json.JsonObject;

public interface Constant {

    String SMTP_SERVER_ADDRESS_KEY = "smtp.server.address";
    String SMTP_TLS_PORT_KEY = "smtp.port.tls";
    String SMTP_SSL_PORT_KEY = "smtp.port.ssl";
    String SMTP_SSL_SSL_REQUIRED_KEY = "smtp.tls.ssl.required";
    String SMTP_AUTH_USERNAME = "smtp.auth.username";
    String SMTP_AUTH_PASSWORD = "smtp.auth.password";

    String USERNAME = "devaccounts@teewe.in";
    String PASSWORD = "teewedev123";

    int EMAIL_MIN_IDLE = 5;
    int EMAIL_MAX_IDLE = 10;
    int EMAIL_VALIDATE_TIMEUNIT = 5;

    int SLACK_MIN_IDLE = 5;
    int SLACK_MAX_IDLE = 10;
    int SLACK_VALIDATE_TIMEUNIT = 5;

    int SMS_MIN_IDLE = 5;
    int SMS_MAX_IDLE = 10;
    int SMS_VALIDATE_TIMEUNIT = 5;

}
