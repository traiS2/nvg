package payload.response.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginByEmailResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;
}
