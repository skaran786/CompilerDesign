/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 *
 * @author manasvi
 */
/*

    Grammar for the language... "Check what can be parsed"

    BODY -> DECL_STMT ASSIGN_STMT           
    DECL_STMT -> TYPE WHITESPACE VAR_NAME VARLIST
    ASSIGN_STMT -> VAR_NAME EQUAL_OP EXPR END
    ASSIGN_STMT -> END
    VARLIST -> null
    VARLIST -> COMMA VAR_NAME VARLIST END          
    EXPR -> VAR_NAME
    EXPR -> VAR_NUM            
    EXPR -> VAR_NAME OP EXPR
    EXPR -> VAR_NUM OP EXPR
    
    COMMA -> ,
    WHITESPACE -> " "
    EQUAL_OP -> =
    END -> ;
    OP -> +
    OP -> -            
    TYPE -> int
    VAR_NAME ->[Letter Letters or Digits]
    VAR_NUM -> [Digit or Digits]
  
*/ 

public class GrammarOnly {
    final static Pattern LETTER = Pattern.compile("[a-zA-Z]");
    final static Pattern LETTERS = Pattern.compile(LETTER+"+");
    final static Pattern DIGIT = Pattern.compile("[0-9]");
    final static Pattern DIGITS = Pattern.compile("[1-9]"+DIGIT+"*");
    final static Pattern VARIABLE = Pattern.compile(LETTER+"[a-zA-Z0-9]*");
    
    final static String EQUAL_OP = "=";
    final static String COMMA = ",";
    final static String PLUS_OP = "+";
    final static String MINUS_OP = "-";
    final static String END = ";";
    
    
    final static Pattern WHITESPACE =Pattern.compile("\\s+");
    final static String TYPE_INT ="int";
    
    static String token; 
    static ArrayList<String> tokenList = new ArrayList<String>();
    static boolean flagAssign = false;
    static boolean flag = false;
    static boolean exprflag = false;
    static int currentTokenPointer = -1;
    
    static void print(String str){
        System.out.println(str);
    }
    
    static void prevToken(){
        decrementPointer();
        token = tokenList.get(currentTokenPointer);
        print(token);
        
    }
    
    static void nextToken(){
        incrementPointer();
        token = tokenList.get(currentTokenPointer);
        print(token);
        
    }
    static void decrementPointer(){
        currentTokenPointer--;
    }
    static void incrementPointer(){
        currentTokenPointer++;
    }

    
    static boolean startParsing(){
        return body();
    }
    
    static boolean body(){
//        print("Main Body Function");
        if (decl_stmt()){
            if(assign_stmt()){
                return true;
            }
        }
        return false;    
    }
    
    static boolean decl_stmt(){
//        print("Declaration Function");
//        nextToken();
        if(type()){
//            nextToken();
            if(whitespace()){
//                nextToken();
                if(var_name()){
//                    nextToken();
                    if(varlist()){
                        flag = true;
                        return true;
                    }
                    
                    else{
                        
                        return true;
                    }
                    
                }
            }
        }
        return false;
    }
    static boolean assign_stmt(){
//        print("Assignment Function");
        if(flag){
            return true;
        }
        
        if(end()){
            return true;
        }
//        print(flag+"---");
        
        prevToken();
        if(var_name()){           
//            nextToken();
            if(equal()){               
//                nextToken();
                if(expr()){
//                    nextToken();
                    if(end()){
                        return true;
                    }                    
                }
            }
        }
        return false;
    }
    
    static boolean type(){
        print("Var Type Function");
        if(token.equals(TYPE_INT)){ 
            return true;
        }
        return false;    
    }
    
    static boolean var_name(){
        print("Var Name Function");
        if(VARIABLE.matcher(token).matches()){
            return true;
        }
        
        return false;
    }
    static boolean var_num(){
//        print("Var Number Function");
        
        if(DIGITS.matcher(token).matches() || DIGIT.matcher(token).matches())
            return true;
        return false;
    }    
    static boolean varlist(){
//        print("Variable List Function");
        
        if(comma()){
//            nextToken();            
            if(var_name()){
//                nextToken();
                if(varlist()){
                    if(end()){                       
                        return true;
                    }
                }else{
                    if(end()){
                        return true;
                    }                    
                }                
            }
            
        }

        return false;  
    }
    
    
    static boolean expr(){
        
//        print("Expression Function");
          
        if(var_name()){
//            nextToken();
            if(op()){
//                nextToken();
                if(expr()){
                   return true;
                }else{
//                   prevToken();
                }                
            }else{
//                prevToken();
            }            
        }
        else if(var_num()){
//            nextToken();
            if(op()){
//                nextToken();
                if(expr()){
                   return true;
                }else{
//                   prevToken();
                }                
            }else{
//                prevToken();

            }            
        }
        
        if(var_name()){
            return true;
        }else if(var_num()){
            return true;
        }       
        

        
        return false;
    }
    static boolean whitespace(){
//        print("Whitespace Function");
        if(WHITESPACE.matcher(token).matches()){
            return true;
        }
        
        return false;
    }
    
    static boolean end(){
//        print("End Function");
        if(token.equals(END)){
            return true;
        }
        
        return false;        
    }
    static boolean equal(){
//        print("Equal Function");
        if(token.equals(EQUAL_OP)){
            return true;
        }
     
        return false;        
    }    
    static boolean comma(){
//        print("Comma Function");
        if(token.equals(COMMA)){
            return true;
        }
     
        return false;        
    }      
    
    
    static boolean op(){
//        print("Operator Function");
        
        if(token.equals(PLUS_OP)){
            return true;
        }
        else if(token.equals(MINUS_OP)){
            return true;
        }
        
        return false;
    }
    
    
    

    
}
