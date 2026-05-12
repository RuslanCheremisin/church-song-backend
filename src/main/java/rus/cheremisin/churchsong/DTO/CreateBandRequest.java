package rus.cheremisin.churchsong.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateBandRequest(

        @NotBlank(message = "Название группы не может быть пустым")
        @Size(min = 2, max = 100, message = "Название группы должно содержать от 2 до 100 символов")
        String name,

        @NotBlank(message = "Email не может быть пустым")
        @Email(message = "Неверный формат email")
        String email,

        @NotBlank(message = "Номер телефона не может быть пустым")
        @Pattern(
                regexp = "^\\+?[0-9\\-() ]{7,20}$",
                message = "Неверный формат номера телефона"
        )
        String contactPhone,

        @Size(max = 1000, message = "Био не должно превышать 1000 символов")
        String bio
) {
}
