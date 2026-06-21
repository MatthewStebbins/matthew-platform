package com.matthew.platform.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotApplication {

    public static void main(String[] args) throws Exception {

        String token = System.getenv("DISCORD_TOKEN");

        if (token == null) {
            System.err.println("Environment variable DISCORD_TOKEN has not been set");
            return;
        }

        JDA jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new ListenerAdapter() {

                    @Override
                    public void onMessageReceived(MessageReceivedEvent event) {

                        if (event.getAuthor().isBot()) return;

                        String msg = event.getMessage().getContentRaw();

                        if (msg.equalsIgnoreCase("ping")) {
                            event.getChannel().sendMessage("pong").queue();
                        }

                        if (msg.equalsIgnoreCase("hello")) {
                            event.getChannel().sendMessage("hello from the voting bot").queue();
                        }
                    }
                })
                .build();

        jda.awaitReady();
        System.out.println("Logged in as: " + jda.getSelfUser().getAsTag());
        System.out.println("Bot is online!");
        jda.getGuilds().forEach(guild ->
                System.out.println("Connected to: " + guild.getName())
        );
    }
}
