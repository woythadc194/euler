import java.util.*;
import java.io.*;
import java.math.*;
public class Euler054{

    public static void main(String[] args) throws FileNotFoundException{
        long start = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("poker.txt"));
        int counter = 0;
        while(sc.hasNext()){
            String handa = "";
            String handb = "";
            for(int i=0; i<5; i++)
                handa += sc.next() + " ";
            for(int i=0; i<5; i++)
                handb += sc.next() + " ";
            PokerHand hand1 = new PokerHand(handa);
            PokerHand hand2 = new PokerHand(handb);
            int [] hand1a = hand1.bestHand();
            int [] hand2a = hand2.bestHand();
            for(int i=0; i<6; i++){
                if(hand1a[i] > hand2a[i]){
                    counter++;
                    break;
                } else if(hand1a[i] < hand2a[i]){
                    break;
                }
            }
        }
        System.out.println(counter);
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
