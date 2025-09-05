package Hackathon.standUp.dto.response;

import Hackathon.standUp.dto.NaverLocalItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverLocalResponse(List<NaverLocalItem> items) {

}

