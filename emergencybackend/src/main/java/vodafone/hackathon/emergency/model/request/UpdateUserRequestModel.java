package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UpdateUserRequestModel {
    private Long id;
    @Email
    private String mail;
    private String password;
}
