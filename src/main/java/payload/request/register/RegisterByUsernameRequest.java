package payload.request.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RegisterByUsernameRequest {
    @NotBlank
    @Size(min = 4, message = "Username must be greater than 6 character")
    private String username;
    @NotBlank
    @Size(min = 4, message = "Password must be greater than 6 character")
    private String password;
}
