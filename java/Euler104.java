import java.util.*;
import java.io.*;
import java.math.*;
public class Euler104{

    private static boolean found = false;

	public static void main(String[] args){
		long start = System.currentTimeMillis();
		
		int k = 1;
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("1");
		BigInteger c = new BigInteger("1");
		
		//wait until C is at least 9 digits long
		while( (c.toString().length() < 9)) {
            c = b.add(a);
		    a=b;
		    b=c;
            k++;
		}
		
		BigInteger backA = a, backB = b, backC = c;
		
		BigInteger frontA = a, frontB = b, frontC = c;
		
		
		//from here on the numbers between the back 9 and front 9 dont matter 
		// so truncate the number into two seperate equations
		while(!found) {
           
            //Start of BACK		
		    backC = backB.add(backA);
		    if(backC.toString().length() > 9)
    		    backC = new BigInteger(backC.toString().substring(backC.toString().length()-9));
		    backA = backB;
		    backB = backC;
		    //End of BACK
		    
		    //Start of Front
	        frontC = frontB.add(frontA);
	        if(frontC.toString().length() > 15){ //15, not 9, because of possibility of carrying 1's 
	            frontC = new BigInteger(frontC.toString().substring(0,15));
	            frontA = new BigInteger("0" + frontB.toString().substring(0, 14));
	            frontB = frontC;
	        } else { //not >12
	            frontA = frontB;
	            frontB = frontC;
	        }
	        //End of FRONT

            k++;
            if(test(frontC.toString()) && test(backC.toString()))
                found = true;
		}
		System.out.println("Front = " + frontC.toString().substring(0,9) 
		                    + ", Back = " + backC + " : F(" + k + ")");
		
		long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static boolean test(String s){
	    if(s.length()<9)
	        return false;
	    return isPan(s.substring(0,9), 9);
            
	}

	public static boolean isPan(String s, int scale){
		//to add 0, change the array size by +1 and remove -1 from marked lines
		if(s.length()!=scale)
			return false;
		boolean [] digits = new boolean[scale];
		for(int i=0; i<s.length(); i++){
			int y = Integer.parseInt("" + s.charAt(i));
			if(y>scale || y==0) //Also remove the RHS
				return false;
			if(digits[y-1]==true) //HERE
				return false;
			digits[y-1] = true; //HERE
		}
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
