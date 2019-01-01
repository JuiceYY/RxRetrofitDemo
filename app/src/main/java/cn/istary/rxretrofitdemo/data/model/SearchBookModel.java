package cn.istary.rxretrofitdemo.data.model;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 19:36
 * DESCRIPTION:
 */

import android.util.Log;

import java.util.List;

import cn.istary.rxretrofitdemo.data.response.BookResponse;
import cn.istary.rxretrofitdemo.util.StaticClass;
import cn.istary.rxretrofitdemo.util.http.RetrofitHelper;
import cn.istary.rxretrofitdemo.data.api.SearchBookApi;
import cn.istary.rxretrofitdemo.util.schedulers.ThreadChange;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchBookModel {


    public void getBooks(String bookName, final GetBooksCallback callback){
        //TODO
        RetrofitHelper.getInstance()
                .createApi(SearchBookApi.class, StaticClass.DOMAIN)
                .searchBook(bookName)//这里返回的是Observable<List<BookResponse>>
                .compose(ThreadChange.<List<BookResponse>>io2main())//io线程到主线程转换

        //正常应该这样, 用到了自定义的Observer, 但请求的接口返回的是List, 所以不能用了
//                .subscribe(new BaseObserver<List<BookResponse>>() {
//                    @Override
//                    public void onNext(List<BookResponse> bookResponses) {
//                        callback.handleData(bookResponses);
//                    }
//
//                    @Override
//                    public void onNetError(String errorMsg) {
//                        callback.handleError(errorMsg);
//                    }
//                });
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

    }

    public interface GetBooksCallback{
        //处理正确返回的数据
        void handleData(List<BookResponse> bookResponseList);

        //处理错误情况
        void handleError(String errorMsg);
    }

}
