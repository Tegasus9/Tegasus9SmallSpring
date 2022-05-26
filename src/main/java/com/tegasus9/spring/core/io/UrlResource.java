package com.tegasus9.spring.core.io;

import cn.hutool.core.lang.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class UrlResource implements Resource{
    private final URL url;
    public UrlResource(URL url){
        Assert.notNull(url,"url Must not be null");
        this.url = url;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.9.11.20", 7890));
        URLConnection urlConnection = this.url.openConnection(proxy);

        try {
            return urlConnection.getInputStream();
        } catch (IOException e) {
            if (urlConnection instanceof HttpURLConnection){
                ((HttpURLConnection) urlConnection).disconnect();
            }
            throw e;
        }

    }
}
