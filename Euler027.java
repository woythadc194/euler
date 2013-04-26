import java.util.*;
public class Euler027{

    public static void main(String [] args){
        long start = System.currentTimeMillis();
        int max = 0;
        int aTerm = 0;
        int bTerm = 0;
        for(int a= -1000; a< 1000; a++){
            for(int b= -1000; b< 1000; b++){
                int numPrimes = getNumPrimes(a,b);
                if(numPrimes> max){
                    max = numPrimes;
                    bTerm = b;
                    aTerm = a;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(" A:" + aTerm + " & B:" + bTerm + " made " + max);
        System.out.println("The product is :" + (aTerm*bTerm));
        System.out.println("Time: " + (end - start) + "ms");
    }   
    
    public static int getNumPrimes(int a, int b){
        int n = 0;
        while(true){
            int answer = ((int)Math.pow(n,2)) + (a*n) + (b);
            if(isPrime(answer))
                n++;
            else
                break;
        }
        return n;
    }
    
    public static boolean isPrime(int num){
        if (num%2==0 || (num<0))
            return false;
        for(int i=3; i*i <= num; i+=2)
            if(num % i == 0)
                return false;
        return true;
    }
}
