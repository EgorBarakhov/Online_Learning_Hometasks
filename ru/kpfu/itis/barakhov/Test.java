package ru.kpfu.itis.barakhov;


import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Student me = new Student("Egor Barakhov", "11-903", 87, false);
        try {
            JSONParser.writeStudent(me);
            YAMLParser.writeStudent(me);
        } catch (IOException ex) {
            System.out.println("Except writing situation");
        }
        try {
            Student alsoMe = (Student) JSONParser.readStudent();
            Student meAgain = (Student) YAMLParser.readStudent();
            System.out.println(alsoMe.toString() + "\n" + meAgain.toString());
        } catch (IOException ex) {
            System.out.println("Except reading situation");
        }
    }
}
