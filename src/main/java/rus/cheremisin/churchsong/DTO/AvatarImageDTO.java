package rus.cheremisin.churchsong.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvatarImageDTO(
        @NotNull
        Long id,
        @NotNull
        @NotBlank
        String link
) {
}