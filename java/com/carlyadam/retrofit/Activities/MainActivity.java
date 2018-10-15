package com.carlyadam.retrofit.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.carlyadam.retrofit.Adapter.BookAdapter;
import com.carlyadam.retrofit.Data.ApiResponse;
import com.carlyadam.retrofit.Data.Book;
import com.carlyadam.retrofit.Interfaces.ApiInterface;
import com.carlyadam.retrofit.R;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit mRestAdapter;
    private ApiInterface apiInterface;
    private ArrayList<Book> books_list;
    private RecyclerView recyclerBooks;
    private BookAdapter bookAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpotsDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new SpotsDialog(MainActivity.this, R.style.CustomDialog);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBooks();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        // build retrofit adapter
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(ApiInterface.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // creating connection ApiInterface
        apiInterface = mRestAdapter.create(ApiInterface.class);

        books_list= new ArrayList<>();
        getBooks();
    }

    private void getBooks(){

        dialog.show();

        Call<ApiResponse> bookCall=apiInterface.getBooks();
        bookCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                dialog.dismiss();


                books_list= (ArrayList<Book>) response.body().getBooks();

                try {
                    setBooks_list(books_list);
                    showToastMessage(response.body().getMesssage());

                }
                catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                dialog.dismiss();
                throwable.printStackTrace();
                showToastMessage(getString(R.string.connection_problems));
            }
        });

    }

    public void setBooks_list(ArrayList<Book> _books_list) {
        books_list = _books_list;
        recyclerBooks = (RecyclerView) findViewById(R.id.include);
        bookAdapter = new BookAdapter(books_list, this);
        bookAdapter.notifyDataSetChanged();
        recyclerBooks.setAdapter(bookAdapter);

    }

    private void showToastMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
