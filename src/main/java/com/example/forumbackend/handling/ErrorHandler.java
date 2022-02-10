package com.example.forumbackend.handling;

import com.example.forumbackend.exception.UserAlreadyExistsException;
import com.example.forumbackend.exception.UserDoesNotExistsException;
import com.example.forumbackend.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleConflict(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserDoesNotExistsException.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({WrongPasswordException.class})
    public ResponseEntity<Object> handleForbidden(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
