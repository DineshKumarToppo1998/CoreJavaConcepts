package com.practice.leetcode.SpecialDiscount;

import java.util.Arrays;

import static java.util.Arrays.stream;

class SpecialDiscountSolution {


    public static void main(String[] args) {
        int[] array1 = {8,4,6,2,3}; // Expected = 4,2,4,2,3
        int[] array2 = {1,2,3,4,5}; // Expected = 1,2,3,4,5
        int[] array3 = {10,1,1,6}; // Expected = 9,0,1,6

        stream(finalPrices(array1)).forEach(System.out::print);
        System.out.println();
        stream(finalPrices(array2)).forEach(System.out::print);
        System.out.println();
        stream(finalPrices(array3)).forEach(System.out::print);

    }
    public static int[] finalPrices(int[] prices) {
        int[] result = new int[prices.length];

        for(int i = 0; i < prices.length; i++){
            for(int j = i+1; j < prices.length; j++){
                if(prices[i] >= prices[j]){
                    result[i] = prices[i] - prices[j];
                    break;
                } else {
                    result[i] = prices[i];
                }
            }
            if(i==prices.length-1){
                result[i] = prices[i];
            }
        }
        return result;

    }
}