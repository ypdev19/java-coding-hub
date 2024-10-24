package com.snakeyaml.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

/**
 * Person object model.
 *
 * @author ypdev19
 */
@Getter
@Setter
public class Person {

    private long id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("address='" + address + "'")
                .toString();
    }
}
