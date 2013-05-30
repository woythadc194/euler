import java.util.*;
import java.math.*;
public class TestBigNumber{

    public static void main(String[] args){
        BigNumber a = new BigNumber("1002342342432340");
        BigNumber b = new BigNumber("456245272134293");
        BigInteger c = new BigInteger("1002342342432340");
        BigInteger d = new BigInteger("456245272134293");
        

        System.out.println("\nAdd: " + (a.add(b).toString().equals(c.add(d).toString()) ? "Works" : "FIXME"));
        System.out.println(a + " + " + b + " = " + a.add(b) + " : BigNumber");
        System.out.println(c + " + " + d + " = " + c.add(d) + " : BigInteger");
    

        System.out.println("\nSub: " + (a.subtract(b).toString().equals(c.subtract(d).toString()) ? "Works" : "FIXME"));
        System.out.println(a + " - " + b + " = " + a.subtract(b) + " : BigNumber");
        System.out.println(c + " - " + d + " = " + c.subtract(d) + " : BigInteger");
    

        System.out.println("\nMult: " + (a.multiply(b).toString().equals(c.multiply(d).toString()) ? "Works" : "FIXME"));
        System.out.println(a + " * " + b + " = " + a.multiply(b) + " : BigNumber");
        System.out.println(c + " * " + d + " = " + c.multiply(d) + " : BigInteger");
    

        System.out.println("\nDivide: " + (a.divide(b).toString().equals(c.divide(d).toString()) ? "Works" : "FIXME"));
//        System.out.println(a + " * " + b + " = " + a.divide(b) + " : BigNumber");
        System.out.println(c + " / " + d + " = " + c.divide(d) + " : BigInteger");
        System.out.println(d + " / " + c + " = " + d.divide(c) + " : BigInteger");

        System.out.println("\nPow: " + (a.pow(3).toString().equals(c.pow(3).toString()) ? "Works" : "FIXME"));
        System.out.println(a + " ^3 = " + a.pow(3) + " : BigNumber");
        System.out.println(c + " ^3 = " + c.pow(3) + " : BigInteger");

        
        System.out.println("\nEquals: " + ( (a.equals(a)==c.equals(c)) ? "Works" : "FIXME")); 
        System.out.println(a + " == " + a + " : BigNumber");
        System.out.println(c + " == " + c + " : BigInteger");
        
        System.out.println();
    }
}
