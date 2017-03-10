package com.santander.serenity.security.service.error;

import com.santander.serenity.security.service.json.ErrorResponse;
import com.santander.serenity.security.service.error.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * Error handler class for exceptions in {@link com.santander.serenity.security.service}.
 */
@ControllerAdvice
@RestController
public class ErrorHandler {
    // Logger instance
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    /**
     * Method that captures an Exception object and generates an {@link ErrorResponse} object.
     *
     * @param exception
     *            The exception handled
     * @param response
     *            The HTTP Response object
     * @return An ErrorResponse object with error data formated
     */
    @ExceptionHandler({Exception.class})
    public ErrorResponse exceptionHandler(Exception exception, HttpServletResponse response) {
        logger.debug("Entering exception handling method for '{}'", exception.getClass().getName());
        ErrorResponse errorResponse = new ErrorResponse(ErrorService.UNEXPECTED_ERROR);
        response.setStatus(ErrorService.UNEXPECTED_ERROR.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        logger.debug("Leaving exception handling method for '{}'. Result: '{}'", exception.getClass().getName(), errorResponse);
        return errorResponse;
    }

    /**
     * Method that captures a {@link HttpRequestMethodNotSupportedException} object and generates an {@link ErrorResponse} object.
     *
     * @param exception
     *            The exception handled
     * @param response
     *            The HTTP Response object
     * @return An ErrorResponse object with error data formated
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse httpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException exception, HttpServletResponse response) {
        logger.debug("Entering exception handling method for '{}'", exception.getClass().getName());
        ErrorResponse errorResponse = new ErrorResponse(ErrorService.REQUEST_METHOD_NOT_SUPPORTED);

        String detailExtended = " Method '" + exception.getMethod() + "' not supported. Methods supported: ";

        for (HttpMethod method : exception.getSupportedHttpMethods()) {
            detailExtended = detailExtended.concat("'" + method.name() + " ");
        }

        errorResponse.appendDetail(detailExtended + ".");

        response.setStatus(ErrorService.REQUEST_METHOD_NOT_SUPPORTED.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        logger.debug("Leaving exception handling method for '{}'. Result: '{}'", exception.getClass().getName(), errorResponse);
        return errorResponse;
    }

    /**
     * Method that captures an {@link IllegalArgumentException}, {@link HttpMessageConversionException} or {@link HttpMediaTypeException}
     * object and generates an {@link ErrorResponse} object.
     *
     * @param exception
     *            The exception handled
     * @param response
     *            The HTTP Response object
     * @return An ErrorResponse object with error data formated
     */
    @ExceptionHandler({IllegalArgumentException.class, HttpMessageConversionException.class, HttpMediaTypeException.class})
    public ErrorResponse illegalArgumentException(Exception exception, HttpServletResponse response) {
        logger.debug("Entering exception handling method for '{}'", exception.getClass().getName());
        ErrorResponse errorResponse = new ErrorResponse(ErrorService.BAD_REQUEST);
        response.setStatus(ErrorService.BAD_REQUEST.getCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        logger.debug("Leaving exception handling method for '{}'. Result: '{}'", exception.getClass().getName(), errorResponse);
        return errorResponse;
    }
}
