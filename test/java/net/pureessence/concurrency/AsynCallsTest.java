package net.pureessence.concurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@ContextConfiguration(locations = {"/executor.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AsynCallsTest {
    @Autowired
    private AsyncCall asyncCall;

    @Test
    public void getPdf() {
        asyncCall.getPdf();
        asyncCall.getPdf();
        asyncCall.getPdf();
    }
}
