package net.pureessence.other;

import net.pureessence.domain.ObjectWithList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceTest {
    @Mock
    private ObjectWithList objectWithList;

    @Spy
    private List<String> logs = new ArrayList<String>();

    @InjectMocks
    private ExampleService exampleService = new ExampleService();

    @Before
    public void setUp() throws Exception {
        when(objectWithList.getLogs()).thenReturn(logs);
    }

    @Test
    public void testDoSomething() {
        exampleService.doSomething();
        verify(logs).add("logic completed");
    }
}
