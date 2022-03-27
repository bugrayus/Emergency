package vodafone.hackathon.emergency.model.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SafeZoneResponseModel {
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitudes;
}
