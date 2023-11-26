package com.rio.messenger.util;


import static com.rio.messenger.bo.HashedPasscode.HashedPasscodeBuilder;

import com.rio.messenger.bo.HashedPasscode;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.entity.User;
import com.rio.messenger.exception.UserException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class PasscodeHashUtil {


    /**
     * This method will generate the hash and salt for the raw text passcode.
     * @param passcode
     * @return
     * @throws NoSuchAlgorithmException
     */
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

    private byte[] decodeToByteArray(String val){
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(val.getBytes(StandardCharsets.UTF_8));

    }

    /**
     * This method will validate the user input passcode with passcode present in db
     * @param user
     * @param userBO
     * @throws NoSuchAlgorithmException
     */
    public void validatePasscode(User user, UserBO userBO) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(decodeToByteArray(user.getSalt()));
        byte[] hash = md.digest(userBO.getPasscode().getBytes(StandardCharsets.UTF_8));
        if (Arrays.compare(decodeToByteArray(user.getHash()),hash) != 0){
            throw new UserException("Incorrect password");
        }
    }
}
