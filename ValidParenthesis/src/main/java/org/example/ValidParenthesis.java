package org.example;

import java.util.*;

public class ValidParenthesis {
        // check if provided string is valid
        public static boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            for(char c : s.toCharArray()) {
                //for each openin bracket, a closing bracket is expected
                if(c=='(') {
                    stack.push(')');
                } else if (c=='{') {
                    stack.push('}');
                }else if (c=='[') {
                    stack.push(']');
                // return false if the stack is empty or incorrect characters are used
                } else if (stack.isEmpty() || stack.pop() != c)
                    return false;
            }
        // once completed, return stack is empty
        return stack.isEmpty();
        }
}
