package net.heedstudio.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import net.heedstudio.soccernews.MainActivity;
import net.heedstudio.soccernews.databinding.FragmentNewsBinding;
import net.heedstudio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, favoritedNews -> {

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDB().newsDao().save(favoritedNews);
                }
            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {

                case DOING:
                    //TODO iniciar swiperefreshlayout
                    break;

                case DONE:
                    //TODO finalizar swiperefreshlayout
                    break;

                case ERROR:
                    //TODO finalizar swiperefreshlayout
                    //TODO mostrar erro
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}