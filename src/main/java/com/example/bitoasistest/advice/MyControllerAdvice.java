package com.example.bitoasistest.advice;

import com.example.bitoasistest.service.TickerService;
import lombok.extern.flogger.Flogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class MyControllerAdvice {
    private static final Logger log = LogManager.getLogger(MyControllerAdvice.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFoundException(final EntityNotFoundException e) {
        log.error("Exception {}",e);
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("error message", e.getMessage());
        errorInfo.put("timestamp", new Date());
        return new ResponseEntity<Map<String, Object>>(errorInfo, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> exception(final Exception e) {
        log.error("Exception {}",e);
        Map<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("error message", e.getMessage());
        errorInfo.put("timestamp", new Date());
        return new ResponseEntity<Map<String, Object>>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
