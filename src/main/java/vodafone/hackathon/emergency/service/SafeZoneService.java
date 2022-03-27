package vodafone.hackathon.emergency.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vodafone.hackathon.emergency.model.SafeZone;
import vodafone.hackathon.emergency.model.request.CreateSafeZoneRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateSafeZoneRequestModel;
import vodafone.hackathon.emergency.model.response.SafeZoneResponseModel;
import vodafone.hackathon.emergency.repository.SafeZoneRepository;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SafeZoneService {
    private final SafeZoneRepository safeZoneRepository;
    private final ModelMapper mapper;

    public boolean createSafeZone(CreateSafeZoneRequestModel requestModel) {
        if (safeZoneRepository.existsByLatitudesAndLongitude(requestModel.getLatitudes(), requestModel.getLongitude()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Safe zone already exists.");
        SafeZone safeZone = mapper.map(requestModel, SafeZone.class);
        safeZoneRepository.save(safeZone);
        return true;
    }

    public SafeZoneResponseModel findSafeZoneById(long id) {
        if (!safeZoneRepository.existsById(id))
            return null;
        return mapper.map(safeZoneRepository.getById(id), SafeZoneResponseModel.class);
    }

    public List<SafeZoneResponseModel> findSafeZones() {
        List<SafeZone> emergencies = safeZoneRepository.findAll();
        return emergencies.stream().map(x -> mapper.map(x, SafeZoneResponseModel.class)).collect(Collectors.toList());
    }

    public boolean deleteSafeZone(long id) {
        if (!safeZoneRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Safe zone not found  by that id.");
        safeZoneRepository.delete(safeZoneRepository.getById(id));
        return true;
    }

    public boolean updateSafeZone(UpdateSafeZoneRequestModel requestModel) {
        if (!safeZoneRepository.existsById(requestModel.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Safe zone not found by that id");
        SafeZone safeZone = safeZoneRepository.getById(requestModel.getId());
        mapper.map(requestModel, safeZone);
        safeZoneRepository.save(safeZone);
        return true;
    }

    public String findShortestSafeZone(BigDecimal longitude, BigDecimal latitudes) {
        String response = null;
        BigDecimal step = new BigDecimal("0");
        while (response == null) {
            List<SafeZone> safeZones = safeZoneRepository.findAllByLongitudeBetweenAndLatitudesBetween(longitude.subtract(step), longitude.add(step), latitudes.subtract(step), latitudes.add(step));
            if (!safeZones.isEmpty()) {
                SafeZone minSafeZone = safeZones.get(0);
                BigDecimal minDistance = minSafeZone.getLongitude()
                        .subtract(longitude)
                        .multiply(
                                minSafeZone
                                        .getLongitude()
                                        .subtract(longitude))
                        .add(
                                minSafeZone
                                        .getLatitudes()
                                        .subtract(latitudes)
                                        .multiply(
                                                minSafeZone
                                                        .getLatitudes()
                                                        .subtract(latitudes)))
                        .sqrt(new MathContext(10));
                for (SafeZone safeZone : safeZones) {
                    var distance = safeZone
                            .getLongitude()
                            .subtract(longitude)
                            .multiply(
                                    safeZone
                                            .getLongitude()
                                            .subtract(longitude))
                            .add(
                                    safeZone
                                            .getLatitudes()
                                            .subtract(latitudes)
                                            .multiply(
                                                    safeZone
                                                            .getLatitudes()
                                                            .subtract(latitudes)))
                            .sqrt(new MathContext(10));
                    if (distance.compareTo(minDistance) < 0) {
                        minDistance = distance;
                        minSafeZone = safeZone;
                    }
                }
                response = "https://www.google.com/maps/dir/" + longitude + "," + latitudes + "/" + minSafeZone.getLongitude() + "," + minSafeZone.getLatitudes();
            }
            step = step.add(new BigDecimal("0.5"));
        }

        return response;
    }
}
