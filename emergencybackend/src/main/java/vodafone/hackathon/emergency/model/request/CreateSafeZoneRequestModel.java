package vodafone.hackathon.emergency.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSafeZoneRequestModel {
    private BigDecimal longitude;
    private BigDecimal latitudes;
}
