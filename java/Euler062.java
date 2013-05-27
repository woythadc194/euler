import java.util.*;
import java.io.*;
import java.math.*;
public class Euler062{

    private static Map<String, ArrayList<Long>> map 
                        = new HashMap<String, ArrayList<Long>>();
    private static boolean found = false;
    private static int scale = 0;
    
	public static void main(String[] args){
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Scale: ");
	    scale = sc.nextInt();
	    
		long start = System.currentTimeMillis();
		int i=1;
		while(!found){
		    makeKey(Long.parseLong(getCube(i)));
		    i++;
	    }
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static void makeKey(long l){
	    String s = "" + l;
	    int [] digits = new int [11];
	    digits[10] = s.length();
	    for(int i=0; i<s.length(); i++){
	        int digit = Integer.parseInt("" + s.charAt(i));
	        digits[digit]++;
	    }
	    String newKey = Arrays.toString(digits);
        ArrayList<Long> temp = new ArrayList<Long>();
        if(map.keySet().contains(newKey))
            temp = map.get(newKey);
        temp.add(l);
        map.put(newKey, temp);
        if(temp.size()==scale){
            found=true;
            System.out.println(newKey + " : " + temp);
        }
	}
	
	public static String getCube(int x){
	    BigInteger y = new BigInteger(""+x);
	    y = y.multiply(new BigInteger(""+x));
	    y = y.multiply(new BigInteger(""+x));
	    return y.toString();
	}
	
	public static boolean isCube(int x){
	    for(int i=1; i<=Math.sqrt(x); i++)
            if(i*i*i==x && x/i/i==i){
                System.out.print(i + "*" + i + "*" + i + " = ");
                return true;	        
            }
	    return false;
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
