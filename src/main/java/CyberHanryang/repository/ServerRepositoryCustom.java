package CyberHanryang.repository;

import CyberHanryang.entity.Server;
import java.util.Optional;

public interface ServerRepositoryCustom {
    Optional<Server> findByTag(String tag);
}
