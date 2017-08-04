package me.ashif.sampleapp.data.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@Singleton
public class ContentRepository {

    @Inject
    ApiService mApiService;

    public LiveData<List<ContentModel>> getContent() {
        final MutableLiveData<List<ContentModel>> contentLiveData = new MutableLiveData<>();
        mApiService.getContentList().enqueue(new Callback<List<ContentModel>>() {
            @Override
            public void onResponse(Call<List<ContentModel>> call, Response<List<ContentModel>> response) {
                if (response.isSuccessful()) {
                    contentLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ContentModel>> call, Throwable t) {
                contentLiveData.setValue(null);
                // TODO: 4/8/17 should implement better error handling strategy
            }
        });
        return contentLiveData;
    }
}
