package Hackathon.standUp.controller;

import Hackathon.standUp.dto.request.PromotionRequest;
import Hackathon.standUp.dto.response.PromotionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/stand-up")
public class StandUpController {

    public StandUpController() {

    }

    @PostMapping("/proxy")
    public ResponseEntity<PromotionResponse> proxyPromotion(
        @RequestPart(name = "request") PromotionRequest request,
        @RequestPart(name = "img", required = false) MultipartFile multipartFile) {


    }
}
