package cn.istary.rxretrofitdemo.adapter;
/*
 * CREATED BY: Sinry
 * TIME: 2018/12/31 19:27
 * DESCRIPTION:
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.istary.rxretrofitdemo.R;
import cn.istary.rxretrofitdemo.data.response.BookResponse;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    /**
     * adapter应该持有一个List的引用
     */
    private List<BookResponse> bookList;

    public BookAdapter(List<BookResponse> bookList){
        this.bookList = bookList;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        //这个是每一项的view, 实现点击事件
        public final View view;

        ImageView image;
        TextView title;
        TextView author;
        TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            image = itemView.findViewById(R.id.bookImg);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            year = itemView.findViewById(R.id.bookYear);
        }
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //创建ViewHolder实例, 并传入构造函数
        //这是item的view对象
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, viewGroup, false);
        //用item实例化Viewholder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //这里要把默认ViewHolder改成自己的ViewHolder
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder viewHolder, int i) {
        //用于对RecyclerView的子项数据进行赋值
        //在每个子项被滚动到屏幕内的时候执行

        /**
         * 在这里给item里的控件设置内容
         */
        BookResponse book = bookList.get(i);

        //TODO 这里加一个使用glide加载图片功能
        viewHolder.image.setImageResource(R.mipmap.ic_launcher_round);

        viewHolder.title.setText(book.getTitle());
        viewHolder.author.setText(book.getAuthor_name());
        viewHolder.year.setText(book.getYear());
    }

    @Override
    public int getItemCount() {
        Log.d("myadapter", String.valueOf(bookList.size()));
        return bookList.size();
    }
}
