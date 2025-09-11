package Hackathon.standUp.dto.response;

import Hackathon.standUp.dto.TextFeature;
import java.util.List;

public record PromotionResponse(String img, List<TextFeature> textFeature) {

}
