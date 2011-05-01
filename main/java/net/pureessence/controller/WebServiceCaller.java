package net.pureessence.controller;

import net.pureessence.util.HttpMethodHelper;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StopWatch;
import sun.security.provider.SystemSigner;

import java.io.IOException;

public class WebServiceCaller {
    @Autowired
    private HttpMethodHelper httpMethodHelper;
    private String statusUrl;
    private long timeout;

    public boolean isJobFinished() throws IOException, InterruptedException {
        PostMethod method = httpMethodHelper.createPostMethod(statusUrl);
        Boolean result = Boolean.valueOf(method.getResponseBodyAsString());
        long start = System.currentTimeMillis();
        while(!result) {
            Thread.sleep(1000L);
            if((System.currentTimeMillis() - start) > timeout) {
                throw new RuntimeException(String.format("request timed out after %s seconds", (System.currentTimeMillis() - start)));
            }
            method = httpMethodHelper.createPostMethod(statusUrl);
            result = Boolean.valueOf(method.getResponseBodyAsString());
        }
        return true;
    }

    @Autowired
    public void setStatusUrl(@Value("${webservice.url}")String statusUrl) {
        this.statusUrl = statusUrl;
    }

    @Autowired
    public void setTimeout(@Value("${webservice.timeout}")long timeout) {
        this.timeout = timeout;
    }
}
