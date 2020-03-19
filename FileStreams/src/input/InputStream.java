package input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InputStream {
	public static void main(String[] args) {
	    File file = new File("/tmp/text.txt");
	    try(FileInputStream in = new FileInputStream(file)){
	      int integ = 0;
	      integ = integ | (int) in.read() << 24; 
	      integ = integ | (int) in.read() << 16;
	      integ = integ | (int) in.read() << 8;
	      integ = integ | (int) in.read();
	      char ch = 0;
	      ch = (char) ((char) ch | (short) in.read() << 8);
	      ch = (char) ((char) ch | (short) in.read());
	    }
	    catch(IOException e){
	      e.getMessage();
	    }
	  }
}
