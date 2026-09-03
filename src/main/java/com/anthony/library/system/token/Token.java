package com.anthony.library.system.token;

import com.anthony.library.system.user.User;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = UUID)
    private String id;
    @Column(unique = true, nullable = false)
    public String token;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TokenType tokenType;
    @Column(nullable = false)
    public Boolean revoked;
    @Column(nullable = false)
    public Boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public User user;

    protected Token() {}

    private Token(Builder builder) {
        super();
        this.token = builder.token;
        this.tokenType = builder.tokenType;
        this.revoked = builder.revoked;
        this.expired = builder.expired;
        this.user = builder.user;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String token;
        private TokenType tokenType;
        private Boolean revoked;
        private Boolean expired;
        private User user;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder tokenType(TokenType tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder revoked(Boolean revoked) {
            this.revoked = revoked;
            return this;
        }

        public Builder expired(Boolean expired) {
            this.expired = expired;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public Boolean getExpired() {
        return expired;
    }

    public User getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
