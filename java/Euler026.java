import java.util.*;
public class Euler026{

    private static int scale;
    
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Scale: ");
        scale=sc.nextInt();
        long start = System.currentTimeMillis();
        Map<String,String> list = makeMap();
        list = removeFinite(list);
        String key = longestChain(list);
        long end = System.currentTimeMillis();
        System.out.print(key + " ");
        System.out.println(list.get(key));
        printTime(end - start);
    }
    
    public static String longestChain(Map<String,String> list){
        int chainLength = 0;
        String targetKey = "";
        for(String key : list.keySet()){
            String decimal = list.get(key);
            int length = decimal.length();
            for(int i=1; i<(length/2)-1; i++){
                String b = decimal.substring(length-i, length);
                String a = decimal.substring(length-i-i, length-i);
                if(a.equals(b)){
                    if(a.length()>chainLength){
                        chainLength=a.length();
                        targetKey=key;
                    }                
                    break;
                }
            }
        }
        System.out.println("L = " + chainLength);
        return targetKey;   
    }
    
    public static Map<String,String> makeMap (){
        Map<String,String> list = new HashMap<String,String>();
        list.put("1/1", ""+1.0);
        for(int denom=2; denom<=scale; denom++){
            String decimal = "0.";
            int numer=1;
            int places =0;
            while(numer%denom!=0){
                places++;
                int zeroFinder = 0;
                while(numer/denom==0){
                    zeroFinder ++;
                    numer = Integer.parseInt(numer + "0");
                    if(zeroFinder>1)
                        decimal += 0;
                }
                //System.out.println(numer + "/" + denom + "=" + (numer/denom));
                decimal += numer/denom;
                numer = numer%denom;
                if(numer==0 || places==scale*2 || decimal.length()>scale*2)
                    break;
            }
            //System.out.println();
            list.put("1/" + denom, decimal);    
        }
        return list;
    }
    
    public static Map<String,String> removeFinite(Map<String,String> list){
        Map<String,String> list2 = new HashMap<String,String>();
        for(String key : list.keySet())
            if(list.get(key).length()>=scale*2)
                list2.put(key, list.get(key));
        return list2;
    }
    
    public static String printMap(Map<String,String> list){
        String s = "";
        for(String key : list.keySet()){
            s+= key;
            for(int i=key.length(); i<7; i++)
                s+= " ";
            s+= list.get(key) + "\n";
        }
        return s;
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
	    m%=60; s%=60; ms%=1000;
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
