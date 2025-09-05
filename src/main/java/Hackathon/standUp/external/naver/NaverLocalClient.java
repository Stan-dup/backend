package Hackathon.standUp.external.naver;

import Hackathon.standUp.dto.response.NaverLocalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NaverLocalClient {

    private final WebClient webClient;

    public NaverLocalClient(
        @Value("${naver.client-id}") String clientId,
        @Value("${naver.client-secret}") String clientSecret
    ) {
        this.webClient = WebClient.builder()
            .baseUrl("https://openapi.naver.com")
            .defaultHeader("X-Naver-Client-Id", clientId)
            .defaultHeader("X-Naver-Client-Secret", clientSecret)
            .build();
    }

    public NaverLocalResponse search(String query, int display) {
        return webClient.get()
            .uri(uri -> uri.path("/v1/search/local.json")
                .queryParam("query", query)
                .queryParam("display", Math.max(1, Math.min(display, 5))) // 최대 5개만 가져오기
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .build())
            .retrieve()
            .bodyToMono(NaverLocalResponse.class)
            .block();
    }
}
