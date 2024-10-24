package com.snakeyaml.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

/**
 * Student object model.
 *
 * @author ypdev19
 */
@Getter
@Setter
public class Student extends Person {

    private int year;
    private String department;
    private List<Course> courses;

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("year=" + year)
                .add("department='" + department + "'")
                .add("courses=" + courses)
                .toString();
    }
}
