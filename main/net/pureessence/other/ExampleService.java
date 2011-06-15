package net.pureessence.other;


import java.util.Arrays;
import java.util.List;

import net.pureessence.domain.ObjectWithList;

import org.springframework.beans.factory.annotation.Autowired;

public class ExampleService {
    public static final List<String> CONSTANTS = Arrays.asList("c1", "c2");

    private ObjectWithList objectWithList;

    protected void doSomething() {
        // logic
        objectWithList.getLogs().add("logic completed");
    }

    @Autowired
    public void setObjectWithList(ObjectWithList objectWithList) {
        this.objectWithList = objectWithList;
    }
}
