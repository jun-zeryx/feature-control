package com.moneylion.assessment.controller;

import com.moneylion.assessment.exception.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<Object> handleCommonException(final CommonException ex) {
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("code", ex.getErrorCode().getCode());
        response.put("message", ex.getErrorCode().getMessage());

        return new ResponseEntity<>(response, ex.getErrorCode().getHttpStatus());
    }

    public ResponseEntity<Object> handleException(final Exception ex) {
        Map<String,Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
