import java.util.*;
public class Euler087{

    private static ArrayList<Integer> twos = new ArrayList<Integer>();
    private static ArrayList<Integer> threes = new ArrayList<Integer>();
    private static ArrayList<Integer> fours = new ArrayList<Integer>();
    
    private static Map<Integer, String> list = new HashMap<Integer, String>();
    private static final int SCALE = 50000000;
    
    public static void main(String[] args){
        makeLists();
        int counter=0;
        for(int two : twos)
            for(int three : threes)
                for(int four : fours){
                    int n = two + three + four;
                    if(n<SCALE)
                        if(!list.keySet().contains(n)){
                            counter++;
                            list.put(n, two+"+"+three+"+"+four);
                        }
                }
        System.out.println(counter);
    }
    
    public static void makeLists(){
        int i=2;
        boolean twoLim=false;
        boolean threeLim=false;
        boolean fourLim=false;
        
        while(!twoLim){
            if(isPrime(i)){
                int two = (int)Math.pow(i,2);
                if(two>SCALE)
                    twoLim=true;
                else
                    twos.add(two);
                    
                if(!threeLim){
                    int three = (int)Math.pow(i,3);
                    if(three>SCALE)
                        threeLim=true;
                    else
                        threes.add(three);
                }
                if(!fourLim){
                    int four = (int)Math.pow(i,4);
                    if(four>SCALE)
                        fourLim=true;
                    else
                        fours.add(four);
                }
            }
            i++;
        }
    }
    
    public static boolean isPrime(int x){
        for(int i=2; i<=Math.sqrt(x); i++)
            if(x%i==0)
                return false;
        return true;
    }
}
