import java.math.BigInteger;
public class Euler053{

    public static void main(String [] args){
        int counter = 0;
        for(int n=1; n<=100; n++)
            for(int r=1; r<=n; r++)
                if(getNumPos(n,r).toString().length()>7 || 
                    Integer.parseInt(getNumPos(n,r).toString())>1000000)
                    counter++;
        System.out.println(counter);
    }
    
    public static String getNumPos(int n, int r){
        BigInteger factN = new BigInteger(getFact(n, new BigInteger("1")));
        BigInteger factR = new BigInteger(getFact(r, new BigInteger("1")));
        BigInteger factNmR = new BigInteger(getFact(n-r, new BigInteger("1")));
        return factN.divide(factR.multiply(factNmR)).toString();
    }
    
    public static String getFact(int x, BigInteger total){
        if(x==0)
            return total.toString();
        return getFact(x-1, total.multiply(new BigInteger(""+x)));
    }    
}
