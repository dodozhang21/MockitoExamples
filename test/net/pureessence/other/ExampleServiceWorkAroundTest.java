package net.pureessence.other;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import net.pureessence.domain.ObjectWithList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceWorkAroundTest {
    @Mock
    private ObjectWithList objectWithList;

    @Spy
    private List<String> logs = new ArrayList<String>();

    private ExampleService exampleService = new ExampleService();

    @Before
    public void setUp() throws Exception {
        when(objectWithList.getLogs()).thenReturn(logs);
        exampleService.setObjectWithList(objectWithList);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoSomething() {
        exampleService.doSomething();
        verify(logs).add("logic completed");
    }
}
