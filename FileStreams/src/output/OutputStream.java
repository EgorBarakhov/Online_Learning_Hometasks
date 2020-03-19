package output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class OutputStream {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        char b = (char) sc.nextShort();
        sc.close();
        String chString = String.valueOf(b);
        String aString = Integer.toString(a);
        byte[] intBytes = aString.getBytes();
        byte[] chBytes = chString.getBytes();
        try(FileOutputStream output = new FileOutputStream("data.txt")){
         output.write(intBytes);
         output.write(chBytes);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}