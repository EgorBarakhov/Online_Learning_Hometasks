package ru.kpfu.itis.barakhov;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class IniFileReader extends InputStream {
    private InputStreamReader inputStream;

    public IniFileReader(InputStream inputStream) {
        this.inputStream = new InputStreamReader(inputStream);
    }

    public String readIni(String section, String key) throws IOException, NoSuchFieldException {
        BufferedReader reader = new BufferedReader(inputStream);
        String line = reader.readLine();
        while(line != null){
            if (line.trim().equals(section.trim())) {
                System.out.println(key);
                System.out.print(line + "");
                line = reader.readLine();
                while (!line.matches("\\s*\\[([^]]*)\\]\\s*")){
                    String [] words = line.split("=", 2);
                    if(words[0].equals(key)) return words[1];
                    line = reader.readLine();
                }
                return "Not Found";
            }
            line = reader.readLine();
        }
        return "Not Found";
    }

    public String getEncoding() {
        return inputStream.getEncoding();
    }

    public int read(char[] cbuf, int offset, int length) throws IOException {
        return inputStream.read(cbuf, offset, length);
    }

    public boolean ready() throws IOException {
        return inputStream.ready();
    }

    public int read(CharBuffer target) throws IOException {
        return inputStream.read(target);
    }

    public int read(char[] cbuf) throws IOException {
        return inputStream.read(cbuf);
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }
}