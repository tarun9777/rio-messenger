package com.rio.messenger.bo;

public class HashedPasscode {

    private final String salt;
    private final String hash;

    private HashedPasscode(String salt,String hash){
        this.salt = salt;
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public String getHash() {
        return hash;
    }

    public static class HashedPasscodeBuilder{
        private String salt;
        private String hash;

        private HashedPasscodeBuilder(){}

        public static HashedPasscodeBuilder getBuilder() {
            return new HashedPasscodeBuilder();
        }

        public HashedPasscodeBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public HashedPasscodeBuilder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public HashedPasscode build(){
            return new HashedPasscode(this.salt,this.hash);
        }
    }
}
