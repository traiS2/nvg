package payload.request.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginByUsernameRequest {
    private String username;
    private String password;
}
