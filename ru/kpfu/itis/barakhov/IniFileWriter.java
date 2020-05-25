package ru.kpfu.itis.barakhov;

import java.io.*;

public class IniFileWriter extends OutputStream {
    private DataOutputStream outputStream;


    public IniFileWriter( OutputStream outputStream) throws IOException {
        this.outputStream = new DataOutputStream(outputStream);


    }

    public void writeIni(String section, String [] key_value, File file) throws IOException {
        String searchWord = key_value[0];
        String changeWord = key_value[1];
        StringBuilder sb = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                stringBuffer.append(strLine);
                if (strLine.equals(section)) {
                    strLine = br.readLine();
                    while (!strLine.matches("\\s*\\[([^]]*)\\]\\s*")){
                        String [] words = strLine.split("=", 2);
                        if(words[0].equals(key_value[0])) {
                            sb.append(strLine.replace(searchWord, key_value[1])).append("\r\n");
                            stringBuffer.append(sb);
                            continue;
                        }
                        stringBuffer.append(strLine);
                        strLine = br.readLine();
                    }
                }
                stringBuffer.append(strLine);

            }
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(stringBuffer.toString());
                fileWriter.flush();
                fileWriter.close();
            }
        }


    }





    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    public void writeBoolean(boolean v) throws IOException {
        outputStream.writeBoolean(v);
    }

    public void writeByte(int v) throws IOException {
        outputStream.writeByte(v);
    }

    public void writeShort(int v) throws IOException {
        outputStream.writeShort(v);
    }

    public void writeChar(int v) throws IOException {
        outputStream.writeChar(v);
    }

    public void writeInt(int v) throws IOException {
        outputStream.writeInt(v);
    }

    public void writeLong(long v) throws IOException {
        outputStream.writeLong(v);
    }

    public void writeFloat(float v) throws IOException {
        outputStream.writeFloat(v);
    }

    public void writeDouble(double v) throws IOException {
        outputStream.writeDouble(v);
    }

    public void writeBytes(String s) throws IOException {
        outputStream.writeBytes(s);
    }

    public void writeChars(String s) throws IOException {
        outputStream.writeChars(s);
    }

    public void writeUTF(String str) throws IOException {
        outputStream.writeUTF(str);
    }

    public int size() {
        return outputStream.size();
    }
}