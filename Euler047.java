import java.util.*;
public class Euler47{
    private static int scale=0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("N: ");
        scale = sc.nextInt();
        int [] list = new int[scale];
        int counter = 1;
        while(!listTrue(list)){
            list = new int[scale];  
            for(int i=0; i<scale; i++){
                if(passes(counter+i))
                    list[i]=counter+i;
                else
                    break;
            }      
            counter++;
        }
        System.out.println(Arrays.toString(list));
    }
    
    public static boolean passes(int x){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for(int i=2; i<=Math.sqrt(x); i++){
            if(x%i==0){
                int a=i, b=x/a;
                factors.add(a);
                if(b!=a && b!=x)
                    factors.add(b);
            }
        }
        int nPrimes=0;
        for(int i=0; i<factors.size(); i++){
            if(isPrime(factors.get(i)))
                nPrimes++;
        }
        if(nPrimes==scale)
            return true;
        return false;
    }
    
    public static boolean isPrime(int x){
        for(int i=2; i<=Math.sqrt(x); i++)
            if(x%i==0)
                return false;
        return true;
    }
    
    public static boolean listTrue(int [] list){
        for(int i=0; i<list.length; i++)
            if(list[i]==0)
                return false;
        return true;
    }
}
