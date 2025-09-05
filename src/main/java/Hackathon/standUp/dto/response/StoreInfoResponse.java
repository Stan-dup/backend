package Hackathon.standUp.dto.response;

import lombok.Builder;

@Builder
public record StoreInfoResponse(String address, String contents) {

    public static StoreInfoResponse create(String address, String contents) {

        return StoreInfoResponse.builder()
            .address(address)
            .contents(contents)
            .build();
    }
}
