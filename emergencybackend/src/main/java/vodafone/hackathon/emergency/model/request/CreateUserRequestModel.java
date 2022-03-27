package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class CreateUserRequestModel {
    @Email
    private String mail;
    private String password;
}
