package Hackathon.standUp.service;

import Hackathon.standUp.dto.response.LocationResponse;
import Hackathon.standUp.entity.Location;
import Hackathon.standUp.repository.LocationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationResponse> getLocationList() {
        List<Location> locationList = locationRepository.findAll();
        List<LocationResponse> responses = new ArrayList<>();

        for (Location location : locationList) {
            responses.add(LocationResponse.create(location.getLocationName()));
        }

        return responses;
    }
}
