package com.gnanavad.utils.bday_wisher.core;

import java.util.Base64;

public class EncoderDecoder {
    public static String encode(String passwordToBeEncoded) {
        return Base64.getEncoder()
                     .withoutPadding()
                     .encodeToString(passwordToBeEncoded.getBytes());
    }

    public static String decode(String hashToBeDecoded) {
        return new String(Base64.getDecoder()
                                .decode(hashToBeDecoded));
    }
}
