package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {

        String path = webRequest.getDescription(false);

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), resourceNotFoundException.getMessage(),
                path != null && !path.isBlank() ? path.substring(4, path.length()) : path, resourceNotFoundException.getErrorCode());

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException emailAlreadyExistsException, WebRequest webRequest) {

        String path = webRequest.getDescription(false);

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), emailAlreadyExistsException.getMessage(),
                path != null && !path.isBlank() ? path.substring(4, path.length()) : path, "USER_EMAIL_ALREADY_EXISTS");

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(
            Exception exception, WebRequest webRequest) {

        String path = webRequest.getDescription(false);

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
                path != null && !path.isBlank() ? path.substring(4, path.length()) : path, "INTERNAL SERVER ERROR");

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
