package Hackathon.standUp.controller;

import Hackathon.standUp.dto.request.CreateGalleryRequest;
import Hackathon.standUp.dto.request.PromotionRequest;
import Hackathon.standUp.dto.response.LocationResponse;
import Hackathon.standUp.dto.response.PromotionResponse;
import Hackathon.standUp.service.GalleryService;
import Hackathon.standUp.service.LocationService;
import Hackathon.standUp.service.ProxyService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final LocationService locationService;

    public StandUpController(ProxyService proxyService, GalleryService galleryService,
        LocationService locationService) {
        this.proxyService = proxyService;
        this.galleryService = galleryService;
        this.locationService = locationService;
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

    @GetMapping("/location")
    public ResponseEntity<List<LocationResponse>> getLocationList() {
        List<LocationResponse> responses = locationService.getLocationList();

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
