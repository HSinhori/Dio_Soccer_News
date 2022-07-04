package net.heedstudio.soccernews.ui.news;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.heedstudio.soccernews.data.remote.SoccerNewsApi;
import net.heedstudio.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews = new MutableLiveData<>();
    private final SoccerNewsApi soccerNewsApi;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hsinhori.github.io/Soccer-News-Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        soccerNewsApi = retrofit.create(SoccerNewsApi.class);
        findNews();
    }

    private void findNews() {
        soccerNewsApi.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    mNews.setValue(response.body());
                    Log.d("newsnews", response.body().toString());
                } else {
                    //TODO pensar em uma estratégia de tratamento de erros
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO pensar em uma estratégia de tratamento de erros
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}