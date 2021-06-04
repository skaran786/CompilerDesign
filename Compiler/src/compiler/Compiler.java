/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author manasvi
 */
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.regex.*;



public class Compiler {

    /**
     * @param args the command line arguments
     */

    
    
    
    //Specifying tokens
    
    final static Pattern LETTER = Pattern.compile("[a-zA-Z]");
    final static Pattern LETTERS = Pattern.compile(LETTER+"+");
    final static Pattern DIGIT = Pattern.compile("[0-9]");
    final static Pattern DIGITS = Pattern.compile("[1-9]"+DIGIT+"*");
//    final static String OPENPAR = "(";
//    final static String CLOSEPAR = ")";
//    final static String CURLYOPEN = "{";
//    final static String CURLYCLOSE = "}";
    final static String EQUAL_OP = "=";
    final static String PLUS_OP = "+";
    final static String END = ";";
    final static Pattern VARIABLE = Pattern.compile(LETTER+"[a-zA-Z0-9]*");
    final static Pattern WHITESPACE =Pattern.compile("\\s+");
    final static String TYPE_INT ="int";
    static StringBuilder token = new StringBuilder();
    static String token_pointer; 
    static int prev_pointer, next_pointer;
    static LinkedList<String> token_list= new LinkedList<String>();
    
    
    
    
    static boolean otherTerminal(String source, int prev, int next){
        moveTokenPointer(source, prev, next);//=
//        print("tokenpointer : "+token_pointer);
        switch(token_pointer){
            
            case EQUAL_OP:
                
                token.append(token_pointer);
                incrementPointers();
                return true;
            
            case END:                
                token.append(token_pointer);
                //incrementPointers();
                return true;    
                                
//            case PLUS_OP:
//                return true;
            
                
        
        }
        
        return false;
    
    }
    
    
    static void print(String token_name){
        System.out.println(token_name);
    }
    
    static void printDetails(String source){
        printPointerPositionsAndCharacter(source);
    }
    
    static void printPointerPositionsAndCharacter(String source){
        System.out.println("Prev_Pointer : "+prev_pointer);
        System.out.println("Next_Pointer : "+next_pointer);
        System.out.println("Character is : "+source.subSequence(prev_pointer, next_pointer));
    }
   
    static void incrementPointers(){
        prev_pointer++;
        next_pointer++;
    }
    
    static void addtoken(String source){
         token_list.add(token.toString());
         //set token to null
         token = new StringBuilder();
    }
    
    
    static void moveTokenPointer(String source, int prev, int next){
        
        token_pointer = (String) source.subSequence(prev, next);
        
    }
   
    
    static boolean find_DataTypeToken(String source, int prev, int next){
        //initialize token_pointer
        moveTokenPointer(source, prev, next);
        if (LETTERS.matcher(token_pointer).matches()) {                    
            //Possible options are variable or data_type or value assigned                
            boolean Match=false;    
            token.append(token_pointer);
                while(!Match){
                    //prev = 0, next = 1
                    //2nd prev = 1, next = 2
                    prev=next;
                    next++;
                    // prev = 1, next = 2
                    //2nd prev = 2, next = 3
                    moveTokenPointer(source, prev, next);
                    
                    token.append(token_pointer);
                    if(token.toString().equals(TYPE_INT)){
                        prev_pointer = prev;
                        next_pointer = next;
                        incrementPointers();
                        return true;
                    }
                }                
        }
        return false;
    }
    
    static boolean find_VariableToken(String source, int prev, int next){

//        moveTokenPointer(source, prev, next);
        if(VARIABLE.matcher(token_pointer).matches()){
            boolean Match = false;
            token.append(token_pointer);            
            while(!Match){
                //prev = 6, next = 7
                prev=next;
                next++;
                //prev = 7, next = 8
                moveTokenPointer(source, prev, next);          
                if(WHITESPACE.matcher(token_pointer).matches()
                        || token_pointer.equals(EQUAL_OP)){                    
                    Match = true;
                }else{
                    token.append(token_pointer);
                }   
            }
            prev_pointer = prev;
            next_pointer = next;
            return true;
        }
        
        
        return false;
        
        
    }
    
    static boolean find_NumberToken(String source, int prev, int next){
                
        moveTokenPointer(source, prev, next);
        if(token_pointer.equals("0")){
            //this only happens when we have 0, rest are handled by the else if case
            token.append(token_pointer);
            prev=next;
            next++;
            
            //but we should check if next thing is a digit than that will error
            moveTokenPointer(source, prev, next);
            if(DIGITS.matcher(token_pointer).matches()){
                return false;
            }else{
                prev_pointer = prev;
                next_pointer = next;
            }
            
            
            return true;
        }
        else if(DIGITS.matcher(token_pointer).matches()){
            boolean Match = false;
            token.append(token_pointer);
            //prev = 10, next = 11
            prev=next;
            next++;
            //prev = 11, next = 12
            moveTokenPointer(source, prev, next);    
            while(!Match){
                moveTokenPointer(source, prev, next); 
                if(DIGIT.matcher(token_pointer).matches()){
//                    System.out.println("Match Found DIGITS " + token);
                    token.append(token_pointer);
                    //prev = 11, next = 12
                    prev=next;
                    next++;
                    //prev = 12, next = 13
                }
                else{
                    //must anything but not a digit so we reach at end case
                    Match = true;
                }
            }
            prev_pointer = prev;
            next_pointer = next;
            return true;
        }
        
        
        return false;
    }
    
    static boolean remove_WhiteSpaces(String source, int prev, int next){
        
        moveTokenPointer(source, prev, next);
        if(WHITESPACE.matcher(token_pointer).matches()){
//            print("arrived"+token_pointer);
            boolean Match = false;
            while(!Match){
                //prev = 7, next = 8
                prev=next;
                next++;
                //prev = 8, next = 9
                moveTokenPointer(source, prev, next);
                if(
                   LETTERS.matcher(token_pointer).matches() || 
                   DIGIT.matcher(token_pointer).matches()  || 
                   DIGITS.matcher(token_pointer).matches()  ||
                   token_pointer.equals(EQUAL_OP) ||
                   token_pointer.equals(END) 
                   ) 
                {
                    prev_pointer = prev;
                    next_pointer = next;                    
//                    print("Whitespaces end ..");          
                    return true;
                }   
            }
        }   
        return false;
    }
   
    static void parse(String source){
    
//        int s=   45;
//        int        r =45;
//        int q            =45;
//        int t=45               ;
//        int a; int c,b;
//        a=10;
//        c=45;
//        b=23; int q=10,p=21;

//        >>>>>int x=10;<<<<<<< 
        
//  trying to parse this format  int var = 42;
        prev_pointer=0; 
        next_pointer=1;
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                           ^        
        
        if(find_DataTypeToken(source, prev_pointer, next_pointer)){
            addtoken(source);           
/*  Successfullly parsed "int" of int var = 42;
    Now remove all the whitespaces between 1st and 2nd token(Not really removing just moving the pointers)             
    Here whitespaces do matter, as they are seperator from type to variable name
*/                
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                              ^
            if(remove_WhiteSpaces(source, prev_pointer, next_pointer)){           
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                               ^               
                if(find_VariableToken(source, prev_pointer, next_pointer)){                  
                    addtoken(source);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                  ^
                    remove_WhiteSpaces(source, prev_pointer, next_pointer);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                   ^                    
                    if(otherTerminal(source, prev_pointer, next_pointer)){                        
                        addtoken(source);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                    ^                        
                        remove_WhiteSpaces(source, prev_pointer, next_pointer);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                     ^                        
                        if(find_NumberToken(source, prev_pointer, next_pointer)){                   
                            addtoken(source);
                            remove_WhiteSpaces(source, prev_pointer, next_pointer);                           
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                       ^                                                        
                            if(otherTerminal(source, prev_pointer, next_pointer)){
                                addtoken(source);
                                print("Successfully parsed");
                            }else{
//  No END token found           
                                print("Not able to parse");
                            }
                        }else{                 
//  Not able to initialize 
                            print("Not able to parse");
                        }
                    }else{
//  ASSIGNEMENT Operand expected
                        print("Not able to parse");
                    }                   
                }else{                   
//  Variable name incorrect
                    print("Not able to parse");
                }    
            }else{           
//  Should have whitespaces
                print("Not able to parse");
            } 
        }else{      
//  Incorrect type format
            print("Not able to parse");
        }    
    } 








    
    
    static void parseWithHelperMethods(String source){
    
        
//  trying to parse this format  int var = 42;
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                           ^
        prev_pointer=0; 
        next_pointer=1;
        if(find_DataTypeToken(source, prev_pointer, next_pointer)){
            addtoken(source);
            print("Token Details : "+token_list);
            printDetails(source);
            
/*  Successfullly parsed "int" of int var = 42;
    Now remove all the whitespaces between 1st and 2nd token(Not really removing just moving the pointers)             
    Here whitespaces do matter, as they are seperator from type to variable name
            
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42; */ 
//                                                                              ^                
            if(remove_WhiteSpaces(source, prev_pointer, next_pointer)){           
                printDetails(source);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                               ^
                if(find_VariableToken(source, prev_pointer, next_pointer)){
                    addtoken(source);
                    print("Token Details : "+token_list);
                    printDetails(source);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                  ^
                    remove_WhiteSpaces(source, prev_pointer, next_pointer);
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                   ^
                    printDetails(source);
                    print("Token Details : "+token_list);
//                    print("-----------------");

                    if(otherTerminal(source, prev_pointer, next_pointer)){
                        
                        addtoken(source);
                        printDetails(source);
                        print("Token Details : "+token_list);
//                        
//                        print("<----------------->");
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                    ^
                        
                        remove_WhiteSpaces(source, prev_pointer, next_pointer);
                        printDetails(source);
                        
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                     ^
                        
                        if(find_NumberToken(source, prev_pointer, next_pointer)){                   
                            addtoken(source);
                            print("Token Details : "+token_list);
                            printDetails(source);
                            remove_WhiteSpaces(source, prev_pointer, next_pointer);
                            printDetails(source);
//                            print(">>>>>>>>>>>>>>>>>>>>>>>>>");
//  Currently token pointer is at >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> int var = 42;
//                                                                                       ^
                            
                            if(otherTerminal(source, prev_pointer, next_pointer)){
                                addtoken(source);
                                print("Token Details : "+token_list);
                                printDetails(source);
                                print("Successfully parsed");
                            }else{
                            
                            
                            }
                            
                            
                        
                        }else{
                        
//                          Not able to initialize 
                        }
                        
                        
                        
                    }
                    else{
                        
                        //ASSIGNEMENT Operand expected
                        
                    }
                                       
                }else{                   
//                 variable name incorrect                       
                }    
            }else{           
//                Should have whitespaces
            } 
       }else{      
//            Incorrect type format       
       }

    }        
        

    
    
    static void checker(){
        //BASE RULES AND CHECKERS
        //Letter must be a single character only English Alphabet
        //Letters must be one or more occurance of the Letter
        
        //returns false
        System.out.println(LETTER.matcher("aaa").matches());
        //returns true
        System.out.println(LETTER.matcher("a").matches());
        //returns false
        System.out.println(LETTERS.matcher("0ab").matches());
        //returns true
        System.out.println(LETTERS.matcher("aaa").matches());
        
        //Digit must any single DIGIT from 0 to 9
        //Digits must be one or more occurance of the DIGIT
        //but should start from >0, so 09 not allowed
        
        //returns false
        System.out.println(DIGIT.matcher("09").matches());
        //returns true
        System.out.println(DIGIT.matcher("9").matches());
        //returns false
        System.out.println(DIGITS.matcher("09").matches());
        //returns true
        System.out.println(DIGITS.matcher("22").matches());
        
        //VARIABLE must start with letter and then digit or letter may follow
        //returns true
        System.out.println(VARIABLE.matcher("a22").matches());
        //returns true
        System.out.println(VARIABLE.matcher("ab22").matches());
        //returns true
        System.out.println(VARIABLE.matcher("ab22ad").matches());
        //returns false
        System.out.println(VARIABLE.matcher("0av22").matches());
        
        
        //WHITESPACE must be on or more empty spaces.
        //returns true
        System.out.println(WHITESPACE.matcher(" ").matches());
        //returns true
        System.out.println(WHITESPACE.matcher("  ").matches());
        
    }
    
    
    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here

        String source ="int var = 42;";
        //              
//        String source ="int v = 4;";
//        String source ="int var = 4;";
//        String source ="int var= 42;"; 
//        String source ="int var =42;";
//        String source ="intvar = 42;";
//        String source ="int      var = 42;";
//        String source ="int var    =    42;";
//        String source ="int var = 42;     ";
//        String source ="int var = 42 ;";

//        String source ="int var = 0;";

//        Error case         
//        String source ="int var = 0;";

//       StringBuilder token_Ex = new StringBuilder();
//       
//       String x = source.subSequence(0, 1).toString();
//       token_Ex.append(x);
//       print(token_Ex.toString());        
//
//       
//       x = source.subSequence(1, 2).toString();
//       token_Ex.append(x);
//       print(token_Ex.toString());
//       
//       x = source.subSequence(2, 3).toString();
//       token_Ex.append(x);
//       print(token_Ex.toString());
       
       



        
//            
        parse(source);
        
//        parseWithHelperMethods(source);
    }
    
}
