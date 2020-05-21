package ru.kpfu.itis.barakhov;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String group;
    private int points;
    private boolean sex;

    public Student(String name, String group, int points, boolean sex) {
        this.name = name;
        this.group = group;
        this.points = points;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getPoints() == student.getPoints() &&
                isSex() == student.isSex() &&
                getName().equals(student.getName()) &&
                getGroup().equals(student.getGroup());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + group.hashCode() + points;
    }
}
