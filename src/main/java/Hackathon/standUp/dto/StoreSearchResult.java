package Hackathon.standUp.dto;

import lombok.Builder;

@Builder
public record StoreSearchResult(
    String name,         // 상호명 (HTML 태그 제거 후)
    String description,  // 가게 설명
    String address,      // 지번주소
    String roadAddress,  // 도로명주소
    String link          // 네이버 상세 링크
) {}
