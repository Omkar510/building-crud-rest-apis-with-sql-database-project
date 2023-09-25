package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 5919089041382454439L;
	
    private String message;

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
