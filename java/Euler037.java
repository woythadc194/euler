import java.util.*;
public class Euler037{
    public static void main(String[] args){
        int numPrimes = 0;
        int n = 10;
        int sum = 0;
        ArrayList<String> list = new ArrayList<String>();
        while(n<1000000){
            if(truncLeft(n) && truncRight(n)){
                list.add("" + n);
            }
            if(n%10000==0)
                System.out.println(n);
            n++;
        } 
        System.out.println(list);
        for(int i=0; i<list.size(); i++)
            sum += Integer.parseInt(list.get(i));
        System.out.println("\n" + sum);
    }
    
    public static boolean truncLeft(int n){
        String x = "" + n;
        if(x.length()==1){
            if(x.equals("1"))
                return false;
            else
                return isPrime(n);
        } else if(isPrime(n)==false){
            return false;
        } else { //isPrime(n)==true && x.length()>1
            n = Integer.parseInt(x.substring(0, x.length()-1));
            return truncLeft(n);
        }
    }
    
    public static boolean truncRight(int n){
        String x = "" + n;
        if(x.length()==1){
            if(x.equals("1"))
                return false;
            else
                return isPrime(n);
        } else if(isPrime(n)==false){
            return false;
        } else { //isPrime(n)==true && x.length()>1
            n = Integer.parseInt(x.substring(1));
            return truncRight(n);
        }
    }
    
    public static boolean isPrime(int n){
        for(int i=2; i<n/2+1; i++)
            if(n%i==0)
               return false;    
        return true;
    }
}
