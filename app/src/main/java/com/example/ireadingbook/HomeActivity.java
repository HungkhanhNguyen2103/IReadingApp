package com.example.ireadingbook;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.ListBookAdapter;
import com.example.apicaller.IAppApiCaller;
import com.example.apicaller.RetrofitClient;
import com.example.basemodel.ReponderModel;
import com.example.helper.Utils;
import com.example.model.Book;
import com.example.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    IAppApiCaller apiCaller;
    RecyclerView listBookRecycler;
    ListBookAdapter listBookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        listBookRecycler = findViewById(R.id.recycler_free_books);
        listBookRecycler.setLayoutManager(layoutManager);
        listBookRecycler.setHasFixedSize(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //get books
        getListBook();
    }

    private void getListBook(){
        apiCaller = RetrofitClient.getInstance(Utils.BASE_URL, this).create(IAppApiCaller.class);
        Call<ReponderModel<Book>> call = apiCaller.getBooks("Visitor");
        call.enqueue(new Callback<ReponderModel<Book>>() {
            @Override
            public void onResponse(Call<ReponderModel<Book>> call, Response<ReponderModel<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ReponderModel<Book> data = response.body();
                    listBookAdapter = new ListBookAdapter(getApplicationContext(), data.getDataList());
                    listBookRecycler.setAdapter(listBookAdapter);
                } else {
                    Log.d("API Response", "Lỗi phản hồi từ API");
                }
            }

            @Override
            public void onFailure(Call<ReponderModel<Book>> call, Throwable t) {
                Log.d("API Response", "Thất bại khi gọi API: " + t.getMessage());
            }
        });
    }
}