/*
Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

37 36 35 34 33 32 31
38 17 16 15 14 13 30
39 18  5  4  3 12 29
40 19  6  1  2 11 28
41 20  7  8  9 10 27
42 21 22 23 24 25 26
43 44 45 46 47 48 49

It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
*/

import java.util.*;
public class Euler058{

    private static ArrayList<Integer> list = new ArrayList<Integer> ();
    
    public static void main(String[] args){
        int counter = 0;
        int multiplier = 1;
        int current = 1;
        int prev = current;
        double nPrimes = 0;
        list.add(1);
        while(true){
            current++;
            if(current==prev+multiplier*2){
                list.add(current);
                counter++;
                prev=current;
                if(isPrime(current)){
                    nPrimes++;
                }
                continue;
            }
            if(counter==4){
                //test has to be here to make a perfect square
                if(nPrimes/list.size()<0.1)
                    break;
                counter=0;
                multiplier++;
            }
        }
        System.out.println("L: " + ((multiplier*2)+1));
    }
    
    public static boolean isPrime(int x){
        for(int i=2; i<=Math.sqrt(x); i++)
            if(x%i==0)
                return false;
        return true; 
    }
}
