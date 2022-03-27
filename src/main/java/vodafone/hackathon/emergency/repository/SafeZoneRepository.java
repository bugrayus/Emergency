package vodafone.hackathon.emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vodafone.hackathon.emergency.model.SafeZone;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SafeZoneRepository extends JpaRepository<SafeZone, Long> {
    boolean existsByLatitudesAndLongitude(BigDecimal latitude, BigDecimal longitude);

    List<SafeZone> findAllByLongitudeBetweenAndLatitudesBetween(BigDecimal longitude, BigDecimal longitude2, BigDecimal latitudes, BigDecimal latitudes2);
}
