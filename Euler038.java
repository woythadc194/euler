import java.util.*;
public class Euler038{
    public static void main(String[] args){
        int number = 2;
        while(true){
            ArrayList<Boolean> digits = new ArrayList<Boolean>();
            for(int i=1; i<=9; i++)
                digits.add(false);
            ArrayList<Integer> products = new ArrayList<Integer>();
            int counter = 1;
            
            mainloop:
            while(digits.contains(false)){
                String prod = counter*number + "";
                for(int i=0; i<prod.length(); i++){
                    int index = Integer.parseInt("" + prod.charAt(i));
                    if(index == 0){
                        break mainloop;
                    }
                    if(digits.get(index-1)==false){
                        digits.set(index - 1, true);
                    } else{
                        break mainloop;
                    }
                }
                products.add(Integer.parseInt(prod));
                counter++;
            }
            
            if(digits.contains(false)){
                number++;
                if(number>10000)
                    System.exit(0);
                continue;
            }
            String finaL = "";
            for(int i=0; i<products.size(); i++){
                finaL += "" + products.get(i);
            }
            System.out.println(finaL);
            number++;
            if(number>10000)
                System.exit(0);
        }
    }
}







