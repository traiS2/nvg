package payload.response.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginByUsernameResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;

    public LoginByUsernameResponse(String token, Long id, String username, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
