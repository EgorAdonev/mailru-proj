//package ru.adonev.ui.login;
//
//import java.util.Arrays;
//
//public class Palindrome {
//    public static void main(String[] args) {
//        String s = "yoyo";
//        System.out.println(isPalindrome(s));
//    }
//    static boolean isPalindrome(String s){
//        boolean isPalindrome = false;
//        s = s.toLowerCase().replace('.', ' ').stripTrailing();
//        char[] chs = s.toCharArray();
//        int l = chs.length;
//        char[] chsNew = new char[l];
//        for(; l > 0;l--){
//            // обработка крайних значений
//            if(l == chs.length) {
//                chsNew[chs.length - 1] = chs[0];
//            }
//            if(l == 0) {
//                chsNew[0] = chs[chs.length - l - 1];
//            }
//            chsNew[l-1] = chs[chs.length - l];
//        }
//        if(Arrays.equals(chs, chsNew)){
//            isPalindrome = true;
//        }
//        System.out.println(Arrays.toString(chsNew) + " " + Arrays.toString(chs));
//        return isPalindrome;
//    }
//}
