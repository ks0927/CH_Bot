package CyberHanryang.repository;

import CyberHanryang.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTag(String tag);
}
