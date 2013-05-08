import java.util.*;
import java.io.*;
import java.math.*;
public class Euler054{

    public static void main(String[] args) throws FileNotFoundException{
        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("poker.txt"));
        while(sc.hasNext()){
            String hand1 = "";
            String hand2 = "";
            for(int i=0; i<5; i++)
                hand1 += sc.next() + " ";
            for(int i=0; i<5; i++)
                hand2 += sc.next() + " ";
            System.out.print(Arrays.toString(new PokerHand(hand1).handStrength()) + "  ");
            System.out.println(Arrays.toString(new PokerHand(hand2).handStrength()));
        }
        long end = System.currentTimeMillis();
        printTime(end-start);
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
