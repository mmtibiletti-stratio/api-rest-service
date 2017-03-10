package com.santander.serenity.security.service.error;

import org.springframework.http.HttpStatus;

/**
 * Enum with possible errors returned by the ServiceName.
 */

public enum ErrorService {

    REQUEST_METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED.value(), "request_method_not_supported", "Request method not supported."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "bad_request", "Request is malformed or there are missing mandatory parameters."),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal_server_error", "Internal server error. Server logs may have additional information.");

    private final int code;
    private final String error;
    private final String detail;

    private ErrorService(int statusCode, String error, String detail) {
        this.code = statusCode;
        this.error = error;
        this.detail = detail;
    }

    /**
     * Returns the integer code of the error.
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the detail message of the error.
     *
     * @return
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Returns the error message of the error.
     *
     * @return
     */
    public String getError() {
        return error;
    }
}
