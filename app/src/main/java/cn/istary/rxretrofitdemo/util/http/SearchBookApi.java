package cn.istary.rxretrofitdemo.util.http;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 20:13
 * DESCRIPTION:
 */

import android.telecom.Call;

import java.util.List;

import cn.istary.rxretrofitdemo.data.BookResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchBookApi {

    @GET("j/subject_suggest")
    Observable<List<BookResponse>> searchBook(@Query("q")String bookName);

}
