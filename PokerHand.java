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
        Scanner sc = new Scanner(this.hand);
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
        Collections.sort(temp);
        return temp;
    }
    
    public int rank(){
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
        return rank;
    }
    
    private boolean royalFlush(){
        if(!flush())
            return false;
        for(int i=10; i<15; i++)
            if(ary[i-10]!=i)
                return false;
        return true;
    }
    
    private boolean straightFlush(){
        if(!flush())
            return false;
        int card = ary[0];
        for(int i=1; i<5; i++){
            card2 = ary[i];
            if(card2!=card+1)
                return false;
            card = card2;
        }
        return true;
    }
    
    private boolean fourOfAKind(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[ary[i]-1]++;
        for(int x : temp)
            if(x==4)
                return true;
        return false;
    }
    
    private boolean fullHouse(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[ary[i]-1]++;
        boolean three = false;
        boolean two = false;
        for(int x : temp)
            if(x==3)
                three = true;
            else if(x==2)
                two = true;
        return (three && two);
    }
    
    public String toString(){
        return this.hand;
    }
}
