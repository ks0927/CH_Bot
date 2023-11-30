package CyberHanryang.repository;

import CyberHanryang.entity.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
}
