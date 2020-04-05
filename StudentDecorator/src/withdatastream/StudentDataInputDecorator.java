package withdatastream;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import student.Student;

public class StudentDataInputDecorator {
		private DataInputStream is;

		public StudentDataInputDecorator(InputStream is) throws IOException{
			this.is = new DataInputStream(is);
		}
		
		public Student readStudent() {
			Student st = new Student();
			try {
				st.setName(is.readUTF());
				st.setGroup(is.readUTF());
				st.setDate(is.readByte());
				st.setSex(is.readChar());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return st;
		}

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
