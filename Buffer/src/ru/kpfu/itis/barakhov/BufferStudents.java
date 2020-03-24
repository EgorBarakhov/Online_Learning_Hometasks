package ru.kpfu.itis.barakhov;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class BufferStudents {
	private final static int TEST_SIZE = 2048;
	
	public static void main(String[] args) {
		//Student i = new Student("Egor Barakhov", "11-903", (byte) 32, 'm');
		//BufferStudents.write(i);
		//Student k = BufferStudents.read();
		//System.out.println(k.getGroup() + " " + (1970+k.getDate()) + " " + k.getName() + " " + k.getSex());
	}
	
	public static void write(List<Student> stL) {
		try(FileOutputStream fos = new FileOutputStream("test.io", true)) {
			ByteBuffer bb = ByteBuffer.allocate(TEST_SIZE);
			for (Student st : stL) {
				bb = bb.put((byte) st.getName().length());
				bb = bb.put(st.getName().getBytes());
				bb = bb.put((byte) st.getGroup().length());
				bb = bb.put(st.getGroup().getBytes());
				bb = bb.put((byte) st.getDate());
				bb = bb.put((byte) (st.getSex() >> 8));
				bb = bb.put((byte) st.getSex());
			}
			fos.write(bb.array());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Student> read() {
		try(FileInputStream fis = new FileInputStream("test.io")) {
			List<Student> stL = new ArrayList<>();
			ByteBuffer bb = ByteBuffer.allocate(TEST_SIZE);
			int count = 0;
			while (count < TEST_SIZE) {
				bb.put((byte) fis.read());
				count++;
			}
			fis.close();
			bb.rewind();
			byte[] bbb = bb.array();
			int ind = 0;
			while (ind < bbb.length) {
				Student st = new Student();
				ind++;
				st.setName(new String(bbb, ind, bbb[ind - 1], "UTF-8"));
				ind += bbb[ind - 1] + 1;
				st.setGroup(new String(bbb, ind, bbb[ind - 1], "UTF-8"));
				ind +=  bbb[ind - 1];
				st.setDate(bbb[ind]);
				ind++;
				char sex = (char) (bbb[ind] >> 8);
				ind++;
				sex |= bbb[ind];
				st.setSex(sex);
				stL.add(st);
			}
			return stL;
		} catch (IOException e) {
			return null;
		}
	}
}
