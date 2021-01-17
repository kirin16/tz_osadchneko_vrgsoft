package artem.com.tz_osadchneko_vrgsoft.ui.fragments;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import artem.com.tz_osadchneko_vrgsoft.R;
import artem.com.tz_osadchneko_vrgsoft.engine.adapter.ArticleAdapter;
import artem.com.tz_osadchneko_vrgsoft.common.BaseConstants;
import artem.com.tz_osadchneko_vrgsoft.model.Children;

public class ArticleFragment extends Fragment {

    private ProgressBar mProgressBar;
    private ArticleAdapter mArticleAdapter;
    private RecyclerView mRecyclerView;
    private Drawable mDivider;
    private LiveData<List<Children>> childrenDataList;

    private int savedPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleAdapter = new ArticleAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_View);
        mProgressBar = view.findViewById(R.id.progress_Bar);
        mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);

        showProgressBar();

        ArticleViewModel model = new ViewModelProvider(requireActivity())
                .get(ArticleViewModel.class);

        childrenDataList = model.getData();
        childrenDataList.observe(this, children -> {
            mArticleAdapter.setArticles(children);
            dismissProgressBar();
            restoreListPosition();
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        divider.setDrawable(mDivider);
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                savedPosition = ((LinearLayoutManager)recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();
            }
        });

        mRecyclerView.setAdapter(mArticleAdapter);


        return view;
    }

    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void dismissProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveListPosition();
    }

    private void saveListPosition(){
        SharedPreferences getPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor e = getPreferences.edit();
        e.putInt(BaseConstants.TAG, savedPosition);
        e.apply();
    }

    private void restoreListPosition(){
        SharedPreferences getPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        savedPosition = getPreferences.getInt(BaseConstants.TAG, 0);
        mRecyclerView.scrollToPosition(savedPosition);
    }

    @Nullable
    @Override
    public Object getSharedElementEnterTransition() {
        return super.getSharedElementEnterTransition();
    }

}
