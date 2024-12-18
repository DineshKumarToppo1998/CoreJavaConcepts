package com.practice.leetcode.validParentheses;

import java.util.List;
import java.util.Stack;

import static com.practice.leetcode.Tester.isMatchingPair;

public class ValidParentheses2 {
    public static void main(String[] args) {
        System.out.println(isValid("({[awd]})"));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char char1, char char2) {
        return (char1 == '(' && char2 == ')') ||
                (char1 == '{' && char2 == '}') ||
                (char1 == '[' && char2 == ']');
    }
}
