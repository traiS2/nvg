package payload.request.login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginByUsernameRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
