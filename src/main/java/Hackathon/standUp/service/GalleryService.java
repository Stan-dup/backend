package Hackathon.standUp.service;

import Hackathon.standUp.dto.StoreInfo;
import Hackathon.standUp.dto.request.CreateGalleryRequest;
import Hackathon.standUp.dto.response.GalleryResponse;
import Hackathon.standUp.entity.Gallery;
import Hackathon.standUp.entity.Location;
import Hackathon.standUp.repository.GalleryRepository;
import Hackathon.standUp.repository.LocationRepository;
import java.util.List;
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
        Location location = locationRepository.findByLocationName(request.locationName());
        String postImgUrl = imageUploadService.uploadProfileImage(multipartFile);

        StoreInfo storeInfo = StoreInfo.create(request.storeInfo().address(),
            request.storeInfo().phone(), request.storeInfo().name());
        Gallery gallery = Gallery.create(location, request.prompt(), request.startDate(),
            request.endDate(), postImgUrl, storeInfo);

        galleryRepository.save(gallery);
    }

    public List<GalleryResponse> getGalleryList(String location, String date, String prompt) {
        return galleryRepository.searchGallery(location, date, prompt)
            .stream()
            .map(gallery -> GalleryResponse.create(gallery.getPostImgUrl(),
                gallery.getLocation().getLocationName(), gallery.getStartDate(), gallery.getEndDate(),
                StoreInfo.create(gallery.getAddress(), gallery.getPhone(), gallery.getName())))
            .toList();
    }
}
