package CyberHanryang.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_messages")
public class UserMessage {
    @Id
    @Column(name = "user_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    public static UserMessage create(String tag, String content, User user, Server server) {
        UserMessage userMessage = new UserMessage();
        userMessage.tag = tag;
        userMessage.content = content;
        userMessage.user = user;
        userMessage.server = server;
        return userMessage;
    }
}
