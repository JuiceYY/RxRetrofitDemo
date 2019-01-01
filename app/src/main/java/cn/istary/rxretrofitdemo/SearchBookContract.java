package cn.istary.rxretrofitdemo;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 19:06
 * DESCRIPTION:
 */

import cn.istary.rxretrofitdemo.adapter.BookAdapter;

public interface SearchBookContract {

    interface View{

        /**
         * recyclerview显示查询结果
         * @param adapter presenter处理好的adapter
         */
        void showBooks(BookAdapter adapter);

        /**
         * 显示错误
         * @param msg 错误信息
         */
        void showError(String msg);
    }

    interface Presenter{

        /**
         * 让presenter依赖view
         * @param view
         */
        void takeView(View view);

        /**
         * 按照书名查找图书
         * @param name
         */
        void searchBooks(String name);

    }

}
