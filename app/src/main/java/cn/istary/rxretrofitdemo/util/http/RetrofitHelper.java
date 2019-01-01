package cn.istary.rxretrofitdemo.util.http;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 20:38
 * DESCRIPTION:
 */

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static volatile RetrofitHelper instance;

    private OkHttpClient client;

    private RetrofitHelper(){
        initOkhttpClient();
    }

    public static RetrofitHelper getInstance(){
        if(null == instance){
            synchronized (RetrofitHelper.class){
                if(null == instance){
                    instance = new RetrofitHelper();
                }
            }
        }

        return instance;
    }

    private void initOkhttpClient(){

        //打印请求日志的拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new RequestInterceptor()) //添加公共头部的拦截器
                .build();
    }

    private static class RequestInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            request = addGetDefaultParams(chain);
            return chain.proceed(request);
        }
    }

    private static Request addGetDefaultParams(Interceptor.Chain chain){
        Request request = chain.request();

        //TODO 这里可以对request添加默认参数, 然后返回, 这里就不做处理了

        return request;
    }

    /**
     * 构建retrofit
     * @param tClass 要构建的Api类型
     * @param baseUrl 请求域名
     * @param <T> 传入的Api类型
     * @return
     */
    public <T> T createApi(Class<T> tClass, String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(tClass);

    }


}
