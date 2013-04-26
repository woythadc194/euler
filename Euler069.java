import java.util.*;
import java.io.*;
import java.math.*;
public class Euler069{

    public static final int SCALE = 1000000;
    private static ArrayList<ArrayList<Integer>> factors 
                        = new ArrayList<ArrayList<Integer>>();
                        
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		double max = 0;
		int x = 0;
		for(int n=1; n<=SCALE+1; n++)
            factors.add(new ArrayList<Integer>());
		long start2 = System.currentTimeMillis();
		for(int n=2; n<=SCALE; n++){
		    if(n%1000==0){
                long end2 = System.currentTimeMillis();
                System.out.print(n + " ");
        		printTime(end2-start2);
        		start2 = System.currentTimeMillis();
            }
		    int relPrimes = getTotient(n);
		    double phi = (double)n/relPrimes;
		    if(phi>max){
		        x=n;
		        max=phi;
		    }
		}
		System.out.println(x);
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
    public static int getTotient(int n){
        int sum = 0;
        int x=n;
        while(x<=SCALE){
            factors.get(x).add(n);
            x+=n;
        }
        main:
        for(int i=1; i<n; i++){//factor list
            for(int j=0; j<factors.get(i).size(); j++) //factor in list
                if(factors.get(n).contains(factors.get(i).get(j)))
                    continue main;
//            System.out.print(i + " ");
            sum++;
        }
        return sum;
    }

   /* public static int getTotient(int n){
        int sum = 0;
        Set<Integer> factors = new HashSet<Integer>();
        for(int i=1; i<=Math.sqrt(n); i++){
            if(n%i==0){
                factors.add(n/i);
                factors.add(i);
            }
        }
        main:
        for(int i=1; i<n; i++){
            for(int fact=2; fact<=i; fact++){
                if(i%fact==0){
                    if(factors.contains(fact)){
                        continue main;
                    }
                }
            }
            sum++;
        }
        return sum;
    }
*/
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
