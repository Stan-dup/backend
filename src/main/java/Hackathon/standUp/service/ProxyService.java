package Hackathon.standUp.service;

import Hackathon.standUp.dto.request.PromotionRequest;
import Hackathon.standUp.dto.response.PromotionResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProxyService {

    private final RestTemplate restTemplate;

    public ProxyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public PromotionResponse proxyPromotion(PromotionRequest request, MultipartFile multipartFile) {
        try{
            String base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> body = new HashMap<>();

            body = pushBody(body, request, base64Image);
            HttpEntity<Map<String, String>> httpRequest = new HttpEntity<>(body, headers);
            String externalApi = "tempUrl"; // <- ai서버 api 주소

            ResponseEntity<PromotionResponse> response =
                restTemplate.postForEntity(externalApi, httpRequest, PromotionResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            throw new RuntimeException("외부 서버 정상 응답이지만 Body 가 비었습니다. status=" + response.getStatusCode());

        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            String msg = String.format("외부 서버 호출 실패: status=%s, body=%s",
                e.getStatusCode(), e.getResponseBodyAsString());
            throw new RuntimeException(msg, e);

        } catch (IOException e) {
            throw new RuntimeException("이미지 파일을 읽는 데 실패했습니다.", e);

        }
    }

    private Map<String, String> pushBody(Map<String, String> body, PromotionRequest request, String base64Image) {
        body.put("image_encoding", base64Image);
        body.put("purpose", request.purpose().name());
        body.put("mainColor", request.subColor());
        body.put("size", request.size());
        body.put("startDate", request.startDate());
        body.put("endDate", request.endDate());
        body.put("facilityType", request.facilityType().name());

        return body;
    }
}
