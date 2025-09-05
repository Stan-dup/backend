package Hackathon.standUp.dto;

import lombok.Builder;

@Builder
public record StoreInfo(String address, String contents) {

    public static StoreInfo create(String address, String contents) {

        return StoreInfo.builder()
            .address(address)
            .contents(contents)
            .build();
    }
}
