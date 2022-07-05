package net.heedstudio.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.heedstudio.soccernews.MainActivity;
import net.heedstudio.soccernews.databinding.FragmentFavoritesBinding;
import net.heedstudio.soccernews.domain.News;
import net.heedstudio.soccernews.ui.adapter.NewsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        loadFavoriteNews();

        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
        if(activity != null) {
            List<News> favoriteNews = activity.getDB().newsDao().loadFavoriteNews();
            binding.rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvFavorites.setAdapter(new NewsAdapter(favoriteNews, updateNews -> {
                activity.getDB().newsDao().save(updateNews);
                loadFavoriteNews();
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}