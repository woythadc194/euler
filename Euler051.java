import java.util.*;
import java.io.*;
import java.math.*;
public class Euler051{

    private static final int SCALE = 8;
    private static Map<String, Integer> map = new HashMap<String, Integer>();
    private static boolean found = false;

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int n = 10;
		while(!found){
    		if(isPrime(n)){
                makeKeys(n);
                map.clear();
            }
            n++;
        }
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static void makeKeys(int n){
	    String s = ""+n;
	    int twoTarget = getBinTarget(s.length())-2;
	    mainLoop:
	    for(int binary=twoTarget; binary>=0; binary--){
	        String b = new BigInteger("" + binary).toString(2);
	        while(b.length()!=s.length())
	            b = "0" + b;
	        String t = "";
	        boolean star = false;
	        for(int i=0; i<s.length(); i++){
    	        if(("" +b.charAt(i)).equals(""+0)){
	                t += "*";
	                star = true;
                } else{
                    t += s.charAt(i);
                }
	        }
            if(star){
                secondaryLoop:
	            for(int digit=0; digit<=9; digit++){
	                String z = t;
	                z = z.replace('*', ("" + digit).charAt(0));
	                int x = Integer.parseInt(z);
                    if((z).charAt(0)=='0')
                        continue;
	                if(isPrime(x)){
                        if(!map.keySet().contains(t)){
                            map.put(t, 1);
                        } else {
                            int val = map.get(t);
                            map.put(t, val+1);
                        }
                        if(map.get(t)==SCALE){
                            found = true;
                            System.out.println(t);
                            System.out.println(getPrime(t));
                            break mainLoop;
                        }
                    }
                }    
           }        
	    }
	}
	
	public static String getPrime(String s){
        for(int digit=0; digit<=9; digit++){
            String t = s.replace('*', ("" + digit).charAt(0));
            if((t).charAt(0)=='0')
                continue;
            if(isPrime(Integer.parseInt(t)))
                return t;
        }
        return "ERROR";
    }
	
	public static int getBinTarget(int x){
	    return Integer.parseInt("" + new BigInteger("" + (int)(Math.pow(2,x))));
	}

	public static boolean isPrime(int x){
		for(int i=2; i<=Math.sqrt(x); i++)
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
