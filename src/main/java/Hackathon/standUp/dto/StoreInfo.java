package Hackathon.standUp.dto;

import lombok.Builder;

@Builder
public record StoreInfo(String phone, String address, String contents) {

    public static StoreInfo create(String phone, String address, String contents) {

        return StoreInfo.builder()
            .phone(phone)
            .address(address)
            .contents(contents)
            .build();
    }
}
