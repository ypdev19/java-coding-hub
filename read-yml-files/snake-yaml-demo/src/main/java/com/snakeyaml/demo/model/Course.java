package com.snakeyaml.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

/**
 * Course object model.
 *
 * @author ypdev19
 */
@Getter
@Setter
public class Course {

    private String name;
    private double credits;

    @Override
    public String toString() {
        return new StringJoiner(", ", Course.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("credits=" + credits)
                .toString();
    }
}
