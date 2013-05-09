/*
 * Author: Dylan Woythal
 */
 
import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;
public class PokerHand{

    private String s;
    private int [] hand;       // size == 5
    private int [] cardOccur; // size == 15
    private int [] bestHand; // size == 6
    
    public PokerHand(String s){
        this.s = s;
        this.hand = getArray();
        this.cardOccur = getOccur();
        this.bestHand = getBestHand();
    }
    
//////////////////////////////////
///    constructor methods     ///
//////////////////////////////////

    
    private int[] getOccur(){
        int [] temp = new int [15];
        for(int card=0; card<5; card++)
            temp[hand[card]]++;
        return temp;
    }
    
    private int [] getArray(){
        int [] temp = new int[5];
        Scanner sc = new Scanner(this.s);
        int card = 0;
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
            temp[card] = x;
            card++;
        }
        Arrays.sort(temp);
        return temp;
    }

    private int[] getBestHand(){
        int [] temp = new int [6];
        temp[0] = rank();
        for(int i=0; i<5; i++)
            temp[i+1] = hand[i];
        return temp;
    }
    
    private int rank(){
        int rank = 0;
        if(royalFlush()){
            rank = 10; highCard();
        }else if(straightFlush()){
            rank = 9; highCard();
        }else if(fourOfAKind()){
            rank = 8; get4Kind();
        }else if(fullHouse()){
            rank = 7; getFullHouse();
        }else if(flush()){
            rank = 6; highCard(); 
        }else if(straight()){
            rank = 5; highCard();
        }else if(threeOfAKind()){
            rank = 4; get3Kind();
        }else if(twoPair()){
            rank = 3; get2Pair();
        }else if(onePair()){
            rank = 2; get1Pair();
        }else{
            rank = 1; highCard();
        }
        return rank;
    }
    
    
//////////////////////////////////
///      sorting of cards      ///
//////////////////////////////////

    
    private void highCard(){
        int[] temp = new int[5];
        for(int x=0; x<5; x++)
            temp[x] = hand[0-x+4];
        hand = temp;
    }
    
    private void get4Kind(){
        for(int index=0; index<14; index++){
            if(cardOccur[index]==4)
                for(int card=0; card<4; card++)
                    hand[card] = index;
            else if(cardOccur[index]!=0)
                hand[4]=index;
        }
    }
    
    private void getFullHouse(){
        for(int index=0; index<14; index++)
            if(cardOccur[index]==3){
                hand[0]=index; 
                hand[1]=index; 
                hand[2]=index;
            } else if(cardOccur[index]==2){
                hand[3]=index+1; 
                hand[4]=index+1;
            }
    }
    
    private void get3Kind(){
        hand[3]=0; hand[4]=0;
        for(int index=14; index>0; index--){
            if(cardOccur[index]==3){
                hand[0]=index; 
                hand[1]=index; 
                hand[2]=index;
            }else if(cardOccur[index]!=0)
                if(hand[3]==0)
                    hand[3]=index+1;
                else
                    hand[4]=index+1;
        }
    }
    
    private void get2Pair(){
        int [] temp = new int[5];
        for(int index=14; index>0; index--){
            if(cardOccur[index]==2)
                if(temp[0]==0){
                    temp[0]=index;
                    temp[1]=index;
                }else{
                    temp[2]=index;
                    temp[3]=index;
                }
            else
                temp[4]=index;
        }
        hand=temp;
    }
    
    private void get1Pair(){
        int [] temp = new int[5];
        for(int index=14; index>0; index--){
            if(cardOccur[index]==2){
                temp[0]=index;
                temp[1]=index;
            }else if(cardOccur[index]==1){
                if(temp[2]==0)
                    temp[2]=index;
                else if(temp[3]==0)
                    temp[3]=index;
                else
                    temp[4]=index;
            }
        }
        hand=temp;
    }

//////////////////////////////////
///       Boolean tests        ///
//////////////////////////////////
    
    private boolean royalFlush(){
        if(!flush())
            return false;
        for(int i=10; i<15; i++)
            if(hand[i-10]!=i)
                return false;
        return true;
    }
    
    private boolean straightFlush(){
        return(flush() && straight());
    }
    
    private boolean fourOfAKind(){
        for(int x : cardOccur)
            if(x==4){
                return true;
            }
        return false;
    }
    
    private boolean fullHouse(){
        int [] cardOccur = new int [14];
        for(int i=0; i<5; i++)
            cardOccur[hand[i]-1]++;
        boolean three = false;
        boolean two = false;
        for(int x : cardOccur)
            if(x==3)
                three = true;
            else if(x==2)
                two = true;
        return (two && three);
    }
    
    private boolean flush(){
        Scanner sc = new Scanner(this.s);
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
        int card = hand[0];
        for(int i=1; i<5; i++){
            int card2 = hand[i];
            if(card2!=card+1)
                return false;
            card = card2;
        }
        return true;
    }
    
    private boolean threeOfAKind(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[hand[i]-1]++;
        for(int x=0; x<14; x++)
            if(temp[x]==3)
                return true;
        return false;
    }
 
    private boolean twoPair(){
        int [] temp = new int [14];
        for(int i=0; i<5; i++)
            temp[hand[i]-1]++;
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
            temp[hand[i]-1]++;
        for(int x : temp)
            if(x==2)
                return true;
        return false;
    }   
    
//////////////////////////////////
///       public returns       ///
//////////////////////////////////
    
    
    public int[] bestHand(){
        return this.bestHand;
    }
    
    public String bestHandString(){
        int rank = bestHand[0];

        if(rank == 10)
            return "Royal Flush";
        else if(rank == 9)
            return "Straight Flush";
        else if(rank == 8)
            return "Four Of A Kind";
        else if(rank == 7)
            return "Full House";
        else if(rank == 6)
            return "Flush";
        else if(rank == 5)
            return "Straight";
        else if(rank == 4)
            return "Three Of A Kind";
        else if(rank == 3)
            return "Two Pair";
        else if(rank == 2)
            return "One Pair";
        else
            return "High Card";
    }
       
    public String toString(){
        return Arrays.toString(this.hand);
    }
}
