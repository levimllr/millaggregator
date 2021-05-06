package com.levimllr.millaggregator.user_aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAggregator {

    private @Id @GeneratedValue Long id;
    private Long userId;
    private Long aggregatorId;
    private String role;

    private UserAggregator() {};

    @JsonCreator
    public UserAggregator(
            @JsonProperty("user_id") Long userId,
            @JsonProperty("aggregator_id") Long aggregatorId,
            @JsonProperty("role") String role) {
        this.userId = userId;
        this.aggregatorId = aggregatorId;
        this.role = role;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonGetter("user_id")
    public Long getUserId() {
        return userId;
    }

    @JsonGetter("aggregator_id")
    public Long getAggregatorId() {
        return aggregatorId;
    }

    @JsonGetter("role")
    public String getRole() {
        return role;
    }
}
