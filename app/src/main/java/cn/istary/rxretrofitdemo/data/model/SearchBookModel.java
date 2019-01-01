package cn.istary.rxretrofitdemo.data.model;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 19:36
 * DESCRIPTION:
 */

import android.util.Log;

import java.util.List;

import cn.istary.rxretrofitdemo.data.BookResponse;
import cn.istary.rxretrofitdemo.util.StaticClass;
import cn.istary.rxretrofitdemo.util.http.RetrofitHelper;
import cn.istary.rxretrofitdemo.util.http.SearchBookApi;
import cn.istary.rxretrofitdemo.util.schedulers.ThreadChange;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchBookModel {


    public void getBooks(String bookName, final GetBooksCallback callback){
        //TODO
        RetrofitHelper.getInstance()
                .createApi(SearchBookApi.class, StaticClass.DOMAIN)
                .searchBook(bookName)//这里返回的是Observable<List<BookResponse>>
                .compose(ThreadChange.<List<BookResponse>>io2main())//io线程到主线程转换
                .subscribe(new Consumer<List<BookResponse>>() {
                    //定义事件的消费者
                    @Override
                    public void accept(List<BookResponse> bookResponses) throws Exception {
                        Log.d("mydemo", String.valueOf(bookResponses.size()));
                        callback.handleData(bookResponses);
                    }
                });

    }

    public interface GetBooksCallback{
        void handleData(List<BookResponse> bookResponseList);
    }

}
