package com.bencu.arcanstore.shared.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseData<Object>> handle(Exception ex,
                                                       HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Object> responseData = new ResponseData<>();
        String formattedMessage = ex.getMessage().replace("com.bencu.arcanstore", "").replace("\"", "");
        if (ex instanceof NullPointerException) {
            responseData.setStatus(false);
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setHttpStatusCode(400);
            responseData.setData(null);
            responseData.getMessages().add(formattedMessage);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(false);
        responseData.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseData.setHttpStatusCode(500);
        responseData.setData(null);
        responseData.getMessages().add(formattedMessage);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }
}
