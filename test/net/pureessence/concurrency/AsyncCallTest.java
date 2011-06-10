package net.pureessence.concurrency;


import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import net.pureessence.concurrency.AsyncCall.GetPdf;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class AsyncCallTest {
    @Mock
    private Log log;
    
    @Mock
    private ThreadPoolTaskExecutor taskExecutor;
    
    @InjectMocks
    private AsyncCall asyncCall = new AsyncCall();

    @Test
    public void testGetPdf() {
	// arrange
	when(taskExecutor.submit(any(GetPdf.class))).thenAnswer(new Answer<Future<String>>() {
	    public Future<String> answer(InvocationOnMock invocation) throws Throwable {
		Future<String> future = mock(FutureTask.class);
		when(future.isDone()).thenReturn(false, false, true);
		when(future.get()).thenReturn("Ran");
		return future;
	    }
	});
	
	// act
	asyncCall.getPdf(1);
	
	// assert
	verify(log, times(2)).info("Task not yet completed.");
	verify(log).info("after the call #1");
	verify(log).info("Ran");
    }
}
