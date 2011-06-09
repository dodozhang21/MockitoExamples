package net.pureessence.component;

import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "webServiceCaller")
public class WebServiceCaller {
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

    public String getJobStatus() throws IOException {
        PostMethod method = httpMethodHelper.createPostMethod(statusUrl);

        return method.getResponseBodyAsString();
    }

    @Autowired
    public void setStatusUrl(@Value("${webservice.url}")String statusUrl) {
        this.statusUrl = statusUrl;
    }

    @Autowired
    public void setTimeout(@Value(value = "${webservice.timeout}")Long timeout) {
        this.timeout = timeout;
    }

    public void setHttpMethodHelper(HttpMethodHelper httpMethodHelper) {
        this.httpMethodHelper = httpMethodHelper;
    }
}
