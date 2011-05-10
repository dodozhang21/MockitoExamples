package net.pureessence.component;

import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

@Component
public class HttpMethodHelper {
    public PostMethod createPostMethod(String url) {
        return new PostMethod(url);
    }
}
