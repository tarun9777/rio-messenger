package com.rio.messenger.util;


import static com.rio.messenger.bo.HashedPasscode.HashedPasscodeBuilder;

import com.rio.messenger.bo.HashedPasscode;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class PasscodeHashUtil {

    public HashedPasscode getPasscodeHash(String passcode) throws NoSuchAlgorithmException {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        byte[] hash = md.digest(passcode.getBytes(StandardCharsets.UTF_8));
        return HashedPasscodeBuilder.getBuilder()
                .setHash(encodeToBase64(hash))
                .setSalt(encodeToBase64(salt))
                .build();
    }

    private String encodeToBase64(byte[] arr){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(arr);
    }
}
