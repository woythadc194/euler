import java.util.*;
import java.io.*;
import java.math.*;
public class Euler112{

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		double percent = 0;
		int n = 100;
		int numBouncy = 0;
		while (percent!=99){
		    n++;
		    if(bouncy(""+n, "X")){
		        numBouncy++;
	        }
		    percent = (double)numBouncy/n*100;
		}
        System.out.println(n);
		long end = System.currentTimeMillis();
		printTime(end-start);
	}

    public static boolean bouncy(String s, String type){
        int A = Integer.parseInt("" + s.charAt(0));
            
        int B = Integer.parseInt("" + s.charAt(1));
         if(s.length()!=2){
            if(type.equals("X")){
                if(A<B){
                    s = s.substring(1);
                    return bouncy(s, "Inc");
                } else if(A>B){
                    s = s.substring(1);
                    return bouncy(s, "Dec");
                } else { //a==b
                    s = s.substring(1);
                    return bouncy(s, "X");
                }
            }else{ //s.length>2 && type!=X
                if(A<B){
                    if(type.equals("Dec"))
                        return true;
                    s = s.substring(1);
                    return bouncy(s, "Inc"); 
                } else if(A>B){
                    if(type.equals("Inc"))
                        return true;
                    s = s.substring(1);
                    return bouncy(s, "Dec"); 
                } else { //a==b
                    s = s.substring(1);
                    return bouncy(s, type);
                }
            } 
        }else{ //s.length==2
             if(A<B){
                if(type.equals("Dec"))
                    return true;
                return false;
            } else if(A>B){
                if(type.equals("Inc"))
                    return true;
                return false;
            } else { //a==b
                return false;
            }
        }
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
