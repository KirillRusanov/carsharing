package carsharing.web.controller.payload;

import lombok.Data;

import java.util.List;


@Data
public class JwtResponse {
    private String type = "Bearer";
    private String token;
    private String email;
    private String name;
    private List<String> roles;

    public JwtResponse(String accessToken, String name, String email, List<String> roles) {
        this.token = accessToken;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

}
