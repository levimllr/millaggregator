package com.levimllr.millaggregator.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levimllr.millaggregator.user_aggregator.UserAggregator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String email;
    private String authProvider;
    private @JsonIgnore String password;
    private @OneToMany List<UserAggregator> userAggregators;
    private String role;

    private User() {
    }

    @JsonCreator
    public User(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("auth_provider") String authProvider,
            String password,
            @JsonProperty("user_aggregators") List<UserAggregator> userAggregators,
            @JsonProperty("roles") String role) {
        this.name = name;
        this.email = email;
        this.authProvider = authProvider;
        this.password = password;
        this.userAggregators = userAggregators;
        this.role = role;
    }

    @JsonGetter("id")
    public Long getId() {
        return id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    @JsonGetter("auth_provider")
    public String getAuthProvider() {
        return authProvider;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    @JsonGetter("user_aggregators")
    public List<UserAggregator> getUserAggregators() {
        return userAggregators;
    }

    @JsonGetter("roles")
    public String getRole() {
        return role;
    }

    public static class Builder {

        private String name;
        private String email;
        private String authProvider;
        private String password;
        private List<UserAggregator> userAggregators;
        private String role;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withAuthProvider (String authProvider) {
            this.authProvider = authProvider;
            return this;
        }

        public Builder withPassword (String password) {
            this.password = PASSWORD_ENCODER.encode(password);;
            return this;
        }

        public Builder withUserAggregators (List<UserAggregator> userAggregators) {
            this.userAggregators = userAggregators;
            return this;
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(name, email, authProvider, password, userAggregators, role);
        }
    }
}