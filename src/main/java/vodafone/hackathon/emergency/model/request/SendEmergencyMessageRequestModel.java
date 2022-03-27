package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SendEmergencyMessageRequestModel {
    Long emergencyId;
    BigDecimal longitude;
    BigDecimal latitudes;
}
