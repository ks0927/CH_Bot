package CyberHanryang.repository;


import CyberHanryang.entity.Server;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Optional<Server> findByTag(String tag);
}
