package rus.cheremisin.churchsong.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateBandRequest(

        @NotBlank(message = "Band name cannot be blank")
        @Size(min = 2, max = 100, message = "Band name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Contact phone cannot be blank")
        @Pattern(
                regexp = "^\\+?[0-9\\-() ]{7,20}$",
                message = "Invalid phone number format"
        )
        String contactPhone,

        @Size(max = 1000, message = "Bio cannot exceed 1000 characters")
        String bio
) {
}
