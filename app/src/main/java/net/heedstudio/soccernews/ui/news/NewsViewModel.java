package net.heedstudio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.heedstudio.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> mNews;

    public NewsViewModel() {
        mNews = new MutableLiveData<>();

        //TODO Remover mock de notícias
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária tem desfalque importante","App Nativo Sobre Futebol Feminino com Android Jetpack e Java App Nativo Sobre Futebol Feminino com Android Jetpack e Java"));
        news.add(new News("Ferrinha joga no sábado","App Nativo Sobre Futebol Feminino com Android Jetpack e Java App Nativo Sobre Futebol Feminino com Android Jetpack e Java"));
        news.add(new News("Copa do mundo feminina está terminando","App Nativo Sobre Futebol Feminino com Android Jetpack e Java App Nativo Sobre Futebol Feminino com Android Jetpack e Java"));

        mNews.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return mNews;
    }
}