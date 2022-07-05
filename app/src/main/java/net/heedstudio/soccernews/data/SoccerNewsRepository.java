package net.heedstudio.soccernews.data;

import androidx.room.Room;

import net.heedstudio.soccernews.App;
import net.heedstudio.soccernews.data.local.SoccerNewsDb;
import net.heedstudio.soccernews.data.remote.SoccerNewsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region consts
    private static final String REMOTE_API_URL = "https://hsinhori.github.io/Soccer-News-Api/";
    private static final String LOCAL_DB_NAME = "soccer-news";
    //region end consts

    //region Atributos: encapsulam o acesso a API e o DB (ROOM)
    private SoccerNewsApi remoteApi;
    private SoccerNewsDb localDb;

    public SoccerNewsApi getRemoteApi() { return remoteApi; }

    public SoccerNewsDb getLocalDb() { return localDb; }
    //end region

    //region singleton: garante instancia unica dos atributos relacionados ao retrofit e room
    private SoccerNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), SoccerNewsDb.class, LOCAL_DB_NAME)
                .build();
    }

    public static SoccerNewsRepository getInstance() { return LazyHolder.INSTANCE; }

    private static class LazyHolder {
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
    //end region

}
