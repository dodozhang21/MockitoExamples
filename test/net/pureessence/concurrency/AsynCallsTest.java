package net.pureessence.concurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations = {"/executor.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AsynCallsTest {
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    @Qualifier("asyncCall1")
    private AsyncCall asyncCall1;

    @Autowired
    @Qualifier("asyncCall2")
    private AsyncCall asyncCall2;

    @Autowired
    @Qualifier("asyncCall3")
    private AsyncCall asyncCall3;

    @Test
    public void getPdf() {
        asyncCall1.getPdf(1);
        System.out.println("1st pdf called");
        System.out.println(String.format("after 1st call, active count '%s'", taskExecutor.getActiveCount()));
        asyncCall2.getPdf(2);
        System.out.println(String.format("after 2nd call, active count '%s'", taskExecutor.getActiveCount()));
        asyncCall3.getPdf(3);
        System.out.println(String.format("after 3rd call, active count '%s'", taskExecutor.getActiveCount()));
    }
}
