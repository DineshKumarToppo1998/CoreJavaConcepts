package com.practice.leetcode.twosum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3, 3};
        int target = 6;
        long startTime = 0, endTime = 0;

        startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(twoSum(nums, target)) + " In " + (System.currentTimeMillis()-startTime));

        startTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(twoSum2(nums,target)) + " In " + (System.currentTimeMillis()-startTime));

    }

    public static int[] twoSum(int[] nums, int target) {
        int[] resultIndex = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    resultIndex[0] = i;
                    resultIndex[1] = j;
                    return resultIndex;
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSum2(int[] nums, int target) {
        {
            // Create a HashMap to store the indices of elements
            Map<Integer, Integer> numIndices = new HashMap<>();

            // Iterate through the array
            for (int i = 0; i < nums.length; i++) {
                // Calculate the difference needed to reach the target
                int difference = target - nums[i];
                // Check if the difference is in the HashMap
                if (numIndices.containsKey(difference)) {
                    // Return the indices of the current element and its difference
                    return new int[]{numIndices.get(difference), i};
                }
                // Store the index of the current element in the HashMap
                numIndices.put(nums[i], i);
            }
            // If no solution is found, return an empty array
            return new int[0];
        }
    }

    //Best Time Complexity
    public static int[] twoSum3(int[] nums, int target) {
        int[] resultIndex = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    resultIndex[0] = i;
                    resultIndex[1] = j;
                    return resultIndex;
                }
            }
        }
        return new int[]{};
    }
}
