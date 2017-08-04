package me.ashif.sampleapp.view.ui.home;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import me.ashif.sampleapp.R;
import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.databinding.FragmentHomeBinding;
import me.ashif.sampleapp.di.components.Injectable;
import me.ashif.sampleapp.view.adapter.ContentAdapter;

public class HomeFragment extends LifecycleFragment implements Injectable {

    private FragmentHomeBinding mBinding;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    ContentAdapter mContentAdapter;

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
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HomeViewModel homeViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(HomeViewModel.class);
        observeViewModel(homeViewModel);

    }

    private void observeViewModel(HomeViewModel homeViewModel) {
        homeViewModel.getContentListObservable().observe(this, (List<ContentModel> contentModels) -> {
            if (contentModels != null){
                mContentAdapter.setContentList(contentModels);
            }
        });
    }
}
