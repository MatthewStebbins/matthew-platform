package com.matthew.platform.discord.listener;

import com.matthew.platform.discord.command.DiscordCommandRouter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordMessageListener extends ListenerAdapter {

    private final DiscordCommandRouter router;

    public DiscordMessageListener(DiscordCommandRouter router) {
        this.router = router;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String msg = event.getMessage().getContentRaw();

        router.handle(event, msg);
    }
}
