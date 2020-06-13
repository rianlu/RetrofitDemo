package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        // Gson默认不导出值为null的属性
        // Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public okhttp3.Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .header("Interceptor-Header", "456")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // getPosts();

        // getPosts(1);

        // getPosts(1, "desc");

        // getPosts(new Integer[]{1, 3});

        /*Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("_order", "desc");
        getPosts(params);*/

        // getComments();

        // getComments("posts/1/comments");

        // createPost();

        // createPost(11011, "Title", "Body");

        /*Map<String, String> fields = new HashMap<>();
        fields.put("userId", "1");
        fields.put("title", "Title");
        fields.put("body", "Body");
        createPost(fields);*/

        // putPost();

        patchPost();

        // deletePost();
    }

    private void getPosts() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> list = response.body();
                for (Post post : list) {
                    textView.append(post.toString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getPosts(int userId) {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(userId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> list = response.body();
                for (Post post : list) {
                    textView.append(post.toString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getPosts(int userId, String order) {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(userId, order);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> list = response.body();
                for (Post post : list) {
                    textView.append(post.toString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getPosts(Integer[] userId) {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(userId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> list = response.body();
                for (Post post : list) {
                    textView.append(post.toString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getPosts(Map<String, String> params) {

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Post> list = response.body();
                for (Post post : list) {
                    textView.append(post.toString() + "\n");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getComments() {

        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(1);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Comment> list = response.body();
                for (Comment comment : list) {
                    textView.append(comment.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getComments(String url) {

        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(1);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }

                List<Comment> list = response.body();
                for (Comment comment : list) {
                    textView.append(comment.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void createPost() {

        Post post = new Post(11011, "Title", "Body");
        Call<Post> call = jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post responsePost = response.body();
                textView.append("Code:" + response.code() + "\n");
                textView.append(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void createPost(int userId, String title, String body) {

        Call<Post> call = jsonPlaceHolderApi.createPost(userId, title, body);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post responsePost = response.body();
                textView.append("Code:" + response.code() + "\n");
                textView.append(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void createPost(Map<String, String> fields) {
        Call<Post> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post responsePost = response.body();
                textView.append("Code:" + response.code() + "\n");
                textView.append(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void putPost() {
        Post post = new Post(1, "Title", "Body");
        Call<Post> call = jsonPlaceHolderApi.putPost(1, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post responsePost = response.body();
                textView.append("Code:" + response.code() + "\n");
                textView.append(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void patchPost() {
        Post post = new Post(1, "Title", null);
        Call<Post> call = jsonPlaceHolderApi.patchPost(1, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code:" + response.code());
                    return;
                }
                Post responsePost = response.body();
                textView.append("Code:" + response.code() + "\n");
                textView.append(responsePost.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    public void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText("Code:" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
