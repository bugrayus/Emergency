package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class CreateMailToSendMessageRequestModel {
    @Email
    private String mail;
}
