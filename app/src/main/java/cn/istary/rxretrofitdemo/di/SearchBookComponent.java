package cn.istary.rxretrofitdemo.di;
/*
 * CREATED BY: Sinry
 * TIME: 2019/1/1 12:07
 * DESCRIPTION:
 */

import cn.istary.rxretrofitdemo.view.SearchBookActivity;
import dagger.Component;

@Component(modules = SearchBookModule.class)
public interface SearchBookComponent {

    void inject(SearchBookActivity activity);
}
