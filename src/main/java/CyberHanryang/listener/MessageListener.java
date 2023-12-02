package CyberHanryang.listener;


import CyberHanryang.entity.Emoji;
import CyberHanryang.entity.Server;
import CyberHanryang.entity.User;
import CyberHanryang.entity.UserMessage;
import CyberHanryang.repository.EmojiRepository;
import CyberHanryang.repository.UserMessageRepository;
import CyberHanryang.repository.ServerRepository;
import CyberHanryang.repository.UserRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener extends ListenerAdapter {
    private final UserMessageRepository userMessageRepository;
    private final UserRepository userRepository;
    private final ServerRepository serverRepository;
    private final EmojiRepository emojiRepository;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //봇이 보낸건 제외
        if (event.getAuthor().isBot()) {
            return;
        }

        User user = getUser(event);

        Server server = getServer(event);

        String content = getMessageContent(event, user, server);

        parsingEmoji(content, server);
    }

    @NotNull
    private User getUser(MessageReceivedEvent event) {
        String userTag = event.getAuthor().getId();
        String userName = event.getAuthor().getGlobalName();
        User user = userRepository.findByTag(userTag).orElseGet(() -> {
            User newUser = User.create(userTag, userName);
            userRepository.save(newUser);
            return newUser;
        });
        return user;
    }

    @NotNull
    private Server getServer(MessageReceivedEvent event) {
        String serverTag = event.getGuild().getId();
        String serverName = event.getGuild().getName();
        Server server = serverRepository.findByTag(serverTag).orElseGet(() -> {
            Server newServer = Server.create(serverTag, serverName);
            serverRepository.save(newServer);
            return newServer;
        });
        return server;
    }

    @NotNull
    private String getMessageContent(MessageReceivedEvent event, User user, Server server) {
        Message message = event.getMessage();
        String content = event.getMessage().getContentRaw();
        UserMessage userMessage = UserMessage.create(message.getId(), content, user, server);
        userMessageRepository.save(userMessage);
        return content;
    }

    private void parsingEmoji(String message,Server server) {
        // 이모지 패턴에 해당하는 정규표현식
        // <:이모지이름:이모지태그> 형식
        String emojiPattern = "<:([^:>]+):([^:>]+)>";

        // 정규표현식 패턴 생성
        Pattern pattern = Pattern.compile(emojiPattern);
        Matcher matcher = pattern.matcher(message);

        // 메시지에서 이모지 찾기
        while (matcher.find()) {
            // 이모지 태그,이름 가져오기
            String emojiTag = matcher.group(2);
            String emojiName = matcher.group(1);
            Emoji emoji = Emoji.create(emojiTag, emojiName, server);

            //저장
            emojiRepository.save(emoji);
        }
    }
}

