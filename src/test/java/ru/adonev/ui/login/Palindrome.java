package ru.adonev.ui.login;

import java.util.Arrays;

public class Palindrome {
    public static void main(String[] args) {
        String s = "yoyo";
        System.out.println(isPalindrome(s));
    }
    static boolean isPalindrome(String s){
        boolean isPalindrome = false;
        s = s.toLowerCase().replace('.', ' ').stripTrailing();
        char[] chs = s.toCharArray();
        int l = chs.length;
        char[] chsNew = new char[l];
        for(; l > 0;l--){
            // обработка крайних значений
            if(l == chs.length) {
                chsNew[chs.length - 1] = chs[0];
            }
            if(l == 0) {
                chsNew[0] = chs[chs.length - l - 1];
            }
            chsNew[l-1] = chs[chs.length - l];
        }
        if(Arrays.equals(chs, chsNew)){
            isPalindrome = true;
        }
        System.out.println(Arrays.toString(chsNew) + " " + Arrays.toString(chs));
        return isPalindrome;
    }
    class Solution {
        public int romanToInt(String s) {
            char[] roman = s.toCharArray();
            int res = 0;
            for (int i = 0; i <= roman.length - 1; i++) {
                switch (roman[i]) {
                    case 'I':
                        res += 1;
                        break;
                    case 'V':
                        res += 5;
                        break;
                    case 'X':
                        res += 10;
                        break;
                    case 'L':
                        res += 50;
                        break;
                    case 'C':
                        res += 100;
                        break;
                    case 'D':
                        res += 500;
                        break;
                    case 'M':
                        res += 1000;
                        break;
                }
                if (roman[i] == 'I' && (
                        roman[i + 1] == 'V'
                                || roman[i + 1] == 'X')) {
                    res -= 1;
                    // res+=4;
                }
                // if(roman[i]=='I' && roman[i+1]=='X') {
                //     res-=1;
                //     // res+=9;
                // }
                if (roman[i] == 'X' && (
                        roman[i + 1] == 'L'
                                || roman[i + 1] == 'C')) {
                    res -= 10;
                    // res+=40;
                }
                if (roman[i] == 'X' && roman[i + 1] == 'C') {
                    res -= 10;
                    // res+=90;
                }
                if (roman[i] == 'C' && roman[i + 1] == 'D') {
                    res += 400;
                }
                if (roman[i] == 'C' && roman[i + 1] == 'M') {
                    res += 900;
                }
            }
            return res;
        }
    }
}
