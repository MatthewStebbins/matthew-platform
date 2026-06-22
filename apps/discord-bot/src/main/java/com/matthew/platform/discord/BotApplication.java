package com.matthew.platform.discord;

import com.matthew.platform.discord.command.DiscordCommandRouter;
import com.matthew.platform.discord.listener.DiscordMessageListener;
import com.matthew.platform.discord.listener.SlashCommandListener;
import com.matthew.platform.poll.repository.InMemoryPollRepository;
import com.matthew.platform.poll.repository.PollRepository;
import com.matthew.platform.poll.service.PollService;
import com.matthew.platform.poll.service.PollServiceImpl;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotApplication {

    public static void main(String[] args) throws Exception {

        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            System.err.println("Environment variable DISCORD_TOKEN has not been set");
            return;
        }

        PollRepository repo = new InMemoryPollRepository();
        PollService service = new PollServiceImpl(repo);

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(new SlashCommandListener(service))
                .build();

        jda.awaitReady();

        System.out.println("Logged in as: " + jda.getSelfUser().getAsTag());
        System.out.println("Bot is online!");
        jda.getGuilds().forEach(guild ->
                System.out.println("Connected to: " + guild.getName())
        );

        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Test the bot"),
                        Commands.slash("create-poll", "Create new poll")
                                .addOption(OptionType.STRING, "name", "Poll name", true)
                )
                .queue();

        jda.retrieveCommands().queue(commands -> {
            System.out.println("=== Registered Slash Commands ===");

            for (Command command : commands) {
                System.out.println("/" + command.getName() + " - " + command.getDescription());
            }
        });
    }
}
