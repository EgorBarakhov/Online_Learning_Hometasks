package ru.kpfu.itis.barakhov;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONParser {

    public static void writeStudent(Student student) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        objectMapper.writeValue(new File("target/student.json"), student);
    }

    public static java.lang.Object readStudent() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(classLoader.getResource("student.json").getFile());
        ObjectMapper objectMapper = new ObjectMapper(new JsonFactory());
        return objectMapper.readValue(file, Student.class);
    }
}
