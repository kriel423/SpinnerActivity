package com.example.spinneractivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SocialMediaAdapter extends RecyclerView.Adapter<SocialMediaAdapter.SocialMediaViewHolder> implements Filterable {

    private List<SocialMediaItem> mSocialMediaList;
    private List<SocialMediaItem> mSocialMediaListFull;
    private OnItemClickListener mListener;


    public interface OnItemClickListener
    {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
     mListener = listener;
    }

    public static class SocialMediaViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewCategory;
        public TextView mTextViewDescription;
        public ImageView mDeleteImage;

        public SocialMediaViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextViewName = itemView.findViewById(R.id.text_view_name);
            mTextViewCategory = itemView.findViewById(R.id.text_view_category);
            mTextViewDescription = itemView.findViewById(R.id.text_view_description);
            mDeleteImage = itemView.findViewById(R.id.image_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }

    public SocialMediaAdapter(ArrayList<SocialMediaItem> socialMediaItems){
        mSocialMediaList = socialMediaItems;
        mSocialMediaListFull = new ArrayList<>(socialMediaItems);
    }

    @NonNull
    @Override
    public SocialMediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        SocialMediaViewHolder socialMediaViewHolder = new SocialMediaViewHolder(v, mListener);
        return socialMediaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SocialMediaViewHolder holder, int position) {
        SocialMediaItem currentItem = mSocialMediaList.get(position);

        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mTextViewName.setText(currentItem.getmTextName());
        holder.mTextViewCategory.setText(currentItem.getmTextCategory());
        holder.mTextViewDescription.setText(currentItem.getmTextDescription());
    }

    @Override
    public int getItemCount() {
        return mSocialMediaList.size();
      //  return mSocialMediaList.size() - 1;
    }

    @Override
    public Filter getFilter() {
        return socialMediaFilter;
    }

    private Filter socialMediaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SocialMediaItem> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(mSocialMediaListFull);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(SocialMediaItem item : mSocialMediaListFull)
                {
                    if(item.getmTextName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                    else if(item.getmTextCategory().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mSocialMediaList.clear();
            mSocialMediaList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };




}
