package com.levimllr.millaggregator.user_aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levimllr.millaggregator.aggregator.Aggregator;
import com.levimllr.millaggregator.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserAggregator {

    private @Id @GeneratedValue Long id;
    private @ManyToOne User user;
    private @ManyToOne Aggregator aggregator;
    private String role;


    private UserAggregator() {};

    @JsonCreator
    public UserAggregator(
            @JsonProperty("user") User user,
            @JsonProperty("aggregator") Aggregator aggregator,
            @JsonProperty("role") String role) {
        this.user = user;
        this.aggregator = aggregator;
        this.role = role;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonGetter("user")
    public User getUser() {
        return user;
    }

    @JsonGetter("aggregator")
    public Aggregator getAggregatorId() {
        return aggregator;
    }

    @JsonGetter("role")
    public String getRole() {
        return role;
    }
}
