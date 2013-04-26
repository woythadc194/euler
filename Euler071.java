import java.util.*;
import java.io.*;
import java.math.*;
public class Euler071{

    private static Map<Double, String> map = new TreeMap<Double, String>();

	public static void main(String[] args){
		long start = System.currentTimeMillis();
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
