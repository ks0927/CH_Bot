package CyberHanryang.config;

import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

public class SlashCommandConfig extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("emoji_stat", "서버 커스텀 이모지 통계를 확인해보세요.").setGuildOnly(true));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
