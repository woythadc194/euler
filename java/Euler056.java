import java.util.*;
import java.io.*;
import java.math.*;
public class Euler056{

    private static int maxSum=0;
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		for(int a=1; a<100; a++)
		    for(int b=1; b<100; b++)
		        testSum(a,b);
		System.out.println("Max sum: " + maxSum);
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static void testSum(int a, int b){
	    BigInteger prod = new BigInteger("" + a);
	    prod = prod.pow(b);
	    int sum = 0;
	    for(int i=0; i<prod.toString().length(); i++)
	        sum += Integer.parseInt("" + prod.toString().charAt(i));
        if(sum>maxSum)
            maxSum=sum;
	}

	public static void printTime(long ms){
		long s=0, m=0, h=0;
		if(ms>1000){
			s = ms/1000;
			if(s>60){
				m = s/60;
				if(m>60)
					h = m/60;
			}
		}
		m%=60;
		s%=60;
		ms%=1000;
		String a=""+m, b=""+s, c=""+ms;
		if(a.length()!=2)
			a = "0" + a;
		if(b.length()!=2)
			b = "0" + b;
		while(c.length()!=3)
			c = "0" + c;
		System.out.println(h + ":" + a + ":" + b + "." + c);
	}
}
