package Hackathon.standUp.repository;

import Hackathon.standUp.entity.Gallery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    @Query("""
        SELECT g FROM Gallery g
        WHERE (:location IS NULL OR g.location.locationName = :location)
        AND (:date IS NULL OR g.startDate = :date)
        AND (:prompt IS NULL OR g.prompt LIKE %:prompt%)""")
    List<Gallery> searchGallery(@Param("location") String location, @Param("date") String date,
        @Param("prompt") String prompt);
}
