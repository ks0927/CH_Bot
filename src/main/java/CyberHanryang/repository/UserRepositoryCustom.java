package CyberHanryang.repository;

import CyberHanryang.entity.User;
import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByTag(String tag);
}
