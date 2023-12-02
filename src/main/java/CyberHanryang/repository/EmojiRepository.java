package CyberHanryang.repository;

import CyberHanryang.entity.Emoji;
import CyberHanryang.entity.Server;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
    List<Emoji> findByServer(Server server);
}
