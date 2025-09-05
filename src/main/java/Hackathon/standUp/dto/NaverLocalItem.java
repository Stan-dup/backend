package Hackathon.standUp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverLocalItem(
    String title,       // 상호명 (<b>태그 포함)
    String link,        // 네이버 검색 상세 링크
    String category,    // 카테고리
    String description, // 가게 설명
    String address,     // 지번주소
    String roadAddress, // 도로명주소
    String mapx,        // 좌표 X
    String mapy         // 좌표 Y
) {

}
