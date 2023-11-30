package CyberHanryang.repository;

import CyberHanryang.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final EntityManager em;

    @Override
    public Optional<User> findByTag(String tag) {
        List<User> results = em.createQuery("SELECT u FROM User u WHERE u.tag = :tag", User.class)
                .setParameter("tag", tag)
                .getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.ofNullable(results.get(0));
    }
}
