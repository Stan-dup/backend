package Hackathon.standUp.service;

import Hackathon.standUp.dto.request.CreateGalleryRequest;
import Hackathon.standUp.entity.Gallery;
import Hackathon.standUp.entity.Location;
import Hackathon.standUp.repository.GalleryRepository;
import Hackathon.standUp.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GalleryService {

    private final LocationRepository locationRepository;
    private final GalleryRepository galleryRepository;
    private final ImageUploadService imageUploadService;

    public GalleryService(LocationRepository locationRepository, GalleryRepository galleryRepository,
        ImageUploadService imageUploadService) {
        this.locationRepository = locationRepository;
        this.galleryRepository = galleryRepository;
        this.imageUploadService = imageUploadService;
    }

    public void createGallery(CreateGalleryRequest request, MultipartFile multipartFile) {
        Location location = locationRepository.findByLocationName(request.location());
        String postImgUrl = imageUploadService.uploadProfileImage(multipartFile);
        Gallery gallery = Gallery.create(location, request.prompt(), request.startDate(),
            request.endDate(), postImgUrl);

        galleryRepository.save(gallery);
    }
}
