import java.util.*;
import java.io.*;
import java.math.*;

public class PEvaluator{
    
    private String line = ""; //post fix expression
    
    //Constructor
    public PEvaluator(String line){
        this.line=line;
    }
    
    public String eval(){
    
        //Scanner on line to read token by token
        Scanner lineScanner = new Scanner(line);
		while(lineScanner.hasNext()){
            String str = lineScanner.next();
            //if the token isn't recognized as an int or an operand return error
            if(!isNumeric(str) && !isOperand(str)){
                return "ERROR Illegal token: " + str;
            }
        }
        //Reset scanner on line
        lineScanner = new Scanner(line);
        //stack to hold strings (Needed for BigDecimal)
        Stack<String> stk = new Stack<String>();
        
        //while there are tokens left in the line
        while(lineScanner.hasNext()){
            String str = lineScanner.next();
            //push on the stack if its numeric
            if(isNumeric(str)){
                stk.push(str);
            }else if(str.equals("!")) { //if token == !
                if(stk.size()==0){
                    //return error is stack is empty
                    return"ERROR Too few operands";
                }
                //else negate the highest item in stack
                BigDecimal x = new BigDecimal(stk.pop());
                x = x.multiply(new BigDecimal("-1"));
                stk.push(x.toString());
            }else { //token is an operanc, but not == !
                if(stk.size()<2){
                    //return error is the stack has less than two items
                    return "ERROR Too few operands";
                } else {
                    //b term is the highest item in stack
                    //a term is the second highest
                    BigDecimal b = new BigDecimal(stk.pop());
                    BigDecimal a = new BigDecimal(stk.pop());
                
                    if(str.equals("+")) { //a + b then push back to stack
                        a = a.add(b);
                        stk.push(a.toString());
                    }else if(str.equals("*")) { //a * b then push back to stack
                        a = a.multiply(b);
                        stk.push(a.toString());
                    }else if(str.equals("/")) { //a / b then push back to stack
                        if(b.toString().equals("0"))
                            //return error if b term == 0
                            return"ERROR Divide by zero";
                        a = a.divide(b, 1000, BigDecimal.ROUND_UP);
                        stk.push(a.toString());
                    }else if(str.equals("-")) { //a - b then push back to stack
                        a = a.subtract(b);
                        stk.push(a.toString());
                    }else if(str.equals("%")) { //a % b then push back to stack
                        if(b.toString().equals("0"))
                            //return error if b term == 0
                            return"ERROR Modulus by zero";
                        BigInteger a2;
                        if(a.toString().contains("."))
                            a2 = new BigInteger(a.toString().substring(0,a.toString().indexOf('.')));
                        else
                            a2 = new BigInteger(a.toString());
                        BigInteger b2;
                        if(b.toString().contains("."))
                            b2 = new BigInteger(b.toString().substring(0,b.toString().indexOf('.')));
                        else
                            b2 = new BigInteger(b.toString());
                        a2 = a2.mod(b2);
                        stk.push(a2.toString());
                    }
                }
            }
        }
        
        if(stk.size()>1){
            //return error if out of tokens and stack has more than one item
            return"ERROR Too many operands";
        }
        if(stk.size()<1){
            //return error if out of tokens and stack has more than one item
            return"ERROR Too few operands";
        } 
        //print OK followed by the evaluated integer to the client
        return stk.pop();
    }
    
    //test if the token is numeric
    private static boolean isNumeric(String str){
        try{
            //if BigInt can be created, return true
            BigDecimal x = new BigDecimal(str);
            String s = "" + x.toString().charAt(0);
            if(isOperand(s))
                return false;
            return true;
        }catch(Exception e){
            //else return false
            return false;
        }
    }
    //test if the token is an operand
    private static boolean isOperand(String s){ 
        //return true if token is an operand
        if( s.equals("!") || s.equals("%") || s.equals("*") || 
            s.equals("-") || s.equals("+") || s.equals("/"))
            return true;
        //if not return false
        return false;
    }
    
}

