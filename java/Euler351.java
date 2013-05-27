/* Author: Dylan_W
 * CompSci Major at SUNY Potsdam
 * Date: 4/1/13
 */
public class Euler351{

    private static int [] hiddenList;                 //layer match to index; hidden pegs at that layer
    private static long totalHidden = 0;              //total hidden pegs of the layers
    public static final int LIMIT = 100000000;

    public static void main(String[]args){

        long start = System.currentTimeMillis();      //Start Time
        hiddenList = new int[LIMIT+1];                //+1 because of 0-Indexing
        for(int i=2; i<=LIMIT; i++)                   //Start at 2 because 1 doesnt have any hidden
            getPegsViz(i);
            
        long end = System.currentTimeMillis();        //End Time
        System.out.println(totalHidden*6);            //print total*6(for each triangle)
        System.out.println(end - start + "ms");       //Print Time Elapsed
    }
    
    public static void getPegsViz(int x){
        hiddenList[x]+=1;                             //one row where one is always hidden
        int multLayer = x*2;                          //first layer with factor of x
        for(; multLayer<=LIMIT; multLayer+=x){        //for all this rings multiples
            hiddenList[multLayer] += x-hiddenList[x]; //the visible pegs of this ring are added to 
                                                      //    the hidden pegs of this rings multiples
        }
        totalHidden += hiddenList[x];                 //add the hidden pegs of this ring to total
    }
}
