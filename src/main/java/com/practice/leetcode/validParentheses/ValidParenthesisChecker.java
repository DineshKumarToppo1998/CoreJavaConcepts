package com.practice.leetcode.validParentheses;

import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesisChecker {


    public static boolean isValid(String s) {
        Stack<Character> st = new Stack();
        for (char ch : s.toCharArray()) {
            // Pushing all the Opening brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);

            // Popping the counterparts if exists
            } else if ((!st.isEmpty() && ch == ')' && st.peek() == '(')
                    || (!st.isEmpty() && ch == '}' && st.peek() == '{')
                    || (!st.isEmpty() && ch == ']' && st.peek() == '[')) {
                st.pop();
            } else if(!(ch == ']' || ch == ')' || ch == '}')){
                continue;
            } else return false;
        }

        return st.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the String for Parenthesis check: ");
        String testString = scanner.next();
        if (isValid(testString)) {
            System.out.println("The string has valid parentheses.");
        } else {
            System.out.println("The string has invalid parentheses.");
        }
    }
}