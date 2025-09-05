package Hackathon.standUp.dto.response;

import Hackathon.standUp.dto.Document;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record KakaoSearchResponse(
    Meta meta,
    List<Document> documents
) {

    public record Meta(
        @JsonProperty("total_count") int totalCount,
        @JsonProperty("pageable_count") int pageableCount,
        @JsonProperty("is_end") boolean isEnd,
        @JsonProperty("same_name") SameName sameName
    ) {

    }

    public record SameName(
        List<String> region,
        String keyword,
        @JsonProperty("selected_region") String selectedRegion
    ) {

    }
}
