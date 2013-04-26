import java.util.*;
import java.io.*;
public class Euler89{
    
    private static ArrayList<Integer> intList = new ArrayList<Integer>();
    private static ArrayList<String> romList = new ArrayList<String>();
    private static int oldSum = 0;
    private static int newSum = 0;
    
    public static void main(String[]args) throws FileNotFoundException{
        Scanner fileSc = new Scanner(new File("roman.txt"));
        while(fileSc.hasNext())
            decode(fileSc.next());
        System.out.println(intList);
        for(int num : intList)
            writeBest(num);
        System.out.println(romList);
        for(String newRom : romList)
            newSum += newRom.length();
        System.out.println("Char saved: " + (oldSum-newSum));
    }
    
    public static void writeBest(int num){
        int k = num/1000%10, h=num/100%10, t=num/10%10, o=num/1%10;
        String roman = "";
        //Thousands
        for(int i=0; i<k; i++)
            roman+="M";
        //Hundreds
        if(h==9)
            roman+="CM";
        else if(h==4)
            roman+="CD";
        else if(h>=5){
            roman+="D";
            for(int i=0; i<h-5; i++)
                roman+="C";
        }else
            for(int i=0; i<h; i++)
                roman+="C";
        //tens
        if(t==9)
            roman+="XC";
        else if(t==4)
            roman+="XL";
        else if(t>=5){
            roman+="L";
            for(int i=0; i<t-5; i++)
                roman+="X";
        }else
            for(int i=0; i<t; i++)
                roman+="X";
        //tens
        if(o==9)
            roman+="IX";
        else if(o==4)
            roman+="IV";
        else if(o>=5){
            roman+="V";
            for(int i=0; i<o-5; i++)
                roman+="I";
        }else
            for(int i=0; i<o; i++)
                roman+="I";
        romList.add(roman);
    }
    
    public static void decode(String oldRom){
        ArrayList<Integer> digits = new ArrayList<Integer>();
        for(int i=0; i<oldRom.length(); i++){
            oldSum++;
            if(oldRom.charAt(i)=='I')
                digits.add(1);  
            if(oldRom.charAt(i)=='V')
                digits.add(5);  
            if(oldRom.charAt(i)=='X')
                digits.add(10);  
            if(oldRom.charAt(i)=='L')
                digits.add(50);  
            if(oldRom.charAt(i)=='C')
                digits.add(100);  
            if(oldRom.charAt(i)=='D')
                digits.add(500);  
            if(oldRom.charAt(i)=='M')
                digits.add(1000);
            int size = digits.size(); 
            if(size>1 && digits.get(size-1)>digits.get(size-2))
                digits.set(size-2, 0-digits.get(size-2));
        }
        int num = 0;
        for(int x : digits)
            num+=x;
        intList.add(num);
    }    
}
