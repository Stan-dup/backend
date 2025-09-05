package Hackathon.standUp.dto.request;

import Hackathon.standUp.dto.StoreInfo;
import Hackathon.standUp.status.FACILITY_TYPE;
import Hackathon.standUp.status.PURPOSE;

public record PromotionRequest(PURPOSE purpose, FACILITY_TYPE facilityType, String prompt,  String mainColor,
                               String mood, String size, String startDate, String endDate,
                               StoreInfo storeInfo) {

}
