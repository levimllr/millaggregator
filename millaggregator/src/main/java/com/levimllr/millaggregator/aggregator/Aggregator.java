package com.levimllr.millaggregator.aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Aggregator {

    private @Id @GeneratedValue Long id;
    private String name;

    private Aggregator() {};

    @JsonCreator
    public Aggregator(
            @JsonProperty("name") String name) {
        this.name = name;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }
}
