package net.pureessence.concurrency;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Component
public class AsyncCall {
    @Autowired
    private Log log;
    
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public void getPdf(int number) {
        Future<String> futureTask = taskExecutor.submit(new GetPdf());
        while (!futureTask.isDone()) {
            log.info("Task not yet completed.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                log.info("Will" +
                        " check after 1/2 sec.");
            }
        }
        log.info(String.format("after the call #%s", number));
        try {
            log.info(futureTask.get());
        } catch (InterruptedException e) {
            log.error(e);
        } catch (ExecutionException e) {
            log.error(e);
        }
    }

    class GetPdf implements Callable<String> {
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Ran";
        }
    }
}
