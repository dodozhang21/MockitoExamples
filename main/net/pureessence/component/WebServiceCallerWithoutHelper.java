package net.pureessence.component;

import java.io.IOException;

import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class WebServiceCallerWithoutHelper {
    private String statusUrl;
    private long timeout;

    public boolean isJobFinished() throws IOException, InterruptedException {
        PostMethod method = new PostMethod(statusUrl);
        Boolean result = Boolean.valueOf(method.getResponseBodyAsString());
        long start = System.currentTimeMillis();
        while(!result) {
            Thread.sleep(1000L);
            if((System.currentTimeMillis() - start) > timeout) {
            	throw new RuntimeException(
                		String.format("request timed out after %s seconds", 
                				(System.currentTimeMillis() - start)
                		)
                	);
            }
            method = new PostMethod(statusUrl);
            result = Boolean.valueOf(method.getResponseBodyAsString());
        }
        return true;
    }

    @Autowired
    public void setStatusUrl(@Value("${webservice.url}")String statusUrl) {
        this.statusUrl = statusUrl;
    }

    @Autowired
    public void setTimeout(@Value(value = "${webservice.timeout}")Long timeout) {
        this.timeout = timeout;
    }
}
