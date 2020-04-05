package withdatastream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import student.Student;

public class StudentDataOutputDecorator extends OutputStream{
	private DataOutputStream os;
	
	public StudentDataOutputDecorator(OutputStream os) throws IOException {
		this.os = new DataOutputStream(os);
	}
	
	public void writeStudent(Student st) {
		try {
			os.writeUTF(st.getName());
			os.writeUTF(st.getGroup());
			os.writeByte(st.getDate());
			os.writeChar(st.getSex());
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
