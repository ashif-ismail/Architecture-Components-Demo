package me.ashif.sampleapp.data.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
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

//  public LiveData<ContentModel> getContentList() {
//    final MutableLiveData<ContentModel> contentLiveData = new MutableLiveData<>();
//
//    mApiService.getContentList().enqueue(new Callback<ContentModel>() {
//      @Override
//      public void onResponse(Call<ContentModel> call, Response<ContentModel> response) {
//        if (response.isSuccessful()) {
//          contentLiveData.setValue(response.body());
//        }
//      }
//
//      @Override
//      public void onFailure(Call<ContentModel> call, Throwable t) {
//        contentLiveData.setValue(null);
//        // TODO: 4/8/17 should implement better error handling strategy
//      }
//    });
//
//    return contentLiveData;
//  }

  public LiveData<Resource<Content>> getContent() {
    return new NetworkBoundResource<Content, ContentModel>(mAppExecutors) {
      @Override
      protected void saveCallResult(@NonNull ContentModel item) {
        mContentDao.saveContent(item.getContent());
      }

      @NonNull
      @Override
      protected LiveData<Content> loadFromDb() {
        return mContentDao.loadContents();
      }

      @NonNull
      @Override
      protected Call<ContentModel> createCall() {
        return mApiService.getContentList();
      }
    }.getAsLiveData();
  }
}
