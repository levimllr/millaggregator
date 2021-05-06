package com.levimllr.millaggregator.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String authProvider;
    private String userAuthCredentialId;

    private User() {};

    @JsonCreator
    public User(
            @JsonProperty ("name") String name,
            @JsonProperty ("email") String email,
            @JsonProperty ("auth_provider") String authProvider,
            @JsonProperty ("user_auth_credential_id") String userAuthCredentialId) {
        this.name = name;
        this.email = email;
        this.authProvider = authProvider;
        this.userAuthCredentialId = userAuthCredentialId;
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

    @JsonGetter("user_auth_credential_id")
    public String getUserAuthCredentialId() {
        return userAuthCredentialId;
    }
}