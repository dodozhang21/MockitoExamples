package net.pureessence.other;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JUnit4Example {
    @Test
    public void whenYouUseJunit4YouShouldBeHappy() {
        Happiness happiness = new Happiness("JUnit4");
        assertEquals(100, happiness.getLevel());
    }

    @Ignore("Pending Implementation")
    @Test
    public void whenYouUseMockitoYouShouldBeOnCloud9() {
        Happiness happiness = new Happiness("Mockito");
        assertEquals(1000, happiness.getLevel());
    }
}
