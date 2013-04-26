import java.util.*;
import java.io.*;
import java.math.*;
public class Euler111{

    private static int[] digits = new int[10];
    private static	ArrayList<Long> list = new ArrayList<Long>();
	
	public static void main(String[] args){
		long start = System.currentTimeMillis();		

	    Scanner sc = new Scanner(System.in);
	    System.out.print("N digits: ");
	    int scale = sc.nextInt();
	    
	    String s = "1";
	    while(s.length()!=scale)
	        s += "0";
        long beginLoop = Long.parseLong(s);
        s += "0";
        long endLoop = Long.parseLong(s);
        for(long i=beginLoop; i<endLoop; i++){
            if(i%100000 == 0)
                System.out.println(i);
            if(isPrime(i))
                list.add(i);
        }
        
        System.out.println(getSum());        
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static long getSum(){
        makeDigits();
        long sum = 0;
        int counter = 0;
        for(int digit=0; digit<10; digit++){
            for(long prime : list){
                String s = "" + prime;
                for(int i=0; i<s.length(); i++){
                    if(Integer.parseInt("" + s.charAt(i)) == digit)
                        counter ++;
                }
                if(counter == digits[digit])
                    sum += prime;
                counter = 0;
            }
        }
        return sum;
	}

    public static void makeDigits(){
        int counter = 0;
        for(int digit=0; digit<10; digit++){
            for(long prime : list){
                String s = "" + prime;
                for(int i=0; i<s.length(); i++){
                    if(Integer.parseInt("" + s.charAt(i)) == digit)
                        counter ++;
                }
                if(digits[digit] < counter)
                    digits[digit] = counter;
                counter = 0;
            }
        }
    }

	public static boolean isPrime(long x){
		for(long i=2; i<=Math.sqrt(x); i++)
			if(x%i==0)
				return false;
		return true;
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
