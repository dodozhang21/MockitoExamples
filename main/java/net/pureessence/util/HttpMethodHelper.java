package net.pureessence.util;

import org.apache.commons.httpclient.methods.PostMethod;

public class HttpMethodHelper {
    public PostMethod createPostMethod(String url) {
        return new PostMethod(url);
    }
}
