package Hackathon.standUp.dto.response;

import Hackathon.standUp.dto.StoreInfo;
import Hackathon.standUp.status.PURPOSE;

public record PromotionResponse(PURPOSE purpose, String mainColor, String subColor, String size,
                                String startDate, String endDate, String img, StoreInfo storeInfo) {

}
