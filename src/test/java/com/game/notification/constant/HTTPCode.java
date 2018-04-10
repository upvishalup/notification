package com.game.notification.constant;

public enum HTTPCode {

    OK_200(200, "Ok"),
    CREATED_201(201, "Created"),// Resource created on server.
    ACCEPTED_202(202, "Accepted"), // Request has been accepted but no fully completed.
    NO_CONTENT_204(204, "No Content"), //Request fulfilled the request and has only status to return without any entity
    RESET_CONTENT_205(205, "Reset Content"), //Must not return any entity. Initiate another user input session after clearing the last one
    PARTIAL_CONTENT_206(206, "Partial Content"), // Partial Content in response, may or may not content the range of content.


    BAD_REQUEST_400(400, "Bad Request"), //domain validation error or missing request, mostly given by servers
    UNAUTHORIZED_401(401, "Unauthorized. Please try again."), // On username and password wrong - Authentication error.
    PAYMENT_REQUIRED_402(402, "Payment Required"), //
    FORBIDDEN_403(403, "Forbidden"), // Do not have permission of the resources.
    NOT_FOUND_404(404, "Not Found"),
    METHOD_NOT_ALLOWED_405(405, "Method Not Allowed"),
    NOT_ACCEPTABLE_406(406, "Not Acceptable"), // Eg : Location of request is invalid
    REQUEST_TIMEOUT_408(408, "Request Timeout"),
    PAYLOAD_TOO_LARGE_413(413, "Payload too large"),
    UNPROCCESABLE_ENTITY_422(422, "Unproccesable Entity"),

    INTERNAL_SERVER_ERROR_500(500, "Internal Server Error"),
    NOT_IMPLEMENTED_501(501, "Not implemented"),
    WEBSERVER_IS_DOWN_521(521, "Web Server is down"),
    CONNECTION_TIMEOUT_522(522, "Connection Timeout"),
    ORIGIN_NOT_REACHABLE_523(523, "Origin Not Reachable"),
    A_TIMEOUT_OCCURED_524(524, "A Timeout Occurred");

    int code;
    String message;

    HTTPCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
