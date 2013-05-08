/*
 * Author: Dylan Woythal
 */
 
public class PokerHand{

    private String hand;
    private String [] ary;
    
    public PokerHand(String hand){
        this.hand = hand;
        this.ary = getArray();
    }
    
    private String[] getArray(){
        String [] temp = new String[5];
        Scanner sc = new Scanner(hand);
        int counter = 0;
        while(sc.hasNext()){
            temp[counter] = sc.next();
            counter++;
        }
        return temp;
    }
    
    public String rank(){
        String rank = "";
        if(royalFlush())
            rank += "1 ";
        else if(straightFlush())
            rank += "2 ";
        else if(fourOfAKind())
            rank += "3 ";
        else if(fullHouse())
            rank += "4 ";
        else if(flush())
            rank += "5 ";
        else if(straight())
            rank += "6 ";
        else if(threeOfAKind())
            rank += "7 ";
        else if(twoPair())
            rank += "8 ";
        else if(onePair())
            rank += "9 ";
        else
            rank += "10 ";
    }
    
    public String toString(){
        return hand;
    }
}
