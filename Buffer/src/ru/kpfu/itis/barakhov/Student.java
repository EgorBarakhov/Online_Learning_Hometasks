package ru.kpfu.itis.barakhov;

public class Student {
	private String name;
	private String group;
	private byte date;
	private char sex;
	
	public Student() {
		
	}
	
	public Student(String name, String group, byte date, char sex) {
		this.name = name;
		this.group = group;
		this.date = date;
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public byte getDate() {
		return date;
	}

	public void setDate(byte date) {
		this.date = date;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}
}
