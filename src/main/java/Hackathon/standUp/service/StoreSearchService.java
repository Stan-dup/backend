package Hackathon.standUp.service;

import Hackathon.standUp.dto.response.KakaoSearchResponse;
import Hackathon.standUp.dto.response.StoreInfoResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StoreSearchService {

    private final WebClient webClient;

    private static final String CHUNCHEON_X = "127.734";
    private static final String CHUNCHEON_Y = "37.881";
    private static final int SEARCH_RADIUS = 10_000;

    public StoreSearchService(@Value("${kakao.api.key}") String kakaoApiKey) {
        this.webClient = WebClient.builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader("Authorization", "KakaoAK " + kakaoApiKey)
            .build();
    }

    public List<StoreInfoResponse> getStoreInfoByKakaoMapApi(String storeName) {
        try {
            KakaoSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/v2/local/search/keyword.json")
                    .queryParam("query", storeName)
                    .queryParam("x", CHUNCHEON_X)
                    .queryParam("y", CHUNCHEON_Y)
                    .queryParam("radius", SEARCH_RADIUS)
                    .build())
                .retrieve()
                .bodyToMono(KakaoSearchResponse.class)
                .block(Duration.ofSeconds(5));

            if (response == null || response.documents() == null || response.documents().isEmpty()) {
                return Collections.emptyList();
            }

            final String target = normalizeName(storeName);

            return response.documents().stream()
                .filter(doc -> {
                    String jibun = doc.addressName();
                    if (jibun == null || !jibun.contains("춘천시")) {
                        return false;
                    }

                    String name = doc.placeName();
                    if (name == null || name.isBlank()) {
                        return false;
                    }

                    String n = normalizeName(name);
                    return n.equals(target) || n.startsWith(target + " ");
                })
                .map(doc -> {
                    String rawJibun = doc.addressName();
                    String trimmed = trimToCityJibun(rawJibun, "춘천시");

                    String phone = (doc.phone() != null && !doc.phone().isBlank())
                        ? doc.phone() : "전화번호 정보 없음";

                    String name = doc.placeName();

                    return StoreInfoResponse.create(trimmed, phone, name);
                })
                .collect(Collectors.toList());

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    private String normalizeName(String s) {
        return s == null ? "" : s.replaceAll("\\s+", " ").trim();
    }

    private String trimToCityJibun(String full, String cityToken) {
        if (full == null || full.isBlank()) {
            return "주소 정보 없음";
        }
        int idx = full.indexOf(cityToken);
        String tail = (idx >= 0) ? full.substring(idx) : full;

        return tail.trim();
    }
}
