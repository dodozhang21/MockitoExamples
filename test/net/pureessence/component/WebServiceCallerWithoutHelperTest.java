package net.pureessence.component;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = WebServiceCallerWithoutHelper.class)
public class WebServiceCallerWithoutHelperTest {
    private static final String STATUS_URL = "http://localhost/status";

    private WebServiceCallerWithoutHelper webServiceCaller = new WebServiceCallerWithoutHelper();

    @Before
    public void setUp() throws Exception {
        webServiceCaller.setStatusUrl(STATUS_URL);
        webServiceCaller.setTimeout(3000L);
    }

    @Test
    public void testIsJobFinished() throws Exception {
    	// arrange
        final PostMethod postMethod = mock(PostMethod.class);

        whenNew(PostMethod.class).withArguments(STATUS_URL).thenReturn(postMethod);

        when(postMethod.getResponseBodyAsString()).thenReturn("false", "false", "true");

        // act
        boolean result = webServiceCaller.isJobFinished();
        
        // assert
        assertTrue(result);

        // for more info -> http://code.google.com/p/powermock/wiki/MockitoUsage13
    }
}