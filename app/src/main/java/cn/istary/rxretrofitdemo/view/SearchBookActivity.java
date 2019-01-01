package cn.istary.rxretrofitdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import javax.inject.Inject;

import cn.istary.rxretrofitdemo.R;
import cn.istary.rxretrofitdemo.SearchBookContract;
import cn.istary.rxretrofitdemo.adapter.BookAdapter;
import cn.istary.rxretrofitdemo.di.DaggerSearchBookComponent;

public class SearchBookActivity extends AppCompatActivity implements View.OnClickListener, SearchBookContract.View {

    private EditText editSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;

    @Inject
    SearchBookContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        editSearch = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recyclerView);
        btnSearch.setOnClickListener(this);

        DaggerSearchBookComponent.builder()
                .build()
                .inject(this);

        presenter.takeView(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSearch:
                String bookName = editSearch.getText().toString();
                presenter.searchBooks(bookName);
                break;
        }
    }

    @Override
    public void showBooks(BookAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, "发生了一些错误: "+msg, Toast.LENGTH_SHORT).show();
    }
}
