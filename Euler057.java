import java.math.BigInteger;
public class Euler57{

    private static int counter = 0;
    
    public static void main(String[] args){
        long start = System.currentTimeMillis();
        for(int i=1; i<=1000; i++)
            getExp(i);
        System.out.println(counter);
        long end = System.currentTimeMillis();
		printTime(end-start);
	}
	
	public static void getExp(int expansion){
        BigInteger aNum = new BigInteger(""+1);
        BigInteger aDem = new BigInteger(""+1);
        BigInteger bNum = new BigInteger(""+1);
        BigInteger bDem = new BigInteger(""+2);
        BigInteger hold = new BigInteger(""+0);
        for(int i=1; i<expansion; i++){
            bNum = bNum.add(bDem.multiply(new BigInteger(""+2)));//Add 2
                                        //Skip
            hold = bNum;                //Flip
            bNum = bDem;
            bDem = hold;
            bNum = bNum.multiply(aNum); //Multiply
            bDem = bDem.multiply(aDem);          
        } 
        bNum = bNum.add(bDem);          //Add final 1
        
        if(bNum.toString().length() >bDem.toString().length())
            counter++;
        //System.out.println(bNum + " / " + bDem);
            
	}

    public static void printTime(long ms){
        long s=0, m=0, h=0;
        if(ms>1000){
            s = ms/1000;
            if(s>60){
                m = s/60;
                if(m>60)
                    h = m/60;
            }
        }
        m%=60;
        s%=60;
        ms%=1000;
        String a=""+m, b=""+s, c=""+ms;
        if(a.length()!=2)
            a = "0" + a;
        if(b.length()!=2)
            b = "0" + b;
        while(c.length()!=3)
            c = "0" + c;
        System.out.println(h + ":" + a + ":" + b + "." + c);
    }
}
