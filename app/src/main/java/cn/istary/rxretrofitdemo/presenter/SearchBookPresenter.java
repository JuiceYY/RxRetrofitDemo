package cn.istary.rxretrofitdemo.presenter;

import java.util.List;

import javax.inject.Inject;

import cn.istary.rxretrofitdemo.SearchBookContract;
import cn.istary.rxretrofitdemo.adapter.BookAdapter;
import cn.istary.rxretrofitdemo.data.BookResponse;
import cn.istary.rxretrofitdemo.data.model.SearchBookModel;

/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 20:05
 * DESCRIPTION:
 */

public class SearchBookPresenter implements SearchBookContract.Presenter {

    private SearchBookContract.View view;

    @Inject
    SearchBookModel model;

    public SearchBookPresenter(SearchBookModel model){
        this.model = model;
    }

    @Override
    public void takeView(SearchBookContract.View view) {
        this.view = view;
    }

    @Override
    public void searchBooks(String name) {
        model.getBooks(name, new SearchBookModel.GetBooksCallback() {
            @Override
            public void handleData(List<BookResponse> bookResponseList) {
                BookAdapter adapter = new BookAdapter(bookResponseList);
                view.showBooks(adapter);
            }
        });
    }
}
