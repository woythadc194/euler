import java.math.BigInteger;
public class Euler55{

    public static void main(String[]args){
        int sum=0;
        for(int i=1; i<10000; i++)
            if(!test(i))
                sum++;
        System.out.println("Non-Lychrel numbers: " + sum);
    }
    
    public static boolean test(int x){
        System.out.print(x);
        BigInteger n = new BigInteger(""+x);
        n=n.add(new BigInteger(reverse(n)));
        int counter=1;
        while(counter<=55)
            if(isPal(n.toString())){
                System.out.println(" after " + counter + " times = " + n.toString());
                return true;
            } else {
                n=n.add(new BigInteger(reverse(n)));
                counter++;
            }
        System.out.println(" failed with " + n.toString());
        return false;
    }
    
    public static String reverse(BigInteger n){
        String s = n.toString();
        String t = "";
        for(int i=s.length()-1; i>=0; i--)
            t += s.charAt(i);
        return t;
    }
    
    public static boolean isPal(String s){
        String t = "";
        for(int i=s.length()-1; i>=0; i--)
            t += s.charAt(i);
        return s.equals(t);
    }
}
