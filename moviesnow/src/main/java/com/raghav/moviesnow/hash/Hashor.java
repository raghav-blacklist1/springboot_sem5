package com.raghav.moviesnow.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashor {
    public Hashor() {

    }

    public String gen(String passwordToHash) {
        String generatedPassword = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }
}