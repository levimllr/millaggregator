package com.levimllr.millaggregator.aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levimllr.millaggregator.user_aggregator.UserAggregator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Aggregator {

    private @Id @GeneratedValue Long id;
    private String name;
    private @OneToMany @JsonIgnore List<UserAggregator> userAggregators;

    private @Version @JsonIgnore Long version;

    private Aggregator() {};

    @JsonCreator
    public Aggregator(
            @JsonProperty("name") String name,
            List<UserAggregator> userAggregators) {
        this.name = name;
        this.userAggregators = userAggregators;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public List<UserAggregator> getUserAggregators() {
        return userAggregators;
    }

    public void setUserAggregators(List<UserAggregator> userAggregators) {
        this.userAggregators = userAggregators;
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

    public static class Builder {
        private String name;
        private List<UserAggregator> userAggregators;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUserAggregators(List<UserAggregator> userAggregators) {
            this.userAggregators = userAggregators;
            return this;
        }

        public Aggregator build() {
            return new Aggregator(name, userAggregators);
        }
    }
}
