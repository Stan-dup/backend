package Hackathon.standUp.dto.response;

import Hackathon.standUp.status.PURPOSE;

public record PromotionResponse(PURPOSE purpose, String mainColor, String SubColor, String size,
                                String startDate, String endDate, String img) {

}
