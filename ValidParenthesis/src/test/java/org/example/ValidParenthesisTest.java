package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisTest {

    private ValidParenthesis validParenthesis;
    @BeforeEach
    void setUp() {validParenthesis = new ValidParenthesis();}

    @Test
    void testValidParenthesis(){
        assertTrue(validParenthesis.isValid("()"));
        assertTrue(validParenthesis.isValid("()"));
        assertTrue(validParenthesis.isValid("[()]"));
        assertTrue(validParenthesis.isValid("{[()]}"));
        assertTrue(validParenthesis.isValid("({[([])]})"));
    }

    @Test
    void testInvalidParenthesis() {
        assertFalse(validParenthesis.isValid("("));
        assertFalse(validParenthesis.isValid("(]"));
        assertFalse(validParenthesis.isValid("{(]("));
        assertFalse(validParenthesis.isValid(")"));
        assertFalse(validParenthesis.isValid(")("));
    }

}