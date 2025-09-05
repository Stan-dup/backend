package Hackathon.standUp.dto.response;

import lombok.Builder;

@Builder
public record StoreInfoResponse(String address, String phone, String name) {

    public static StoreInfoResponse create(String address, String phone, String name) {

        return StoreInfoResponse.builder()
            .address(address)
            .phone(phone)
            .name(name)
            .build();
    }
}
