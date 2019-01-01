package cn.istary.rxretrofitdemo.data.model;

import org.junit.Test;

import static org.junit.Assert.*;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 23:46
 * DESCRIPTION:
 */

public class SearchBookModelTest {

    @Test
    public void getBooks() {

        SearchBookModel searchBookModel = new SearchBookModel();
        searchBookModel.getBooks("xiyouji");

    }
}