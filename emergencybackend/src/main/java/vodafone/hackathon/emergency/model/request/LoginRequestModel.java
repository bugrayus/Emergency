package vodafone.hackathon.emergency.model.request;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String mail;
    private String password;
}
