package com.example.apicaller;

import com.example.basemodel.ReponderModel;
import com.example.model.Book;
import com.example.model.Category;

import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface IAppApiCaller {
    @GET("Book/GetCategories")
    Call<ReponderModel<Category>> getCategories();
    @GET("Book/GetAllBookByUser")
    Call<ReponderModel<Book>> getBooks(
            @Query("userName") String userName
    );
}
