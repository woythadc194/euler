/*
 * Author: Dylan Woythal
 */
import java.util.Scanner; 
public class PokerHand{

    private String hand;
    private int [] ary;
    
    public PokerHand(String hand){
        this.hand = hand;
        this.ary = getArray();
    }
    
    private int [] getArray(){
        int [] temp = new int[5];
        Scanner sc = new Scanner(hand);
        int counter = 0;
        while(sc.hasNext()){
            int x = 0;
            char c = sc.next().charAt(0);
            try{
                x = Integer.parseInt("" + c);
            } catch(Exception e){
                if(c=='J')
                    x = 11;
                else if(c=='Q')
                    x = 12;
                else if(c=='K')
                    x = 13;
                else if(c=='A')
                    x = 14;
            }
            temp[counter] = x;
            counter++;
        }
        return temp;
    }
    
    public String rank(){
        int rank = 0;
        if(royalFlush())
            rank = 1;
        else if(straightFlush())
            rank = 2;
        else if(fourOfAKind())
            rank = 3;
        else if(fullHouse())
            rank = 4;
        else if(flush())
            rank = 5;
        else if(straight())
            rank = 6;
        else if(threeOfAKind())
            rank = 7;
        else if(twoPair())
            rank = 8;
        else if(onePair())
            rank = 9;
        else
            rank = 10;
    }
    
    public String toString(){
        return hand;
    }
}
