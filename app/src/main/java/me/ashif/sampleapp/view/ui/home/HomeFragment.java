package me.ashif.sampleapp.view.ui.home;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.ashif.sampleapp.R;
import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.databinding.FragmentHomeBinding;
import me.ashif.sampleapp.di.components.Injectable;
import me.ashif.sampleapp.view.adapter.ContentAdapter;
import timber.log.Timber;

public class HomeFragment extends LifecycleFragment implements Injectable {

    String TAG = "logger";
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private FragmentHomeBinding mBinding;
    private ContentAdapter mContentAdapter;

//    @Inject
//    ContentAdapter mContentAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initViews();
        return mBinding.getRoot();
    }

    private void initViews() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.listContent.setLayoutManager(llm);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Log.d(TAG, "onActivityCreated: inside onActivty created");
        Timber.d("inside activity created");
        HomeViewModel homeViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(HomeViewModel.class);
        observeViewModel(homeViewModel);

    }

    private void observeViewModel(HomeViewModel homeViewModel) {
        homeViewModel.getContentListObservable().observe(this, (ContentModel contentModels) -> {
            if (contentModels != null){
                Log.d(TAG, "onActivityCreated: inside observe");
                Log.d(TAG, "onActivityCreated:" + contentModels.getContent().get(0).getTitle());
//                mContentAdapter.setContentList(contentModels);
                List<ContentModel> models = new ArrayList<>();
                models.add(contentModels);
                ContentAdapter contentAdapter = new ContentAdapter(models);
                mBinding.listContent.setAdapter(contentAdapter);
            }
        });
    }
}
