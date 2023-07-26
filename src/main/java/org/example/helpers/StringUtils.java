package org.example.helpers;

import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {
        String BASELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String BASENUMBERS = "1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int indexNumbers = (int) (rnd.nextFloat() * BASENUMBERS.length());
            result.append(BASENUMBERS.charAt(indexNumbers));
            int indexLetters = (int) (rnd.nextFloat() * BASELETTERS.length());
            result.append(BASELETTERS.charAt(indexLetters));
        }
        return result.toString();
    }
}
