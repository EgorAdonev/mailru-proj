package ru.adonev.ui.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public Map<Integer, Integer> findGreatestNumberAndItsDigitsSum(int[] in) {
        Map<Integer, Integer> res = new HashMap<>();
        int digitsSum = 0;
        int maxDigitsSum = 0;
        int[] digitsSumArr = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            int curNum = in[i];

            while (curNum > 0) {
                digitsSum += curNum % 10;
                curNum /= 10;
            }

            if (digitsSum > maxDigitsSum) {
                maxDigitsSum = digitsSum;
            }
            digitsSumArr[i] = digitsSum;
            digitsSum = 0;
        }
        int position = findMaxDigitsSumPosition(digitsSumArr);
        res.put(in[position], maxDigitsSum);
        return res;
    }

    public int findMaxDigitsSumPosition(int[] sumOfDigitsArray) {
        int pos = 0;
        int max = 0;
        for (int i = 0; i < sumOfDigitsArray.length; i++) {

            if (sumOfDigitsArray[i] > max) {
                max = sumOfDigitsArray[i];
            }

        }
        for (int i = 0; i < sumOfDigitsArray.length; i++) {
            if (sumOfDigitsArray[i] == max) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    @Test
    void test1() {
        int[] in = {15, 18, 95, 345};
        int[] in1 = {2147483647, 256, 127, Integer.MAX_VALUE};
        int[] in2 = {Integer.MIN_VALUE, 18, 32_767, 65_535};
        System.out.println(findGreatestNumberAndItsDigitsSum(in1));
        System.out.println(findGreatestNumberAndItsDigitsSum(in2));
        System.out.println(findGreatestNumberAndItsDigitsSum(in));
//        Assertions.assertEquals();
    }
}
