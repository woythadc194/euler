import java.util.*;
import java.io.*;
public class MakeEuler{

    public static void main(String[]args) throws FileNotFoundException{
        String fileName = getProblem();
        System.out.println("\n\"" + fileName + "\"\n");
        PrintStream ps = new PrintStream(fileName);
        ps.print(header(fileName));
        ps.print(main());
        ps.print(methods());
        ps.print(closure());
    }
    
    public static String getProblem(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Euler problem (int): ");
        String num = sc.next();
        while(num.length()<3)
            num = "0" + num;
        String fileName = "Euler" + num + ".java";
        File f = new File("/home/woythadc194/Documents/" + fileName);
        while(f.exists()) {
            System.out.println("Sorry, that file already exits, writing to it will erase data");
            System.out.print("Enter Euler problem (int): ");
            num = sc.next();
            while(num.length()<3)
                num = "0" + num;
            fileName = "Euler" + num + ".java";
            f = new File("/home/woythadc194/Documents/" + fileName);    
        }
        return fileName;
    }
    
    public static String header(String fileName){
        String header = "";
        header +="import java.util.*;\nimport java.io.*;\nimport java.math.*;";
        header +="\npublic class " 
                    + fileName.substring(0,fileName.indexOf('.')) + "{\n\n";
        return header;
    }
    
    public static String main(){
        String main = "";
        main +="    public static void main(String[] args){\n";
        main +="        long start = System.currentTimeMillis();\n";
        main +="        long end = System.currentTimeMillis();\n";
        main +="        printTime(end-start);\n";
        main +="    }\n";
        return main;
    }
    
    public static String methods(){
        String body = "";
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter index of methods needed: ");
        System.out.println("1: isPrime");
        System.out.println("2: isPalindromic");
        System.out.println("3: isPandig");
        System.out.println("4: isFib");
        System.out.println("5: isIntParseable");
        System.out.println("6: getReducedFrac");
        System.out.println("7: getFactorial");
        System.out.println("0: EXIT");
        System.out.print("Index: ");
        String s = sc.next();
        boolean loop = true;
        while(loop){ //true
            while(!isParseable(s)){ //if not parseable
                System.out.print("Incorrect input\nIndex: ");
                s = sc.next();   
            }//now parsable
            if(Integer.parseInt(s)>=8){ //if int not an index
                System.out.print("Incorrect input\nIndex: ");
                s = sc.next(); 
            } else {// int now an index
                int x=Integer.parseInt(s);
                if(x==0){ 
                    loop=false;
                    continue;
                } else if(list.contains(x)) { //not 0 but already added
                    System.out.println("//" + x);
                    System.out.print("Already added.\nIndex: ");
                    s = sc.next();
                    continue;
                } else{ // not 0 and not added
                    if(x==1){
                        list.add(x);
                        body+="\n" + makePrimeMethod();
                    } else if(x==2){
                        list.add(x);
                        body+="\n" + makePalMethod();
                    } else if(x==3){
                        list.add(x);
                        body+="\n" + makePanMethod();
                    } else if(x==4){
                        list.add(x);
                        body+="\n" + makeFibMethod();
                    } else if(x==5){
                        list.add(x);
                        body+="\n" + makeParseMethod();
                    } else if(x==6){
                        list.add(x);
                        body+="\n" + makeFracMethod();
                    } else if(x==7){
                        list.add(x);
                        body+="\n" + makeFactMethod();
                    }
                }
                System.out.println("Added " + x);
                System.out.print("Index: ");
                s = sc.next();    
            }
        }
        return body;
    }

    public static boolean isParseable(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    public static String makeParseMethod(){
        String method = "";
        method +="    public static boolean isIntParseable(String s){\n";
        method +="        try{\n";
        method +="            Integer.parseInt(s);\n";
        method +="            return true;\n";
        method +="        } catch(Exception e){\n";
        method +="        \rreturn false;\n";
        method +="        }\n";
        method +="    }\n";
        return method;
    }
    
    public static String makePrimeMethod(){
        String method = "";
        method +="    public static boolean isPrime(int x){\n";
        method +="        for(int i=2; i<=Math.sqrt(x); i++)\n";
        method +="            if(x%i==0)\n";
        method +="                return false;\n";
        method +="        return true;\n";
        method +="    }\n";
        return method;
    }
    
    public static String makePalMethod(){
        String method = "";
        method +="    public static boolean isPal(String s){\n";
        method +="        String t = \"\";\n";
        method +="        for(int i=s.length()-1; i>=0; i--)\n";
        method +="            t += s.charAt(i);\n";
        method +="        return s.equals(t);\n";
        method +="    }\n";
        return method;
    }
    
    public static String makeFibMethod(){
        String method = "";
        method +="    public static boolean isFib(int x){\n";
        method +="        ArrayList<Integer>fibList = new ArrayList<Integer>();\n";
        method +="        int i = 1;\n";
        method +="        int j = 1;\n";
        method +="        fibList.add(i);\n";
        method +="        fibList.add(j);\n";
        method +="        int sum = 0;\n";
        method +="        while(sum<x){\n";
        method +="            sum = i+j;\n";
        method +="            fibList.add(sum);\n";
        method +="            i=j;\n";
        method +="            j=sum;\n";
        method +="        }\n";
        method +="        return fibList.contains(x);\n";
        method +="    }\n";
        return method;
    }
    
    public static String makePanMethod(){
        String method = "";
        method +="    public static boolean isPan(int x, int scale){\n";
        method +="        //to add 0, change the array size by +1 and remove -1 from marked lines\n";
        method +="        String s = \"\" + x;\n";
        method +="        if(s.length()!=scale)\n";
        method +="            return false;\n";
        method +="        boolean [] digits = new boolean[scale];\n";
        method +="        for(int i=0; i<s.length(); i++){\n";
        method +="            int y = Integer.parseInt(\"\" + s.charAt(i));\n";
        method +="            if(y>scale || y==0) //Also remove the RHS\n";
        method +="                return false;\n";
        method +="            if(digits[y-1]==true) //HERE\n";
        method +="                return false;\n";
        method +="            digits[y-1] = true; //HERE\n";
        method +="        }\n";
        method +="        return true;\n";
        method +="    }\n";
        return method;
    }
    
    public static String makeFracMethod(){
        String method = "";
        method +="    public static String getReducedFrac(int num, int den){\n";
        method +="        mainLoop:\n";
        method +="        while(true){\n";
        method +="            for(int i=2; i<=num; i++){\n";
        method +="                if(num%i==0 && den%i==0){\n";
        method +="                    num /= i;\n";
        method +="                    den /= i;\n";
        method +="                    continue mainLoop;\n";
        method +="                }\n";
        method +="            }\n";
        method +="            break;\n";
        method +="        }\n";
        method +="        return num + \"/\" + den;\n";
        method +="    }\n";
        return method;
    }
    
    public static String makeFactMethod(){
        String method = "";
        method +="    public static long getFactorial(long l){\n";
        method +="        long x = 1;\n";
        method +="        for(long i=1; i<=l; i++)\n";
        method +="            x *= i;\n";
        method +="        return x;\n";
        method +="    }\n";
        return method;
    }
    
    public static String closure(){
        String closure = "\n";
        closure +="    public static void printTime(long ms){\n";
        closure +="        long s=0, m=0, h=0;\n";
        closure +="        if(ms>1000){\n";
        closure +="            s = ms/1000;\n";
        closure +="            if(s>60){\n";
        closure +="                m = s/60;\n";
        closure +="                if(m>60)\n";
        closure +="                    h = m/60;\n";
        closure +="            }\n";
        closure +="        }\n";
        closure +="        m%=60;\n";
        closure +="        s%=60;\n";
        closure +="        ms%=1000;\n";
        closure +="        String a=\"\"+m, b=\"\"+s, c=\"\"+ms;\n";
        closure +="        if(a.length()!=2)\n";
        closure +="            a = \"0\" + a;\n";
        closure +="        if(b.length()!=2)\n";
        closure +="            b = \"0\" + b;\n";
        closure +="        while(c.length()!=3)\n";
        closure +="            c = \"0\" + c;\n";
        closure +="        System.out.println(h + \":\" + a + \":\" + b + \".\" + c);\n";
        closure +="    }\n";
        closure +="}\n";
        return closure;
    }
    
    public static boolean isIntParseable(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
