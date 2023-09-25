package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 6841243381667144643L;

	private String resourceName;

    private String fieldName;

    private Object fieldValue;

    private String errorCode;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue, String errorCode) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.errorCode = errorCode;
    }
}
