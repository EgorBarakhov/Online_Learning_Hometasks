package withobjectstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import student.Student;

public class StudentObjectInputDecorator extends InputStream {
	private ObjectInputStream is;

	public StudentObjectInputDecorator(InputStream is) throws IOException{
		this.is = new ObjectInputStream(is);
	}
	
	public Student readStudent() {
		Student st = null;
		try {
			st = (Student) is.readObject();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return st;
	}

	@Override
	public int read() throws IOException {
		return is.read();
	}

	public int hashCode() {
		return is.hashCode();
	}

	public int read(byte[] b) throws IOException {
		return is.read(b);
	}

	public boolean equals(Object obj) {
		return is.equals(obj);
	}

	public int read(byte[] b, int off, int len) throws IOException {
		return is.read(b, off, len);
	}

	public long skip(long n) throws IOException {
		return is.skip(n);
	}

	public String toString() {
		return is.toString();
	}

	public int available() throws IOException {
		return is.available();
	}

	public void close() throws IOException {
		is.close();
	}

	public void mark(int readlimit) {
		is.mark(readlimit);
	}

	public void reset() throws IOException {
		is.reset();
	}

	public boolean markSupported() {
		return is.markSupported();
	}
	
	
}
