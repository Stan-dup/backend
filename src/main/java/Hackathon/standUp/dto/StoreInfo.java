package Hackathon.standUp.dto;

import lombok.Builder;

@Builder
public record StoreInfo(String address, String phone, String name) {

    public static StoreInfo create(String address, String phone, String name) {

        return StoreInfo.builder()
            .name(name)
            .address(address)
            .phone(phone)
            .build();
    }
}
