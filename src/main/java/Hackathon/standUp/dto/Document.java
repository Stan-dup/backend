package Hackathon.standUp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Document(
    @JsonProperty("place_name") String placeName,
    @JsonProperty("phone") String phone,
    @JsonProperty("address_name") String addressName,
    @JsonProperty("road_address_name") String roadAddressName,
    @JsonProperty("category_name") String categoryName
) {

}
