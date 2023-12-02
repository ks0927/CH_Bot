package CyberHanryang.config;

import CyberHanryang.listener.EmojiStatusSlashCommand;
import CyberHanryang.listener.MessageListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotConfig {
    private final MessageListener messageListener;
    private final EmojiStatusSlashCommand emojiStatusSlashCommand;

    private String token = "MTE3NzE2NDAyOTAzOTQ4OTE1OA.Gr4PDm.-2OASpDQtTslteEX5VQpgDIv7jkcSpmcheG8KM";

    public JDA setup() throws InterruptedException {
        return JDABuilder.createDefault(token)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.DIRECT_MESSAGE_REACTIONS)
                .addEventListeners(messageListener, emojiStatusSlashCommand, new SlashCommandConfig())
                .build()
                .awaitReady();
    }
}
