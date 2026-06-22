package com.matthew.platform.poll.service;

import com.matthew.platform.poll.domain.Poll;

public interface PollService {

    Poll createPoll(String name);

    Poll getPoll(String id);
}
