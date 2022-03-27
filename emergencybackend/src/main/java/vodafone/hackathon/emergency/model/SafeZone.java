package vodafone.hackathon.emergency.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SafeZone extends BaseModel{
    @Column(precision = 7,scale = 5)
    private BigDecimal longitude;
    @Column(precision = 7,scale = 5)
    private BigDecimal latitudes;
}
