package com.electrolux.dmp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Handle all Exceptions in for All controllers
 */
@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);


    @ExceptionHandler(NoSuchElementException.class)
    public void handleNoSuchElementException(NoSuchElementException ex, HttpServletResponse res) throws IOException {
        LOGGER.error("Handled No Such element Exception", ex);
        res.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public void handleRunTimeException(RuntimeException ex, HttpServletResponse res) throws IOException {
        LOGGER.error("Handled No Such element Exception", ex);
        res.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, HttpServletResponse res) throws IOException {
        LOGGER.error("Handled Internal Error Exception", ex);
        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
    }

}

