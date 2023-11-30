package CyberHanryang.repository;

import CyberHanryang.entity.Server;
import CyberHanryang.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServerRepositoryCustomImpl implements ServerRepositoryCustom {

    private final EntityManager em;

    @Override
    public Optional<Server> findByTag(String tag) {
        List<Server> results = em.createQuery("SELECT s FROM Server s WHERE s.tag = :tag", Server.class)
                .setParameter("tag", tag)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.ofNullable(results.get(0));
    }
}
