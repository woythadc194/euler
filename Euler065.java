import java.math.*;
public class Euler065{
    
    public static final int SCALE = 100;    //how many expansions of e, must be at least 3

    public static void main(String[]args){
        long start = System.currentTimeMillis();
        
        //Sets up an array of denominator values for continued fraction of e
        int [] list = new int[SCALE-1]; // -1 because the first iteration is the whole number 2, which is added on afterwords
        int i = 0;
        int counter = 1;
        while(i<list.length){
            if((i+2)%3==0){             //every 3rd index starting at 2...
                list[i] = counter*2;    //...gets the counter*2...
                counter++;              //...then increment the counter by 1
            } else { 
                list[i] = 1;            //every other index gets 1 as the value
            }
            i++;
        }
        System.out.println(getNumeratorSum(list));
        long end = System.currentTimeMillis();
		printTime(end-start);
    }    

    public static int getNumeratorSum(int[] list){
        //These are the initial starting conditions for all parts of the fractions
        BigInteger A = new BigInteger("1");
        BigInteger B = new BigInteger(""+list[list.length-2]);
        BigInteger C = new BigInteger("1");
        BigInteger D = new BigInteger(""+list[list.length-1]);
        
        // (A/1)/(B/1 + C/D) is how this should be viewed from the deepest expansion out 
        for(int i=list.length-2; i>=0; i--){
            B = new BigInteger(""+list[i]); //next denominator up in list
            B = B.multiply(D);              //makes B/1 now have a common denominator D with C/D
            B = B.add(C);                   //add B/D and C/D to make new B/D
            
            //Skip Flip and Multiply (A/1) / (B/D)
            //  A   * D
            //  1     B
            
            //Since A is always 1, the answer to that is D/B
            //Now assign the new values of the C/D fraction for the next iteration of the loop
            C = D;  //new C gets old D
            D = B;  //new D gets old B   
        }
        //Since e = 2 + <Continued Fraction> we need to add a whole value of 2 to the fraction
        C = C.add(D.multiply(new BigInteger("2"))); //Add 2*D to C
        System.out.println(C + " / " + D);        //For testing to make sure values of numerator and denominator are correct
        
        
        //Calculates the sum of the numerator after all of the continued fraction has been computed
        int numeratorSum = 0;
        String s = C.toString();
        for(int i=0; i<s.length(); i++)
            numeratorSum += Integer.parseInt("" + s.charAt(i));
        return numeratorSum;
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
