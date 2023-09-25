package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
                        ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {

                String path = webRequest.getDescription(false);

                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                resourceNotFoundException.getMessage(),
                                path != null && !path.isBlank() ? path.substring(4, path.length()) : path,
                                resourceNotFoundException.getErrorCode());

                return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(
                        EmailAlreadyExistsException emailAlreadyExistsException, WebRequest webRequest) {

                String path = webRequest.getDescription(false);

                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                                emailAlreadyExistsException.getMessage(),
                                path != null && !path.isBlank() ? path.substring(4, path.length()) : path,
                                "USER_EMAIL_ALREADY_EXISTS");

                return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body,
                        HttpHeaders headers,
                        HttpStatusCode statusCode, WebRequest request) {

                String path = request.getDescription(false);

                ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                                path != null && !path.isBlank() ? path.substring(4, path.length()) : path,
                                "INTERNAL SERVER ERROR");

                return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        	HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        	Map<String, String> errors = new HashMap<>();
        	
        	List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        	
        	errorList.stream().forEach(error -> {
        		String fieldName = ((FieldError) error).getField();
        		String defaultMessage = error.getDefaultMessage();
        		
        		errors.put(fieldName, defaultMessage);
        	});
        	
        	return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
}
