/*
 * Author: Dylan Woythal
 */
 
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
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
                if(c=='T')
                    x = 10;
                else if(c=='J')
                    x = 11;
                else if(c=='Q')
                    x = 12;
                else if(c=='K')
                    x = 13;
                else //if(c=='A')
                    x = 14;
            }
            temp[counter] = x;
            counter++;
        }
        Arrays.sort(temp);
        return temp;
    }

    public int[] handStrength(){
        int [] strength = new int [6];
        strength[0] = rank();
        for(int i=0; i<5; i++)
            strength[i+1] = ary[i];
        return strength;
    }
    
    private int rank(){
        int rank = 0;
        if(royalFlush())
            rank = 10;
        else if(straightFlush())
            rank = 9;
        else if(fourOfAKind())
            rank = 8;
        else if(fullHouse())
            rank = 7;
        else if(flush())
            rank = 6; 
        else if(straight())
            rank = 5;
        else if(threeOfAKind())
            rank = 4;
        else if(twoPair())
            rank = 3;
        else if(onePair())
            rank = 2;
        else{
            rank = 1;
            int[] temp = new int[5];
            for(int x=0; x<5; x++)
                temp[x] = ary[0-x+4];
            ary = temp;
        }
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
        int[] temp = new int[5];
        for(int x=0; x<5; x++)
            temp[x] = ary[0-x+4];
        ary = temp;
        return (flush() && straight());
    }
    
    private boolean fourOfAKind(){
        int [] cardOccur = new int [14];
        for(int i=0; i<5; i++)
            cardOccur[ary[i]-1]++;
        for(int x : cardOccur)
            if(x==4){
                for(int i=1; i<14; i++)
                    if(cardOccur[i]==4)
                        for(int j=0;j<4; j++)
                            ary[j] = i;
                    else if(temp[i]!=0)
                        ary[4]=i;
                return true;
            }
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
    
    private boolean flush(){
        Scanner sc = new Scanner(this.hand);
        String card = sc.next();
        char suit = card.charAt(card.length()-1);
        while(sc.hasNext()){
            card = sc.next();
            if(card.charAt(card.length()-1) != suit)
                return false;
        }
        return true;
    }
    
    private boolean straight(){
        int card = ary[0];
        for(int i=1; i<5; i++){
            int card2 = ary[i];
            if(card2!=card+1)
                return false;
            card = card2;
        }
        return true;
    }
    
    private boolean threeOfAKind(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[ary[i]-1]++;
        for(int x : temp)
            if(x==3)
                return true;
        return false;
    }
 
    private boolean twoPair(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[ary[i]-1]++;
        boolean a = false;
        boolean b = false;
        for(int x : temp)
            if(x==2 && !a)
                a = true;
            else if(x==2)
                b = true;
        return (a && b);        
    }   
    
    private boolean onePair(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[ary[i]-1]++;
        for(int x : temp)
            if(x==2)
                return true;
        return false;
    }   
       
    public String toString(){
        return this.hand;
    }
}
