import java.util.ArrayList;
public class Euler050{
    
    private static int largePrime = 0;
    private static int max = 0;
    private static ArrayList<Integer> pList = new ArrayList<Integer>();
    
    public static void main(String [] args){
        long start = System.currentTimeMillis();
        for(int i=2; i<1000000; i++)
            if(isPrime(i))
                pList.add(i);
        testAdd();
        long end = System.currentTimeMillis();
        System.out.println(largePrime + " is sum of " 
                        + max + " primes");
        printTime(end - start);
    }
    
    public static void testAdd(){
        System.out.println("Size : " + pList.size());
        for(int k=0; k<pList.size(); k++){
            System.out.println(k);
            for(int i=0; i<pList.size(); i++){
                int counter = 0;
                int n = pList.get(i);
                for(int j=k+0; j<i; j++){
                    n-=pList.get(j);    
                    counter++;
                    if(n==0){   
                        if(counter>=max){
                            max = counter;
                            largePrime = pList.get(i);
                            break;
                        }  
                    }
                    if(n<0)
                        break;
                }
            }
        }
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
