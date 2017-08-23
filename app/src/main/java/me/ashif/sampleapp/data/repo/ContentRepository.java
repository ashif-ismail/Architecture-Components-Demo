package me.ashif.sampleapp.data.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import me.ashif.sampleapp.api.RESTService;
import me.ashif.sampleapp.conf.AppExecutors;
import me.ashif.sampleapp.conf.NetworkBoundResource;
import me.ashif.sampleapp.conf.Resource;
import me.ashif.sampleapp.data.dao.ContentDao;
import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.data.model.ContentModel.Content;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@Singleton
public class ContentRepository {

  private RESTService mApiService;
  private ContentDao mContentDao;
  private AppExecutors mAppExecutors;

  @Inject
  public ContentRepository(RESTService mApiService, ContentDao mContentDao,
      AppExecutors mAppExecutors) {
    this.mContentDao = mContentDao;
    this.mApiService = mApiService;
    this.mAppExecutors = mAppExecutors;
  }

  public LiveData<List<Content>> getContentList() {
    final MutableLiveData<List<Content>> contentLiveData = new MutableLiveData<>();
    mApiService.getContentList().enqueue(new Callback<ContentModel>() {
      @Override
      public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
        if (response.isSuccessful()) {
          contentLiveData.setValue(response.body().getContent());
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
