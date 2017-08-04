package me.ashif.sampleapp.view.ui.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.data.repo.ContentRepository;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<List<ContentModel>> contentListObservable;

    @Inject
    public HomeViewModel(Application application, ContentRepository contentRepository) {
        super(application);
        this.contentListObservable = contentRepository.getContent();
    }

    public LiveData<List<ContentModel>> getContentListObservable() {
        return contentListObservable;
    }
}
