package by.piskunou.solvdlaba.web.dto;

import by.piskunou.solvdlaba.domain.enums.Authority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDTO {
    @NotBlank
    @Size(max = 300, message = "The username must be less than 300 characters")
    private String username;
    @NotNull
    private Authority authority;
}