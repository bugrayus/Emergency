package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UpdateEmergencyRequestModel {
    private Long id;
    private String name;
    private String info;
    @Email
    private String emergencyMail;
    private String mailContent;
}
