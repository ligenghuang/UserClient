package com.yizhitong.userclient.net.service;



import com.lgh.huanglib.retrofitlib.Api.BaseResultEntity2;
import com.yizhitong.userclient.event.WeiXinPayDto;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;


/**
* 
* @author lgh
* created at 2019/5/13 0013 14:38
*/
public interface HttpPostService {

//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<BaseResultEntity> getAbout();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Observable<String> getAboutString();
//
//    @GET(WebUrlUtil.GET_ABOUT)
//    Call<BaseResultEntity> getAboutByCall();
//
//    @GET(WebUrlUtil.URL_GET_MAIN)
//    Observable<BaseResultEntity> getHome();


    /**
     * 获取数据
     * @return
     */
    @POST
    Observable<BaseResultEntity2> PostData_1(@Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie") String SessionId, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie") String SessionId, @Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<BaseResultEntity2> PostData_1(@Header("Cookie") String SessionId, @Body RequestBody Body, @Url String url);

    @POST
    Observable<String> PostData_String(@Body RequestBody Body,@Url String url);
    @POST
    Observable<String> PostData_String(@Header("Cookie") String SessionId, @Body Map<Object, Object> body, @Url String url);
    @POST
    Observable<String> PostData_String(@Header("Cookie") String SessionId, @Body RequestBody Body, @Url String url);

    @POST
    Observable<Double> PostData_double(@Body Map<Object, Object> body, @Url String url);

    @POST
    Observable<WeiXinPayDto> PostPayData(@Header("Cookie") String SessionId, @Body Map<Object, Object> body, @Url String url);
}
