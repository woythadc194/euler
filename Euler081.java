//FIXME
import java.util.*;
import java.io.*;
public class Euler81{

    public static void main(String[] args) throws FileNotFoundException{
        int [][] matrix = makeMatrix();
        int sum = getSum(matrix[79][79], 79, 79, matrix);
        System.out.println(sum);
    }
    
    public static int getSum(int sum, int i, int j, int [][] matrix){
        if(i==0 && j==0)
            return sum;
        if(i==0)
            return getSum(sum+matrix[i][j-1], i, j-1, matrix);
        if(j==0)
            return getSum(sum+matrix[i-1][j], i-1, j, matrix);
        int a = matrix[i][j-1];
        int b = matrix[i-1][j];
        if(a>b)
            return getSum(sum+matrix[i][j-1], i, j-1, matrix);
        if(b>a)
            return getSum(sum+matrix[i-1][j], i-1, j, matrix);
        return 0;
    }
    
    public static int[][] makeMatrix() throws FileNotFoundException{
        int [][] matrix = new int [80][80];
        Scanner fileSc = new Scanner(new File("matrix.txt"));
        for(int i=0; i<80; i++){
            String line = fileSc.next().replace(',',' ');
            Scanner sc = new Scanner(line);
            for(int j=0; j<80; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }
}
