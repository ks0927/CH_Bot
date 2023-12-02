package CyberHanryang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "emojis")
public class Emoji {
    @Id
    @Column(name = "emoji_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private Server server;

    public static Emoji create(String tag, String name, Server server) {
        Emoji emoji = new Emoji();
        emoji.tag = tag;
        emoji.name = name;
        emoji.server = server;
        return emoji;
    }
}
