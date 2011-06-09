package net.pureessence.aspect;

import net.pureessence.component.HttpMethodHelper;
import net.pureessence.component.WebServiceCaller;
import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(locations = {"/aop.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebServiceAspectTest {
    @Autowired
    @Qualifier("webServiceCaller")
    private WebServiceCaller webServiceCaller;

    @Autowired
    private WebServiceAspect webServiceAspect;

    @Mock
    private Log log;

    @Mock
    private HttpMethodHelper httpMethodHelper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        webServiceAspect.setLog(log);
        webServiceCaller.setHttpMethodHelper(httpMethodHelper);
    }

    @Test
    public void testLogging() throws IOException {
        // arrange
        RuntimeException runtimeException = new RuntimeException("host not found");
        when(httpMethodHelper.createPostMethod(anyString())).thenThrow(runtimeException);

        // act
        webServiceCaller.getJobStatus();

        // assert
        verify(log).error("web service get job status failed", runtimeException);
    }
}
