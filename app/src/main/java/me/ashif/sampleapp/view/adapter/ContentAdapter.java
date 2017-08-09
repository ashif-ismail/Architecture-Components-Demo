package me.ashif.sampleapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.List;
import me.ashif.sampleapp.R;
import me.ashif.sampleapp.data.model.ContentModel;
import me.ashif.sampleapp.databinding.ContentItemsBinding;


/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

public class ContentAdapter extends RecyclerView.Adapter {

  private ContentItemsBinding mBinding;
  private List<ContentModel> contentList;

  public ContentAdapter(List<ContentModel> contentList) {
    this.contentList = contentList;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.content_items, parent, false);
        return new ContentViewHolder(mBinding);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return contentList.size();
  }

  private class ContentViewHolder extends RecyclerView.ViewHolder {

    private ContentItemsBinding binding;

    public ContentViewHolder(ContentItemsBinding mBinding) {
      super(mBinding.getRoot());
      this.binding = mBinding;
    }
  }
}
