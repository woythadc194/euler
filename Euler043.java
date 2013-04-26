import java.util.*;
public class Euler043{
    
    private static ArrayList<Integer> l2 = new ArrayList<Integer>();
    private static ArrayList<Integer> l3 = new ArrayList<Integer>();
    private static ArrayList<Integer> l5 = new ArrayList<Integer>();
    private static ArrayList<Integer> l7 = new ArrayList<Integer>();
    private static ArrayList<Integer> l11 = new ArrayList<Integer>();
    private static ArrayList<Integer> l13 = new ArrayList<Integer>();
    private static ArrayList<Integer> l17 = new ArrayList<Integer>();
    private static long sum=0;
    
    public static void main(String[] args){
        makeLists();
        for(int num : l2)
            match3(num);
        System.out.println("Sum = " + sum);
    }
    
    public static void match3(int x){
        for(int num : l3)
            if(x%100 == num/10)
                match5(Integer.parseInt(""+ x + (num%10)));
    }
    
    public static void match5(int x){
        for(int num : l5)
            if(x%100 == num/10)
                match7(Integer.parseInt(""+ x + (num%10)));
    }
    
    public static void match7(int x){
        for(int num : l7)
            if(x%100 == num/10)
                match11(Integer.parseInt(""+ x + (num%10)));
    }
    
    public static void match11(int x){
        for(int num : l11)
            if(x%100 == num/10)
                match13(Integer.parseInt(""+ x + (num%10)));
    }
    
    public static void match13(int x){
        for(int num : l13)
            if(x%100 == num/10)
                match17(Integer.parseInt(""+ x + (num%10)));
    }
    
    public static void match17(int x){
        for(int num : l17)
            if(x%100 == num/10)
                almostPan(""+ x + (num%10));
    }
    
    public static void  almostPan(String s){
        boolean [] digits = new boolean[10];
        for(int i=0; i<s.length(); i++){
            int x = Integer.parseInt("" + s.charAt(i));
            digits[x] = true;
        }
        int falseCounter = 0;
        int falseDigit = 0;
        for(int i=0; i<digits.length; i++)
            if(digits[i]==false){
                falseCounter++;
                falseDigit = i;
            }
        if(falseCounter==1)
            sum += Long.parseLong("" + falseDigit + s);            
    }
    
    public static void makeLists(){
        for(int i=0; i<1000; i++){
            if(i%2==0)
                l2.add(i);
            if(i%3==0)
                l3.add(i);
            if(i%5==0)
                l5.add(i);
            if(i%7==0)
                l7.add(i);
            if(i%11==0)
                l11.add(i);
            if(i%13==0)
                l13.add(i);
            if(i%17==0)
                l17.add(i);
        }
    }
}
