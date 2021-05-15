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

    public void setUser(User user) {
        this.user = user;
    }

    @JsonGetter("aggregator")
    public Aggregator getAggregatorId() {
        return aggregator;
    }

    @JsonGetter("roles")
    public String getRole() {
        return role;
    }

    public static class Builder {
        private User user;
        private Aggregator aggregator;
        private String role;

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withAggregator(Aggregator aggregator) {
            this.aggregator = aggregator;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserAggregator build() {
            return new UserAggregator(user, aggregator, role);
        }
    }
}
