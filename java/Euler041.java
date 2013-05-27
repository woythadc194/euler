import java.util.ArrayList;
public class Euler041{
    
    public static void main(String [] args){
        for(long i=987654321L; i>0; i-=2)
            if(isPandigital(i)){
                System.out.print(i);
                if(isPrime((int)i)){
                    System.out.println(" true"); 
                    break;
                } else {
                    System.out.println(" false");
                }   
            }
    }

    public static boolean isPrime(int num){
        for(int i=2; i<Math.sqrt(num); i++){
            if(num%i==0)
                return false;

        }  
        return true;
    }   
    
    public static boolean isPandigital(long num){
        ArrayList<Boolean> test = new ArrayList<Boolean>();
        String number = "" + num;
        for(int i=0; i<=number.length(); i++)
            test.add(false);
        test.set(0,true);
        for(int i=0; i<number.length(); i++){
            String digit = "" + number.charAt(i);
            int x = Integer.parseInt(digit);
            if(x>number.length())
                return false;
            if(test.get(x)==false)
                test.set(x, true);
            else
                return false;
        }
        return true;
    }
}
