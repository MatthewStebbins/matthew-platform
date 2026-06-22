package com.matthew.platform.discord.command;

import com.matthew.platform.poll.service.PollService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DiscordCommandRouter {

    private final PollService pollService;

    public DiscordCommandRouter(PollService pollService) {
        this.pollService = pollService;
    }

    public void handle(MessageReceivedEvent event, String msg) {

        if (msg.equalsIgnoreCase("ping")) {
            event.getChannel().sendMessage("pong").queue();
            return;
        }

        if (msg.equalsIgnoreCase("hello")) {
            event.getChannel().sendMessage("hello from bot").queue();
            return;
        }
    }
}
