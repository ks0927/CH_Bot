package CyberHanryang.listener;

import CyberHanryang.dto.EmojiStatDto;
import CyberHanryang.entity.Emoji;
import CyberHanryang.entity.Server;
import CyberHanryang.repository.ServerRepository;
import CyberHanryang.service.EmojiService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.emoji.RichCustomEmoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmojiStatusSlashCommand extends ListenerAdapter {
    private static final String FORMAT = "이모지: %s, 사용 횟수: %d회\n";

    private final EmojiService emojiService;
    private final ServerRepository serverRepository;

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("emoji_stat")) {
            event.deferReply().queue();
            Server server = serverRepository.findByTag(event.getGuild().getId()).get();

            List<RichCustomEmoji> emojis = event.getGuild().getEmojis();
            List<Emoji> serverEmojis = emojis.stream()
                    .map(richCustomEmoji -> Emoji.create(richCustomEmoji.getId(), richCustomEmoji.getName(), server))
                    .collect(Collectors.toList());
            List<EmojiStatDto> currentServerEmojiStat = emojiService.getCurrentServerEmojiStat(server, serverEmojis);
            StringBuilder sb = new StringBuilder();

            currentServerEmojiStat.stream()
                    .forEach(dto -> sb.append(String.format(FORMAT, dto.getRealEmojiContent(), dto.getCount())));

            sendMessage(event, sb);
        }
    }

    // 페이징 적용
    private void sendMessage(SlashCommandInteractionEvent event, StringBuilder sb) {
        String message = sb.toString();
        String[] lines = message.split("\n");

        StringBuilder page = new StringBuilder();
        for (String line : lines) {
            // 새로운 라인을 추가했을 때 2000자를 넘으면 이전까지의 내용을 먼저 보냄
            if (page.length() + line.length() > 2000) {
                event.getHook().sendMessage(page.toString()).queue();
                page = new StringBuilder();
            }
            page.append(line).append("\n");
        }

        // 마지막 페이지가 남아있으면 보냄
        if (page.length() > 0) {
            event.getHook().sendMessage(page.toString()).queue();
        }
    }
}
