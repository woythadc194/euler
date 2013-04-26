import java.util.*;
import java.math.*;
public class Euler125{

    private static ArrayList<String> palList = new ArrayList<String>();

    public static void main(String [] args){
        long start = System.currentTimeMillis();
        for(int i=1; i<=(int)Math.pow(10,8); i++){
            String s = "" + i;
            if(isPal(s))
                checkSquare(Integer.parseInt(s));
            if(i%1000000==0)
                System.out.println(i);
        }
        System.out.println(palList);
        BigInteger sum = new BigInteger("0");
        for(String s : palList)
            sum = sum.add(new BigInteger(s));
        System.out.println("Sum: " + sum);
        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
    }
    
    /*public static void checkSquare(int x){
        int counter = (int)Math.sqrt(x)+1;

        while(counter>0){
            int nLinked = 0;
            int y = x;
            for(int i=counter; i>0; i--){
                nLinked++;
                //System.out.print(y + " - " + i + "^2 = ");
                y -= Math.pow(i,2);
                //System.out.println(y);
                if(y==0)
                    if(nLinked==1)
                        break;
                    else
                        palList.add("" + x);
                if(y<0)
                    break;
            }
            counter--;
        }
    }*/
    
    public static void checkSquare(int x){
        int counter = (int)Math.sqrt(x);

        while(counter>0){
            int nLinked = 0;
            BigInteger y = new BigInteger("" + x);
            for(int i=counter; i>0; i--){
                nLinked++;
                //System.out.print(y + " - " + i + "^2 = ");
                y = y.subtract(new BigInteger("" + i).pow(2));
                //System.out.println(y);
                if(y.toString().equals("0"))
                    if(nLinked==1)
                        break;
                    else
                        palList.add("" + x);
                if(Integer.parseInt(y.toString())<0)
                    break;
            }
            counter--;
        }
    }
    
    public static boolean isPal(String s){
        String t = "";
        for(int i=s.length()-1; i>=0; i--)
            t += s.charAt(i);
        if(s.equals(t))
            return true;
        return false;
    }
}
