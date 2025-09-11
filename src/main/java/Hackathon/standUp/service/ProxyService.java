package Hackathon.standUp.service;

import Hackathon.standUp.dto.request.PromotionRequest;
import Hackathon.standUp.dto.response.PromotionResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            Map<String, Object> body = new HashMap<>();
            pushBody(body, request, base64Image);
            HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(body, headers);

            String externalApi = "https://mumbai-employer-served-updating.trycloudflare.com/generate-poster";

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

    private void pushBody(Map<String, Object> body, PromotionRequest request, String base64Image) {
        body.put("img", base64Image);
        body.put("purpose", request.purpose());
        body.put("facilityType", request.facilityType());
        body.put("prompt", request.prompt());
        body.put("mainColor", request.mainColor());
        body.put("mood", request.mood());
        body.put("size", request.size());
        body.put("startDate", request.startDate());
        body.put("endDate", request.endDate());

        Map<String, Object> storeInfo = new HashMap<>();
        storeInfo.put("address", request.storeInfo().address());
        storeInfo.put("phone",request.storeInfo().phone());
        storeInfo.put("name",request.storeInfo().name());
        body.put("storeInfo", storeInfo);
    }
}
