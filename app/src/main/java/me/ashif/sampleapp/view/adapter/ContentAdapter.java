package me.ashif.sampleapp.view.adapter;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

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

    public void setContentList(List<ContentModel> contentList) {
        if (this.contentList == null) {
            this.contentList = contentList;
            notifyItemRangeInserted(0, contentList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ContentAdapter.this.contentList.size();
                }

                @Override
                public int getNewListSize() {
                    return contentList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ContentAdapter.this.contentList.get(oldItemPosition).getContent().get(oldItemPosition).getId() ==
                            contentList.get(newItemPosition).getContent().get(newItemPosition).getId();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ContentModel content = contentList.get(newItemPosition);
                    ContentModel old = contentList.get(oldItemPosition);
                    return content.getContent().get(newItemPosition).getId() == old.getContent().get(oldItemPosition).getId()
                            && Objects.equals(old.getContent().get(newItemPosition).getImg(), old.getContent().get(oldItemPosition).getImg());
                }
            });
        }
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
