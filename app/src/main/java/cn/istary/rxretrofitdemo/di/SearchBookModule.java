package cn.istary.rxretrofitdemo.di;
/*
 * CREATED BY: Sinry
 * TIME: 2019/1/1 12:08
 * DESCRIPTION:
 */

import cn.istary.rxretrofitdemo.SearchBookContract;
import cn.istary.rxretrofitdemo.data.model.SearchBookModel;
import cn.istary.rxretrofitdemo.presenter.SearchBookPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class SearchBookModule {

    @Provides
    SearchBookContract.Presenter providesSearchBookPresenter(SearchBookModel model){
        return new SearchBookPresenter(model);
    }

    @Provides
    SearchBookModel providesSearchBookModel(){
        return new SearchBookModel();
    }
}
