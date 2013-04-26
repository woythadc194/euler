import java.util.*;
import java.io.*;
import java.math.*;
public class Euler063{

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int n = 1;
		int sum = 0;
		BigInteger x = new BigInteger("1");
		long start2 = System.currentTimeMillis();		
		long end2 = System.currentTimeMillis();
		while(end2-start2<1000){    //TIMEOUT of 1 second
		    while(x.pow(n).toString().length()<=n){
    		    end2 = System.currentTimeMillis();
    		    if(x.pow(n).toString().length()==n){
                    //System.out.println(x.toString() + "^" + n 
                    //                + "=" + x.pow(n));
                    sum++;
                    //System.out.println(sum);
        		    start2 = System.currentTimeMillis();		
                }
		        x = x.add(new BigInteger("1"));
		    }
		    n++;
		    x = new BigInteger("1");

	    }
	    System.out.println(sum);
		long end = System.currentTimeMillis();
		printTime(end-start);
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
