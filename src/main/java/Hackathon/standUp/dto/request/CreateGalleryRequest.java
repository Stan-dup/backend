package Hackathon.standUp.dto.request;

import Hackathon.standUp.dto.StoreInfo;

public record CreateGalleryRequest(String prompt, String locationName, String startDate, String endDate,
                                   StoreInfo storeInfo) {

}
