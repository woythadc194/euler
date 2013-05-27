import java.util.*;
import java.io.*;
import java.math.*;
public class Euler071{

    private static final int SCALE = 8;
    private static Map<Double, String> map = new TreeMap<Double, String>();
    private static double [] array;
    
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		
		for(int d=1; d<=SCALE; d++){
		    for(int n=1; n<d; n++){
		        double decimal = ((double)n)/d;
		        String frac = getReducedFrac(n,d);
		        map.put(decimal, frac);
		    }
	    }
	    
	    array = new double[map.size()];
	    int counter = 0;
        for(double d : map.keySet()){
            array[counter] = d;
            counter ++;
        }
        Arrays.sort(array);
        
        int index = 0;
        for(int i=0; i<array.length; i++){
            if(array[i]==0.4)
                index = i-1;
        }
        for(double d : map.keySet())
            System.out.print(map.get(d) + " ");
        System.out.println();
	    System.out.println(map.get(array[index]));
	    
		long end = System.currentTimeMillis();
		printTime(end-start);
	}

	public static String getReducedFrac(int num, int den){
		mainLoop:
		while(true){
			for(int i=2; i<=num; i++){
				if(num%i==0 && den%i==0){
					num /= i;
					den /= i;
					continue mainLoop;
				}
			}
			break;
		}
		return num + "/" + den;
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
