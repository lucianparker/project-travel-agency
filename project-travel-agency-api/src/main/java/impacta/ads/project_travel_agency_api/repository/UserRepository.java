package impacta.ads.project_travel_agency_api.repository;

import impacta.ads.project_travel_agency_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}