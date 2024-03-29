package com.lgh.huanglib.retrofitlib.http.cookie;

import com.lgh.huanglib.retrofitlib.utils.CookieDbUtil;
import com.lgh.huanglib.util.L;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * gson持久化截取保存数据
 * Created by WZG on 2016/10/20.
 */
public class CookieInterceptor implements Interceptor {
    private CookieDbUtil dbUtil;
    /*是否缓存标识*/
    private boolean cache;
    /*url*/
    private String url;

    public CookieInterceptor(boolean cache, String url) {
        dbUtil= CookieDbUtil.getInstance();
        this.url=url;
        this.cache=cache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);


        L.e("CookieInterceptor","intercept.....cache  "+ cache);
        if(cache){
            ResponseBody body = response.body();
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);
            CookieResulte resulte= dbUtil.queryCookieBy(url);
            long time=System.currentTimeMillis();
            L.e("xx","Response code  "+response.code());
            L.e("xx","Response code  "+response.message());
            /*保存和更新本地数据*/
            if(resulte==null){
                resulte  =new CookieResulte(url,bodyString,time,response.code(),response.message());
                dbUtil.saveCookie(resulte);
            }else{
                resulte.setResulte(bodyString);
                resulte.setTime(time);
                dbUtil.updateCookie(resulte);
            }
        }
        return response;
    }
}
