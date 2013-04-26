public class Euler46{
    
    public static void main(String[] args){
        int i=1;
        while(true)
            if(test(i))
                break;
            else
                i++;
        System.out.println(i);
    }
    
    public static boolean test(int n){
        if(!isPrime(n) && n%2==1){ //if odd and composite
            System.out.print(n + " = ");
            if(!isGoldbach(n)){ //return true if successful at disproving thoery
                return true;
            }
        }
        return false;
    }
    
    public static boolean isGoldbach(int n){
        for(int i=2; i<n; i++){
            int x=n;
            if(isPrime(i)){
                x-=i;
                if(x%2==0){
                    x/=2;
                    if(x%(Math.sqrt(x))==0){
                        System.out.println(i + " + 2x" + (int)Math.sqrt(x) + "^2");
                        return true;
                    } else {
                        continue;
                    }
                } else{
                 continue;
                }
            } else{
                continue;
            }
        }
        return false;
    }
    
    public static boolean isPrime(int num){
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num%i==0)
                return false;

        }  
        return true;
    } 
}
