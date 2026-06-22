package com.matthew.platform.discord.listener;

import com.matthew.platform.poll.domain.Poll;
import com.matthew.platform.poll.service.PollService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class SlashCommandListener extends ListenerAdapter {

    private final PollService pollService;

    public SlashCommandListener(PollService pollService) {
        this.pollService = pollService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

       switch (event.getName()) {

           case "ping" -> {
               event.reply("pong").queue();
           }

           case "create-poll" -> {
               String name = Objects.requireNonNull(event.getOption("name")).getAsString();

               Poll poll = pollService.createPoll(name);

               event.reply(
                       "poll created!" +
                               "\nID: " + poll.getId() +
                               "\nName: " + poll.getName() +
                               "\nStatus: " + poll.getStatus())
                       .setEphemeral(true)
                       .queue();
           }
       }

    }
}
