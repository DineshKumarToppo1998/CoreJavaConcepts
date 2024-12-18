package com.practice.leetcode;

import java.util.Stack;

public class Tester {
    public static void main(String[] args) {
        String str = "({[awd]})";
        String str2 = "(]";
        System.out.println(isValid(str));
        System.out.println(isValid1(str));
        System.out.println("_______________________");
        System.out.println(isValid(str2));
        System.out.println(isValid1(str2));
    }
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (!stack.isEmpty()) {
                char topElement = stack.pop();
                if ((c == ')' && topElement != '(') ||
                        (c == '}' && topElement != '{') ||
                        (c == ']' && topElement != '[')) {
                    return false;
                }
            } else {
                return false; // Unmatched closing bracket
            }
        }

        return stack.isEmpty();
    }

//    public static boolean isValid1(String s) {
//        Stack<Character> stack = new Stack<>();
//
//        for (char c : s.toCharArray()) {
//            if (c == '(' || c == '{' || c == '[') {
//                stack.push(c);
//            } else if (c == ')' || c == '}' || c == ']') {
//                if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
//                    return false;
//                }
//            }
//        }
//
//        return stack.isEmpty();
//    }
//
//    private static boolean isMatchingPair(char char1, char char2) {
//        return (char1 == '(' && char2 == ')') ||
//                (char1 == '{' && char2 == '}') ||
//                (char1 == '[' && char2 == ']');
//    }

    public static boolean isValid1(String s) {
        Stack<Character> st = new Stack();
        for(char ch : s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else if(ch == ')' || ch == '}' || ch == ']'){
                if(st.isEmpty() || !isMatchingPair(st.pop(), ch));
            }
        }

        return st.isEmpty();
    }

    public static boolean isMatchingPair(char a, char b){
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }
}
