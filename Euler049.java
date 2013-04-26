import java.util.*;
public class Euler49{

    public static void main(String[] args){
        for(int i=1234; i<=9876; i++){
            test(i);
        }
    }
    
    public static void test(int x){
        if(testA(""+x) && testB(x)){
            //System.out.println("testing " + x);
            testC(x);
        }
    }
    
    public static boolean testA(String s){ //number is permutable
        ArrayList<Integer> digits = new ArrayList<Integer>();
        for(int i=0; i<4; i++){
            int x = Integer.parseInt(""+s.charAt(i));
            if(x==0)
                return false;
        }   
        return true;
    }
    
    public static boolean testB(int x){ //number is prime
        for(int i=2; i<=Math.sqrt(x); i++)
            if(x%i==0)
                return false;
        return true;
    }
    
    public static void testC(int x){//we know x is permutable and prime
        int x1=x, x2=0, x3=0;
        String s = ""+x;
        ArrayList<Character> x1List = new ArrayList<Character>();
        for(int i=0; i<4; i++){
            char y = s.charAt(i);
            x1List.add(y);    
        }
        Collections.sort(x1List);
        int start = getStart(x1List);
        int end = getEnd(x1List);
        MainLoop:
        for(int i=start; i<=end; i++){
            if(isPerm(i, x1List) && testB(i) && testA(""+i)){
                x2=i;
                for(int j=i+1; j<=end; j++){
                    if(isPerm(j, x1List) && testB(j) && testA(""+i)){
                        x3=j;
                        if(finalTest(x1, x2, x3)){
                            System.out.println(x1 + "," + x2 + "," + x3);
                            continue MainLoop;
                        }
                    }
                }
            }
        }
    }
    
    public static boolean isPerm(int x, ArrayList<Character> x1List){
        String s = ""+x;
//        System.out.println(x);

        ArrayList<Character> xxList = new ArrayList<Character>();
        for(int i=0; i<4; i++){
            char y = s.charAt(i);
            xxList.add(y);    
        }
        Collections.sort(xxList);
        return xxList.equals(x1List);
    }
    
    public static boolean finalTest(int x1, int x2, int x3){
        int min, mid, max;
        min = Math.min(x1,Math.min(x2,x3));
        max = Math.max(x1,Math.max(x2,x3));
        if(x1!=min && x1!=max) 
            mid = x1;
        else if(x2!=min && x2!=max) 
            mid = x2;
        else
            mid = x3;
        return ((max-mid == mid-min) && (max!=min) && (max!=mid) &&(min!=mid));
    }
    
    public static int getStart(ArrayList<Character> digits){
        String s = "";
        for(int i=0; i<digits.size(); i++)
            s+=digits.get(i);
        return Integer.parseInt(s);
    }
    public static int getEnd(ArrayList<Character> digits){
        String s = "";
        for(int i=digits.size()-1; i>=0; i--)
            s+=digits.get(i);
        return Integer.parseInt(s);
    }
}
