package me.ashif.sampleapp.data.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import javax.inject.Inject;
import javax.inject.Singleton;
import me.ashif.sampleapp.api.ApiService;
import me.ashif.sampleapp.data.model.ContentModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@Singleton
public class ContentRepository {

  private ApiService mApiService;

  @Inject
  public ContentRepository(ApiService mApiService) {
    this.mApiService = mApiService;
  }

  public LiveData<ContentModel> getContent() {
    final MutableLiveData<ContentModel> contentLiveData = new MutableLiveData<>();

    mApiService.getContentList().enqueue(new Callback<ContentModel>() {
      @Override
      public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
        if (response.isSuccessful()) {
          contentLiveData.setValue(response.body());
        }
      }

      @Override
      public void onFailure(Call<ContentModel> call, Throwable t) {
        contentLiveData.setValue(null);
        // TODO: 4/8/17 should implement better error handling strategy
      }
    });
    return contentLiveData;
  }
}
