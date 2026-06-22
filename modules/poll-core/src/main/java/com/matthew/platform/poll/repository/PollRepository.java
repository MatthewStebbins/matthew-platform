package com.matthew.platform.poll.repository;

import com.matthew.platform.poll.domain.Poll;

import java.util.Optional;

public interface PollRepository {

    Poll save(Poll poll);

    Optional<Poll> findById(String id);
}
