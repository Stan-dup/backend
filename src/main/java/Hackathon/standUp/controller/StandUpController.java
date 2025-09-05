package Hackathon.standUp.controller;

import Hackathon.standUp.dto.request.CreateGalleryRequest;
import Hackathon.standUp.dto.request.PromotionRequest;
import Hackathon.standUp.dto.response.PromotionResponse;
import Hackathon.standUp.service.GalleryService;
import Hackathon.standUp.service.ProxyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/stand-up")
public class StandUpController {

    private final ProxyService proxyService;
    private final GalleryService galleryService;

    public StandUpController(ProxyService proxyService, GalleryService galleryService) {
        this.proxyService = proxyService;
        this.galleryService = galleryService;
    }

    @PostMapping("/proxy")
    public ResponseEntity<PromotionResponse> proxyPromotion(
        @RequestPart(name = "request") PromotionRequest request,
        @RequestPart(name = "img", required = false) MultipartFile multipartFile) {
        PromotionResponse response = proxyService.proxyPromotion(request, multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/gallery")
    public ResponseEntity<Void> createGallery(@RequestPart(name = "request") CreateGalleryRequest request,
        @RequestPart(name = "postImg", required = false) MultipartFile multipartFile) {
        galleryService.createGallery(request, multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
