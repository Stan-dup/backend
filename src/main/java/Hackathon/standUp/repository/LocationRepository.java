package Hackathon.standUp.repository;

import Hackathon.standUp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByLocationName(String locationName);
}
