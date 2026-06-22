package com.matthew.platform.poll.domain;

import java.util.ArrayList;
import java.util.List;

public class Poll {

    private final String id;
    private final String name;
    private PollStatus status;
    private final List<Option> options = new ArrayList<>();

    public Poll(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = PollStatus.OPEN;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PollStatus getStatus() {
        return status;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setStatus(PollStatus status) {
        this.status = status;
    }
}
