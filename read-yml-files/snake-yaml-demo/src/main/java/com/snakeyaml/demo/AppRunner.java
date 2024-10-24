package com.snakeyaml.demo;

import com.snakeyaml.demo.model.Person;
import com.snakeyaml.demo.model.Student;
import com.snakeyaml.demo.util.YamlUtils;

/**
 * Main class - project execution
 * What is this project about: Read yml file with snakeyaml library (V-2.0)
 *
 * @author ypdev19
 */
public class AppRunner {

    private static final String PERSON_YML_FILE_NAME = "person.yml";
    private static final String COURSES_YML_FILE_NAME = "courses.yml";
    private static final String STUDENT_YML_FILE_NAME = "student.yml";
    private static final String STUDENT_WITH_COURSES_YML_FILE_NAME = "student-with-courses.yml";

    private static final YamlUtils reader = new YamlUtils();

    public static void main(String[] args) {
        System.out.println("*** Reading YAML files ***");

        System.out.println("\n*** readSimpleYmlFile ***");
        System.out.println(reader.readSimpleYmlFile(PERSON_YML_FILE_NAME));
        System.out.println(reader.readSimpleYmlFile(COURSES_YML_FILE_NAME));

        System.out.println("\n*** Parse YAML file containing a collection using readYmlWithCustomLoader ***");
        System.out.println(reader.readYmlWithCustomLoader(PERSON_YML_FILE_NAME, Person.class));
        System.out.println(reader.readYmlWithCustomLoader(STUDENT_YML_FILE_NAME, Student.class));

        System.out.println("\n*** Parse YAML file containing a collection using readYmlWithDefaultLoader ***");
        System.out.println(reader.readYmlWithDefaultLoader(STUDENT_WITH_COURSES_YML_FILE_NAME, Student.class));
    }

}
