package com.matthew.platform.poll.repository;

import com.matthew.platform.poll.domain.Poll;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPollRepository implements PollRepository {

    private final Map<String, Poll> store = new HashMap<>();

    @Override
    public Poll save(Poll poll) {
        store.put(poll.getId(), poll);
        return poll;
    }

    @Override
    public Optional<Poll> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
