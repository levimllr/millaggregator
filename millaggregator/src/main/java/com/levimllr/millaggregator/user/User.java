package com.levimllr.millaggregator.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String authProvider;
    private @JsonIgnore String password;

    private User() {};

    @JsonCreator
    public User(
            @JsonProperty ("name") String name,
            @JsonProperty ("email") String email,
            @JsonProperty ("auth_provider") String authProvider,
            String password) {
        this.name = name;
        this.email = email;
        this.authProvider = authProvider;
        this.password = password;
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
}