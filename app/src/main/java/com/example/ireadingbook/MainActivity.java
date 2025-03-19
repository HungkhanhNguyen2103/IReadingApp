package com.example.ireadingbook;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apicaller.IAppApiCaller;
import com.example.apicaller.RetrofitClient;
import com.example.basemodel.ReponderModel;
import com.example.helper.Utils;
import com.example.model.Category;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    IAppApiCaller apiCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ReponderModel<Category> cateList = new ReponderModel<>();
        setContentView(R.layout.activity_main);
        apiCaller = RetrofitClient.getInstance(Utils.BASE_URL, this).create(IAppApiCaller.class);
        Call<ReponderModel<Category>> call = apiCaller.getCategories();
        call.enqueue(new Callback<ReponderModel<Category>>() {
            @Override
            public void onResponse(Call<ReponderModel<Category>> call, Response<ReponderModel<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ReponderModel<Category> data = response.body();
                    cateList.setDataList(data.getDataList());
                } else {
                    Log.d("API Response", "Lỗi phản hồi từ API");
                }
            }

            @Override
            public void onFailure(Call<ReponderModel<Category>> call, Throwable t) {
                Log.d("API Response", "Thất bại khi gọi API: " + t.getMessage());
            }
        });

        //getCategories();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

//    private void getCategories() {
//        try {
//            Call<ReponderModel> call = apiCaller.getCategories();
//            call.enqueue(new Callback<ReponderModel>() {
//                @Override
//                public void onResponse(Call<ReponderModel> call, Response<ReponderModel> response) {
//                    if (response.isSuccessful()) {
//                        ReponderModel data = response.body();
//                    } else {
//                        Log.d("API Response", "Lỗi phản hồi từ API");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ReponderModel> call, Throwable t) {
//                    Log.d("API Response", "Thất bại khi gọi API: " + t.getMessage());
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
}