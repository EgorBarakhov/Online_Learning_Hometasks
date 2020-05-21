package ru.kpfu.itis.barakhov;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class YAMLParser {
    public static void writeStudent(Student student) {
        ObjectMapper objectMapper = new ObjectMapper(new YamlFactory());
        objectMapper.writeValue(new File("target/student.yaml"), student);
    }
    public java.lang.Object void readStudent() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("person.yaml").getFile());
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        return om.readValue(file, Student.class);
    }
}
