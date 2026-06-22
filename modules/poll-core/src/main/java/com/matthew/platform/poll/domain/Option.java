package com.matthew.platform.poll.domain;

public class Option {

    private String id;
    private String label;

    public Option(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
