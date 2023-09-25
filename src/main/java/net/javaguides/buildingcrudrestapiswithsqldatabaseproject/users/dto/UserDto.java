package net.javaguides.buildingcrudrestapiswithsqldatabaseproject.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    
    private Long userId;

    // User first name should not be null or empty

    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    // User last name should not be null or empty

    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    // User email should not be null or empty
    // Email address should be valid

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String emailAddress;
}
