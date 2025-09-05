package Hackathon.standUp.dto.response;

import lombok.Builder;

@Builder
public record LocationListResponse(String locationName) {

    public static LocationListResponse create(String locationName) {

        return LocationListResponse.builder()
            .locationName(locationName)
            .build();
    }
}
