package com.matthew.platform.poll.service;

import com.matthew.platform.poll.domain.Poll;
import com.matthew.platform.poll.repository.PollRepository;

import java.util.UUID;

public class PollServiceImpl implements PollService {

    private PollRepository repository;

    public PollServiceImpl(PollRepository repository) {
        this.repository = repository;
    }

    @Override
    public Poll createPoll(String name) {
        String id = UUID.randomUUID().toString();

        Poll poll = new Poll(id, name);
        repository.save(poll);

        return poll;
    }

    @Override
    public Poll getPoll(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll with id " + id + " not found"));
    }
}
