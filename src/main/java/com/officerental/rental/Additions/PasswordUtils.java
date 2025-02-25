package com.officerental.rental.Additions;

import java.security.MessageDigest;

public class PasswordUtils {
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {

        String hashedPlainPassword = hashPassword(plainPassword);

        return hashedPlainPassword.equals(hashedPassword);
    }
}
