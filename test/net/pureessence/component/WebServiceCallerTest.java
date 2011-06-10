package net.pureessence.component;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WebServiceCallerTest {
    private static final String STATUS_URL = "http://localhost/status";

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpMethodHelper httpMethodHelper;

    @InjectMocks
    private WebServiceCaller webServiceCaller = new WebServiceCaller();

    @Test
    public void testIsJobFinished() throws Exception {
    	when(httpMethodHelper.createPostMethod(STATUS_URL).getResponseBodyAsString()).thenReturn(
    			"false", 
    			"false", 
    			"true");
    	
    	boolean result = webServiceCaller.isJobFinished();
    	assertTrue(result);
    }
    @Before
    public void setUp() throws Exception {
        webServiceCaller.setStatusUrl(STATUS_URL);
        webServiceCaller.setTimeout(3000L);
    }


    @Test(expected = RuntimeException.class)
    public void testIsJobFinishedTimeout() throws Exception {
    	// without deep stub you'd do
//    	PostMethod postMethod = mock(PostMethod.class);
//    	when(httpMethodHelper.createPostMethod(STATUS_URL)).thenReturn(postMethod);
//    	when(postMethod.getResponseBodyAsString()).thenReturn("false");
    	
        when(httpMethodHelper.createPostMethod(STATUS_URL).getResponseBodyAsString()).thenReturn("false", "false", "false", "false", "true");

        boolean result = webServiceCaller.isJobFinished();
        assertTrue(result);
    }
}
