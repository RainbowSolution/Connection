package com.connection.ApiPkg;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String CATEGORY_URL = "https://alphawizz.com/connection/uploads/category_images/";
    public static final String BASE_URL = "https://alphawizz.com/connection/Api/";
    public static final String IMAGE_PATH = "https://alphawizz.com/connection/uploads/profiles/";
    private static OkHttpClient client;
    private static Retrofit retrofit = null;

    //https://alphawizz.com/connection/uploads/profiles/

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}