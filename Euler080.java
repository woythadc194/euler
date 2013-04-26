import java.math.*;
public class Euler080{

    public static void main(String[]args){
        int sum = 0;
        for(int i=1; i<=100; i++){
            int tempSum = 0;
            String s = makeSqrt(new BigInteger("" + i), 100);
            System.out.println(i + " : " + s);
            for(int j=0; j<s.length(); j++)
                tempSum += Integer.parseInt(""+s.charAt(j));
            if(tempSum>10)
                sum += tempSum;
        }
        System.out.println(sum);
    }
    
    public static String makeSqrt(BigInteger num, int iter){
        String digits = "";
        BigInteger origin = num;
        int guess = 0;
        int counter = 0;
        while(counter<iter){
            boolean digitFound=false;
            while(!digitFound){
                BigInteger x = new BigInteger(digits + guess);
                if(num.max(x.multiply(x)).toString().equals(num.toString())) {
                    guess++;;
                } else {
                    guess--;
                    x = new BigInteger(digits + guess);
                    digits = x.toString();
//                    System.out.println(num + " > " + x + "*" + x);
                    digitFound = true;
                }
            }
            num = new BigInteger(num.toString() + "00");
            guess = 0;
            counter++;
        }
        return digits;
    }
}
