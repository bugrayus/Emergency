package vodafone.hackathon.emergency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vodafone.hackathon.emergency.core.model.ApiResponse;
import vodafone.hackathon.emergency.model.request.CreateSafeZoneRequestModel;
import vodafone.hackathon.emergency.model.request.UpdateSafeZoneRequestModel;
import vodafone.hackathon.emergency.model.response.SafeZoneResponseModel;
import vodafone.hackathon.emergency.service.SafeZoneService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/safe-zones")
public class SafeZoneController {
    private final SafeZoneService safeZoneService;

    @PostMapping
    public ResponseEntity<ApiResponse<Boolean>> createEmergency(@RequestBody @Valid CreateSafeZoneRequestModel createSafeZoneRequestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.createSafeZone(createSafeZoneRequestModel)
        ));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<SafeZoneResponseModel>> findEmergencyById(@PathVariable int id) {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.findSafeZoneById(id)
        ));
    }

    @GetMapping("/{longitude}-{latitudes}")
    public ResponseEntity<ApiResponse<String>> findShortestSafeZone(@PathVariable BigDecimal longitude, @PathVariable BigDecimal latitudes) {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.findShortestSafeZone(longitude, latitudes)
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SafeZoneResponseModel>>> findEmergencies() {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.findSafeZones()
        ));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Boolean>> updateEmergency(@RequestBody UpdateSafeZoneRequestModel requestModel) {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.updateSafeZone(requestModel)
        ));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteEmergency(@PathVariable long id) {
        return ResponseEntity.ok(ApiResponse.of(
                safeZoneService.deleteSafeZone(id)
        ));
    }
}
