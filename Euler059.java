import java.util.*;
import java.io.*;
import java.math.*;
public class Euler59{

	public static void main(String[] args) throws FileNotFoundException {

		long start = System.currentTimeMillis();

		Scanner sc = new Scanner(new File("cipher1.txt"));
		String line = sc.nextLine();
		line = line.replace(',',' ');
		sc = new Scanner(line);
		
		Random rand = new Random();
        int key1 = 103;
        int key2 = 111;
        int key3 = 100;
        int counter = 1;
        int sum = 0;
        while(sc.hasNext()){
            int key;
            if(counter==4)
                counter=1;
            int xOr = Integer.parseInt("" + sc.next());
            if(counter == 1)
                key = key1;
            else if(counter == 2)
                key = key2;
            else
                key = key3;
        
            System.out.print((char)(xOr^key));
            sum += (xOr^key);
            counter++;
        }
        System.out.println("\n" + sum);

		long end = System.currentTimeMillis();
		printTime(end-start);
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
