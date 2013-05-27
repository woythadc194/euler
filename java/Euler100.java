import java.util.*;
import java.io.*;
import java.math.*;
public class Euler100{
    
    public static void main(String[] args){
		long start = System.currentTimeMillis();
		long n = 1070379000000L;
		while(n<1200000000000L){
            n++;
            if(n%100000==0)
                System.out.println(">>> " + n);
            if(test(2, n, n)) {
                long i = getBlue(2,n,n);
                System.out.println(i+"/"+n+" * "+(i-1)+"/"+(n-1));
            }
        }
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
    /*
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		long n = 1000000000000L;
		while(!test(2, n, n)) {
            n++;
        }
        long i = getBlue(2,n,n);
        System.out.println(i+"/"+n+" * "+(i-1)+"/"+(n-1));
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	*/
	public static long getBlue(long low, long high, long n){
        long i = (low+high)/2;
        BigDecimal d = new BigDecimal(""+i);
        d = d.divide(new BigDecimal(n), 20, BigDecimal.ROUND_HALF_UP);
        d = d.multiply(new BigDecimal(i-1+""));
        d = d.divide(new BigDecimal(n-1+""), 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal half = new BigDecimal("1").divide(new BigDecimal("2"), 20, BigDecimal.ROUND_HALF_UP);
        if(d.toString().equals(half.toString()))
            return i;
        if(d.min(half)==half)
            return getBlue(low, i, n);
        return getBlue(i, high, n);
	}
	
	public static boolean test(long low, long high, long n){
        long i = (low+high)/2;
//        double d = (double)i/n*(i-1)/(n-1);
        BigDecimal d = new BigDecimal(""+i);
        d = d.divide(new BigDecimal(n), 20, BigDecimal.ROUND_HALF_UP);
        d = d.multiply(new BigDecimal(i-1+""));
        d = d.divide(new BigDecimal(n-1+""), 20, BigDecimal.ROUND_HALF_UP);
        BigDecimal half = new BigDecimal("1").divide(new BigDecimal("2"), 20, BigDecimal.ROUND_HALF_UP);
        if(d.toString().equals(half.toString()))
            return true;
        if(low+1==high){
            //System.out.println(n + " = " + d);
            return false;
        }
        if(d.min(half)==half)
            return test(low, i, n);
        return test(i, high, n);
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
