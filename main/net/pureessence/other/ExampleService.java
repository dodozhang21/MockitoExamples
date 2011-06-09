package net.pureessence.other;


import net.pureessence.domain.ObjectWithList;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ExampleService {
    public static final List<String> CONSTANTS = Arrays.asList("c1", "c1");

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
