# RxRetrofitDemo

Retrofit和Rxjava网络请求的mvp模式简单封装, 获取的是豆瓣图书搜索时的搜索推荐列表

<a href="https://publicpics-1252866204.cos.ap-chengdu.myqcloud.com/1546348507515_video%5B1%5D.gif" target="_blank">点击查看效果</a>

## mvp的设计思路
model层负责数据的请求, 通过接口回调给presenter层进行逻辑处理, 然后presenter调用view的方法将数据显示在用户界面上, presenter在中间负责调用m层和v层, 通过中间层presenter, 使view(视图层, 即activity)与model(数据层)解耦

## 封装
- 封装一个RetrofitHelper, 使用单例模式, (算一个简单工厂?), 负责创建Retrofit, 并对添加一些通用的配置(如公共头部, 日志拦截器等)
- 封装一个BaseResponse, 包含返回的json的一些公共字段, 比如code(是否成功)等
- 封装BaseObserver, 即数据消费者的基类, 在这里处理一些共有的操作, 比如请求失败时的处理

## Retrofit与Rxjava组合做网络请求

> 由于我请求的豆瓣这个接口返回的json直接是一个数组, 后来也懒得改了, 所以用不上BASEObserver了
> "https://book.douban.com/j/subject_suggest?q=..."

1. 创建Retrofit请求接口的Api类SearchBookApi, 返回一个Observable
```
public interface SearchBookApi {

    @GET("j/subject_suggest")
    Observable<List<BookResponse>> searchBook(@Query("q")String bookName);

}
```
2. 创建Retrofit对象(这个demo里已经封装在RetrofitHelper里了)
```
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
```
3. 通过Rxjava链式调用请求数据
```
        retrofit.create(SearchBookApi.class)
                .searchBook(bookName)//这里返回的是Observable<List<BookResponse>>
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(StaticClass.MYTAG, "onSubscribe()");
                    }

                    @Override
                    public void onNext(List<BookResponse> bookResponses) {
                        callback.handleData(bookResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.handleError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(StaticClass.MYTAG, "onCompleted()");
                    }
                });

```

