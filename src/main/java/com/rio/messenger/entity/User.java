package com.rio.messenger.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

    @Id
    private String username;
    private String salt;
    private String hash;


    public User(){}

    private User(String username, String salt ,String hash) {
        this.username = username;
        this.salt = salt;
        this.hash = hash;
    }
    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public static class UserEntityBuilder{

        private String username;
        private String salt;
        private String hash;

        private UserEntityBuilder(){}

        public static UserEntityBuilder getBuilder(){
            return new UserEntityBuilder();
        }

        public UserEntityBuilder setUsername(String username){
            this.username = username;
            return this;
        }

        public UserEntityBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public UserEntityBuilder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public User build(){
            return new User(this.username,this.salt,this.hash);
        }
    }
}
