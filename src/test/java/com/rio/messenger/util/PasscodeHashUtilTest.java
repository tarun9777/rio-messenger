package com.rio.messenger.util;

import com.rio.messenger.bo.HashedPasscode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.security.NoSuchAlgorithmException;

public class PasscodeHashUtilTest {

    PasscodeHashUtil hashUtil;

    @BeforeEach
    public void init(){
        hashUtil = new PasscodeHashUtil();
    }

    @Test
    void PasscodeHashUtilTest_1() throws NoSuchAlgorithmException {
        String passcode = "abcde1234";
        HashedPasscode hash = hashUtil.getPasscodeHash(passcode);
        Assertions.assertNotNull(hash.getHash());
        Assertions.assertNotNull(hash.getSalt());
    }




}
