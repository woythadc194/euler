import java.util.*;
public class BigNumber{
    
    private int [] array;

    public BigNumber(String s){
        this.array = new int[s.length()];
        for(int i=0; i<s.length(); i++)
            this.array[0-i+s.length()-1]=Integer.parseInt(""+s.charAt(i));
    }
    
    private BigNumber(int [] a){
        this.array=a;
    }
    
    public BigNumber subtract(BigNumber b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        if((bSize>thisSize) || ((bSize==thisSize)&&isGreater(b))){
            System.out.println("Error: Answer Negative");
            System.exit(0);
        }
        int [] newNum = new int[thisSize];
        for(int i=0; i<bSize; i++){
            int x = this.array[i];
            int y = b.array[i];
            if(x<y){
                int offset = 1;
                x += 10;
                while(this.array[i+offset] == 0){
                    this.array[i+offset] = 9;
                    offset++;
                }
                this.array[i+offset]--;
            }
            newNum[i] = x-y;
        }
        for(int i=bSize; i<thisSize; i++)
            newNum[i]=this.array[i];
        return new BigNumber(newNum);
    }
    
    private boolean isGreater(BigNumber b){
        int thisSize = this.array.length;
        int bSize = b.array.length;
        if(bSize>thisSize)
            return true;
        if(thisSize>bSize)
            return false;
        for(int i=thisSize; i>=0; i++){
            if(b.array[i]>this.array[i])
                return true;
            else if(b.array[i]<this.array[i])
                return false;
            //if equal, continue
        }
        return false;
    }
    
    public BigNumber add(BigNumber b){
        int maxLength = Math.max(this.array.length, b.array.length);
        int minLength = Math.min(this.array.length, b.array.length);
        int [] newNum = new int[maxLength+1];
        int carryover = 0;
        for(int i=0; i<minLength; i++){
            int n = this.array[i] + b.array[i]+carryover;
            carryover = n/10;
            newNum[i] = n%10;
        }
        if(maxLength == b.array.length){
            for(int i=minLength; i<maxLength; i++){
                int n = b.array[i] + carryover;
                carryover = n/10;
                newNum[i] = n%10;
            }            
        } else {
            for(int i=minLength; i<maxLength; i++){
                int n = this.array[i] + carryover;
                carryover = n/10;
                newNum[i] = n%10;
            }
        }
        newNum[newNum.length-1]=carryover;
        return new BigNumber(newNum);
    }
    
    public BigNumber multiply(BigNumber b){
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
                BigNumber d = new BigNumber(newNum);
                BigNumber v = new BigNumber(temp);
                newNum = d.add(v).array;
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
                BigNumber d = new BigNumber(newNum);
                BigNumber v = new BigNumber(temp);
                newNum = d.add(v).array;
                temp = new int[maxLength*2];
            }            
        }
        return new BigNumber(newNum);
    }
    
    public BigNumber pow(int power){
        if(power == 1)
            return this;
        if(power == 0)
            return new BigNumber("0");
        return pow(power, this, true);
    }
    
    private BigNumber pow(int power, BigNumber b, boolean first){
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
