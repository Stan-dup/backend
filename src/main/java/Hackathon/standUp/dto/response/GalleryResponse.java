package Hackathon.standUp.dto.response;

import Hackathon.standUp.dto.StoreInfo;
import lombok.Builder;

@Builder
public record GalleryResponse(String postImgUrl, String locationName, String startDate, String endDate,
                              StoreInfo storeInfo) {

    public static GalleryResponse create(String postImgUrl, String locationName, String startDate,
        String endDate, StoreInfo storeInfo) {

        return GalleryResponse.builder()
            .postImgUrl(postImgUrl)
            .locationName(locationName)
            .startDate(startDate)
            .endDate(endDate)
            .startDate(startDate)
            .storeInfo(storeInfo)
            .build();
    }
}
