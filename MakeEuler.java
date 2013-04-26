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
        main +="\tpublic static void main(String[] args){\n";
        main +="\t\tlong start = System.currentTimeMillis();\n";
        main +="\t\tlong end = System.currentTimeMillis();\n";
        main +="\t\tprintTime(end-start);\n";
        main +="\t}\n";
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
        method +="\tpublic static boolean isIntParseable(String s){\n";
        method +="\t\ttry{\n";
        method +="\t\t\tInteger.parseInt(s);\n";
        method +="\t\t\treturn true;\n";
        method +="\t\t} catch(Exception e){\n";
        method +="\t\t\rreturn false;\n";
        method +="\t\t}\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makePrimeMethod(){
        String method = "";
        method +="\tpublic static boolean isPrime(int x){\n";
        method +="\t\tfor(int i=2; i<=Math.sqrt(x); i++)\n";
        method +="\t\t\tif(x%i==0)\n";
        method +="\t\t\t\treturn false;\n";
        method +="\t\treturn true;\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makePalMethod(){
        String method = "";
        method +="\tpublic static boolean isPal(String s){\n";
        method +="\t\tString t = \"\";\n";
        method +="\t\tfor(int i=s.length()-1; i>=0; i--)\n";
        method +="\t\t\tt += s.charAt(i);\n";
        method +="\t\treturn s.equals(t);\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makeFibMethod(){
        String method = "";
        method +="\tpublic static boolean isFib(int x){\n";
        method +="\t\tArrayList<Integer>fibList = new ArrayList<Integer>();\n";
        method +="\t\tint i = 1;\n";
        method +="\t\tint j = 1;\n";
        method +="\t\tfibList.add(i);\n";
        method +="\t\tfibList.add(j);\n";
        method +="\t\tint sum = 0;\n";
        method +="\t\twhile(sum<x){\n";
        method +="\t\t\tsum = i+j;\n";
        method +="\t\t\tfibList.add(sum);\n";
        method +="\t\t\ti=j;\n";
        method +="\t\t\tj=sum;\n";
        method +="\t\t}\n";
        method +="\t\treturn fibList.contains(x);\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makePanMethod(){
        String method = "";
        method +="\tpublic static boolean isPan(int x, int scale){\n";
        method +="\t\t//to add 0, change the array size by +1 and remove -1 from marked lines\n";
        method +="\t\tString s = \"\" + x;\n";
        method +="\t\tif(s.length()!=scale)\n";
        method +="\t\t\treturn false;\n";
        method +="\t\tboolean [] digits = new boolean[scale];\n";
        method +="\t\tfor(int i=0; i<s.length(); i++){\n";
        method +="\t\t\tint y = Integer.parseInt(\"\" + s.charAt(i));\n";
        method +="\t\t\tif(y>scale || y==0) //Also remove the RHS\n";
        method +="\t\t\t\treturn false;\n";
        method +="\t\t\tif(digits[y-1]==true) //HERE\n";
        method +="\t\t\t\treturn false;\n";
        method +="\t\t\tdigits[y-1] = true; //HERE\n";
        method +="\t\t}\n";
        method +="\t\treturn true;\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makeFracMethod(){
        String method = "";
        method +="\tpublic static String getReducedFrac(int num, int den){\n";
        method +="\t\tmainLoop:\n";
        method +="\t\twhile(true){\n";
        method +="\t\t\tfor(int i=2; i<=num; i++){\n";
        method +="\t\t\t\tif(num%i==0 && den%i==0){\n";
        method +="\t\t\t\t\tnum /= i;\n";
        method +="\t\t\t\t\tden /= i;\n";
        method +="\t\t\t\t\tcontinue mainLoop;\n";
        method +="\t\t\t\t}\n";
        method +="\t\t\t}\n";
        method +="\t\t\tbreak;\n";
        method +="\t\t}\n";
        method +="\t\treturn num + \"/\" + den;\n";
        method +="\t}\n";
        return method;
    }
    
    public static String makeFactMethod(){
        String method = "";
        method +="\tpublic static long getFactorial(long l){\n";
        method +="\t\tlong x = 1;\n";
        method +="\t\tfor(long i=1; i<=l; i++)\n";
        method +="\t\t\tx *= i;\n";
        method +="\t\treturn x;\n";
        method +="\t}\n";
        return method;
    }
    
    public static String closure(){
        String closure = "\n";
        closure +="\tpublic static void printTime(long ms){\n";
        closure +="\t\tlong s=0, m=0, h=0;\n";
        closure +="\t\tif(ms>1000){\n";
        closure +="\t\t\ts = ms/1000;\n";
        closure +="\t\t\tif(s>60){\n";
        closure +="\t\t\t\tm = s/60;\n";
        closure +="\t\t\t\tif(m>60)\n";
        closure +="\t\t\t\t\th = m/60;\n";
        closure +="\t\t\t}\n";
        closure +="\t\t}\n";
        closure +="\t\tm%=60;\n";
        closure +="\t\ts%=60;\n";
        closure +="\t\tms%=1000;\n";
        closure +="\t\tString a=\"\"+m, b=\"\"+s, c=\"\"+ms;\n";
        closure +="\t\tif(a.length()!=2)\n";
        closure +="\t\t\ta = \"0\" + a;\n";
        closure +="\t\tif(b.length()!=2)\n";
        closure +="\t\t\tb = \"0\" + b;\n";
        closure +="\t\twhile(c.length()!=3)\n";
        closure +="\t\t\tc = \"0\" + c;\n";
        closure +="\t\tSystem.out.println(h + \":\" + a + \":\" + b + \".\" + c);\n";
        closure +="\t}\n";
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
