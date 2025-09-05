package Hackathon.standUp.dto.response;

import lombok.Builder;

@Builder
public record LocationResponse(String locationName) {

    public static LocationResponse create(String locationName) {

        return LocationResponse.builder()
            .locationName(locationName)
            .build();
    }
}
