import java.util.*;
public class Number{
    
    public int [] array;

    public Number(String s){
        this.array = new int[s.length()];
        for(int i=0; i<s.length(); i++)
            this.array[0-i+s.length()-1]=Integer.parseInt(""+s.charAt(i));
        s = this.toString();
        this.array = new int[s.length()];
        for(int i=0; i<s.length(); i++)
            this.array[0-i+s.length()-1]=Integer.parseInt(""+s.charAt(i));
        
    }
    
    private Number(int [] a){
        this.array=a;
    }
    
    public Number add(Number b){
        int maxLength = Math.max(this.array.length, b.array.length);
        int minLength = Math.min(this.array.length, b.array.length);
        int [] newNum = new int[maxLength+1];
        int carryover = 0;
        for(int i=0; i<minLength; i++){
            int n = this.array[i] + b.array[i]+carryover;
            carryover = n/10;
            newNum[i] = n%10;
        }
        if(!this.isLessThan(b)){
            for(int i=minLength; i<maxLength; i++){
                int n = this.array[i] + carryover;
                carryover = n/10;
                newNum[i] = n%10;
            }
        } else {
            for(int i=minLength; i<maxLength; i++){
                int n = b.array[i] + carryover;
                carryover = n/10;
                newNum[i] = n%10;
            }
        }
        newNum[newNum.length-1]=carryover;
        return new Number(new Number(newNum).toString());
    }
        
    public Number subtract(Number b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        int [] thisCopy = new int [thisSize];
        for(int i=0; i<thisSize; i++)
            thisCopy[i]=this.array[i];
        if(this.isLessThan(b)){
            System.out.println("Error: Answer Negative");
            System.exit(0);
        }
        int [] newNum = new int[thisSize];
        for(int i=0; i<bSize; i++){
            if(thisCopy[i]<0){
                thisCopy[i]=9;
                thisCopy[i+1]--;
            }
            if(thisCopy[i]<b.array[i]){
                thisCopy[i] += 10;
                thisCopy[i+1]--;
            }
            newNum[i] = thisCopy[i]-b.array[i];
        }
        for(int i=bSize; i<thisSize; i++)
            newNum[i]=thisCopy[i];
        return new Number(new Number(newNum).toString());
    }
    
    public Number multiply(Number b){
        int maxLength = Math.max(this.array.length, b.array.length);
        int minLength = Math.min(this.array.length, b.array.length);
        int [] newNum = new int[maxLength*2];
        int [] temp = new int[maxLength*2];

        if(maxLength == b.array.length){
            for(int x=0; x<minLength; x++){         //This.array
                int carryover = 0;
                int placeholder = 0;
                for(int i=0; i<x; i++)
                    placeholder++;
                for(int y=0; y<maxLength; y++){     //b.array
                    int n = (this.array[x]*b.array[y]) + carryover;
                    carryover = n/10;
                    temp[x+y] = n%10;
                }
                temp[maxLength+placeholder]=carryover;
                Number c = new Number(newNum);
                Number d = new Number(temp);
                newNum = c.add(d).array;
                temp = new int[maxLength*2];
            }            
        } else{            
            for(int x=0; x<minLength; x++){         //b.array
                int carryover = 0;
                int placeholder = 0;
                for(int i=0; i<x; i++)
                    placeholder++;
                for(int y=0; y<maxLength; y++){     //this.array
                    int n = (b.array[x]*this.array[y]) + carryover;
                    carryover = n/10;
                    temp[x+y] = n%10;
                }
                temp[maxLength+placeholder]=carryover;
                Number c = new Number(newNum);
                Number d = new Number(temp);
                newNum = c.add(d).array;
                temp = new int[maxLength*2];
            }            
        }
        return new Number(new Number(newNum).toString());
    }
    
    public Number divide(Number divisor){
        int thisSize = this.array.length;
        Number reverse = new Number(this.flip(this.toString()));
        String newNum = "";
        if(divisor.toString() == "0"){
            System.out.println("Error: Divide by Zero");
            System.exit(0);
        }
        if(this.isLessThan(divisor))
            return new Number("0");
        Number focus = new Number("0");
        for(int i=0; i<thisSize; i++){
            focus = new Number(focus.toString() + reverse.array[i]);
            if(divisor.isGreaterThan(focus)){
                newNum += "0";
            } else if(divisor.equals(focus)){
                newNum += "1";
                focus = new Number("0");
            } else { //divisor < focus
                int counter = 0;
                Number sum = new Number("0");
                while(sum.isLessThan(focus)){
                    sum = sum.add(divisor);
                    counter++;
                }
                counter--;                 //Reduced so now less than focus by 1
                sum = sum.subtract(divisor);  //  ""  ""  ""  ""  ""  ""  ""
                focus = focus.subtract(sum);
                newNum += counter;
            }
        }
        return new Number(newNum);
    }
    
    public Number mod(Number b){
        if(b.toString() == "0"){
            System.out.println("Error: Modulus by 0");
            System.exit(0);
        }
        return (this.subtract(this.divide(b).multiply(b)));
    }
    
    public Number pow(int power){
        if(power == 1)
            return this;
        if(power == 0)
            return new Number("0");
        if(power < 0){
            System.out.println("Error: Can't accomidate negative powers yet");
            System.exit(0);
        }
        return pow(power, this, true);
    }
    
    private Number pow(int power, Number b, boolean first){
        if(first){
            first=false;
            if(power == 2)
                return b.multiply(this);
            return pow(power-2, b.multiply(this), first);
        }
        if(power == 0)
            return b;
        return pow(power-1, b.multiply(this), first);
    }

    public boolean isGreaterThan(Number b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        if(bSize<thisSize)
            return true;
        if(thisSize<bSize)
            return false;
        for(int i=(thisSize-1); i>=0; i--){
            if(b.array[i]>this.array[i])
                return false;
            else if(b.array[i]<this.array[i])
                return true;
            //if equal, continue
        }
        return false; //if numbers are equal
    }
    
    public boolean isLessThan(Number b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        if(bSize>thisSize)
            return true;
        if(thisSize>bSize)
            return false;
        for(int i=(thisSize-1); i>=0; i--){
            if(b.array[i]>this.array[i])
                return true;
            else if(b.array[i]<this.array[i])
                return false;
            //if equal, continue
        }
        return false; //if numbers are equal
    }
    
    public boolean equals(Number b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        if(bSize != thisSize)
            return false;
        for(int i=(thisSize-1); i>=0; i--)
            if(b.array[i] != this.array[i])
                return false;
        return true; //if numbers are equal
    }
    
    public boolean even(){
        return (this.array[0]%2 == 0);
    }
    
    public Number add1(){
        return this.add(new Number("1"));
    }
    
    public Number sub1(){
        return this.subtract(new Number("1"));
    }
    
    private String flip(String s){
        String t = "";
        for(int i=s.length()-1; i>=0; i--)
            t += s.charAt(i);
        return t;
    }
    
    public String toString(){
        String s = "";
        for(int i=this.array.length-1; i>=0; i--)
            s += this.array[i];
        while(s.charAt(0) == '0'){
            if(s.length() == 1)
                return "0";
            s=s.substring(1);
        }
        return s;
    }
}
