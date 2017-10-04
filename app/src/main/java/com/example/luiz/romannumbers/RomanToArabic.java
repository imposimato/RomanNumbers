package com.example.luiz.romannumbers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class RomanToArabic {

    private static final Set<String> ROMANS =
            new HashSet<String>(Arrays.asList(new String[] {"I", "V", "X", "L", "C", "D", "M"}));

    private static boolean isValid(String s){
        s = s.toUpperCase();
        int count = 1;

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            if (!(ROMANS.contains(toString(c)))) return false;

            if (i >= 1){
                if (c == s.charAt(i-1)) count++;
                else  count = 1;
                if (count > 3) return false;

                if ((charToInt(c) > charToInt(s.charAt(i-1)))){
                    if ((c == 'M' || c == 'D') && s.charAt(i-1) != 'C') return false;
                    if ((c == 'L' || c == 'C') && s.charAt(i-1) != 'X') return false;
                    if ((c == 'X' || c == 'V') && s.charAt(i-1) != 'I') return false;
                }
            }

            if (i >= 2){
                if (charToInt(c) > charToInt(s.charAt(i-2))) return false;
            }
        }

        return true;
    }

    public static int toArabic(String s) throws NotValidRoman {
        int total = 0;

        if (isValid(s) && s.length() > 0){
            for (int i = 0; i < s.length()-1; i++) {

                if ((charToInt(s.charAt(i+1)) > charToInt(s.charAt(i)))) {
                    total -= charToInt(s.charAt(i));
                } else {
                    total += charToInt(s.charAt(i));
                }
            }
            total += charToInt(s.charAt(s.length()-1));
            return total;
        } else {
            throw new NotValidRoman("Not a valid Roman number!");
        }

    }

    public static int charToInt(char c){
        switch (c){
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    private static String toString(char c) {
        return String.valueOf(c);
    }
}
