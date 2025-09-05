package Hackathon.standUp.entity;

import Hackathon.standUp.dto.StoreInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "GALLERY_TB")
@Getter
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gallery_id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "prompt", nullable = false)
    private String prompt;

    @Column(name = "start_date", nullable = true)
    private String startDate;

    @Column(name = "end_date", nullable = true)
    private String endDate;

    @Column(name = "post_img_url", nullable = false)
    private String postImgUrl;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contents", nullable = false)
    private String contents;

    protected Gallery() {
    }

    @Builder
    private Gallery(Location location, String prompt, String startDate, String endDate, String postImgUrl,
        StoreInfo storeInfo) {
        this.location = location;
        this.prompt = prompt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postImgUrl = postImgUrl;
        this.address = storeInfo.address();
        this.contents = storeInfo.contents();
    }

    public static Gallery create(Location location, String prompt, String startDate, String endDate,
        String postImgUrl, StoreInfo storeInfo) {

        return Gallery.builder()
            .location(location)
            .prompt(prompt)
            .startDate(startDate)
            .endDate(endDate)
            .postImgUrl(postImgUrl)
            .storeInfo(storeInfo)
            .build();
    }
}
