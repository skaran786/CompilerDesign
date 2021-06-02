/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.demo;

import java.util.regex.Pattern;

/**
 *
 * @author manas
 */
public class Demo {
    
    final static Pattern LETTER = Pattern.compile("[a-zA-Z]");
    final static Pattern LETTERS = Pattern.compile(LETTER+"+");
    
    
    
    final static Pattern DIGIT = Pattern.compile("[0-9]");
    //1, 10, 100, 1001, X==> 001,00,01
    final static Pattern DIGITS = Pattern.compile("[1-9]"+DIGIT+"*");
    
    
    final static Pattern VARIABLE = Pattern.compile(LETTER+"[a-zA-Z0-9]*");
    
    final static String OPENPAR = "(";
    final static String CLOSEPAR = ")";
    final static String CURLYOPEN = "{";
    final static String CURLYCLOSE = "}";
    final static String EQUAL_OP = "=";
    final static String PLUS_OP = "+";
    final static String END = ";";
    
    final static Pattern WHITESPACE =Pattern.compile("\\s+");
    final static String TYPE_INT ="int";
    
    static void checker() {
        //BASE RULES AND CHECKERS
        //Letter must be a single character only English Alphabet
        //Letters must be one or more occurance of the Letter

        //returns false
//        System.out.println(LETTER.matcher("aaa").matches());
//        //returns true
//        System.out.println(LETTER.matcher("a").matches());
        //returns false
//        System.out.println(LETTERS.matcher("0ab").matches());
//        //returns true
//        System.out.println(LETTERS.matcher("a").matches());
//        //returns true
//        System.out.println(LETTERS.matcher("aZB").matches());

        //Digit must any single DIGIT from 0 to 9
        //Digits must be one or more occurance of the DIGIT
        //but should start from >0, so 09 not allowed
        //returns false
//        System.out.println(DIGIT.matcher("09").matches());
//        //returns true
//        System.out.println(DIGIT.matcher("9").matches());
//        //returns false
//        System.out.println(DIGITS.matcher("09").matches());
//        //returns true
//        System.out.println(DIGITS.matcher("22").matches());
//        
//        //returns true
//        System.out.println(DIGITS.matcher("224543453").matches());
        
        
//
//        //VARIABLE must start with letter and then digit or letter may follow
//        //returns true
//        System.out.println(VARIABLE.matcher("a22").matches());
//        //returns true
//        System.out.println(VARIABLE.matcher("ab22").matches());
//        //returns true
//        System.out.println(VARIABLE.matcher("ab22ad").matches());
//        //returns false
//        System.out.println(VARIABLE.matcher("0av22").matches());
//
//        //WHITESPACE must be on or more empty spaces.

        //returns FALSE
        System.out.println(WHITESPACE.matcher("").matches());
//        //returns true
        System.out.println(WHITESPACE.matcher(" ").matches());
        //returns true
        System.out.println(WHITESPACE.matcher("  ").matches());

    }
    
    
        public static void main(String[] args) {
        
            checker();
        
        }
    
    
}
