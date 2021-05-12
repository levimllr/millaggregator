package com.levimllr.millaggregator.aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

@Entity
public class Aggregator {

    private @Id @GeneratedValue Long id;
    private String name;

    private @Version @JsonIgnore Long version;

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

    public Long getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Aggregator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                '}';
    }
}
