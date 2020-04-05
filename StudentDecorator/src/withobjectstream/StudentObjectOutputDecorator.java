package withobjectstream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import student.Student;

public class StudentObjectOutputDecorator extends OutputStream{
	private ObjectOutputStream os;
	
	public StudentObjectOutputDecorator(OutputStream os) throws IOException {
		this.os = new ObjectOutputStream(os);
	}
	
	public void writeStudent(Student st) {
		try {
			os.writeObject(st);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void write(int b) throws IOException {
		os.write(b);
	}

	public int hashCode() {
		return os.hashCode();
	}

	public void write(byte[] b) throws IOException {
		os.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		os.write(b, off, len);
	}

	public boolean equals(Object obj) {
		return os.equals(obj);
	}

	public void flush() throws IOException {
		os.flush();
	}

	public void close() throws IOException {
		os.close();
	}

	public String toString() {
		return os.toString();
	}

	
		
}
