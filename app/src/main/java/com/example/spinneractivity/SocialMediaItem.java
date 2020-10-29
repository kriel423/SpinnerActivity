package com.example.spinneractivity;

import android.os.Parcel;
import android.os.Parcelable;

public class SocialMediaItem implements Parcelable {
    private int mImageResource;
    private String mTextName;
    private String mTextCategory;
    private String mTextDescription;

    public SocialMediaItem(int mImageResource, String mTextName, String mTextCategory, String mTextDescription) {
        this.mImageResource = mImageResource;
        this.mTextName = mTextName;
        this.mTextCategory = mTextCategory;
        this.mTextDescription = mTextDescription;
    }

    protected SocialMediaItem(Parcel in) {
        mImageResource = in.readInt();
        mTextName = in.readString();
        mTextCategory = in.readString();
        mTextDescription = in.readString();
    }

    public static final Creator<SocialMediaItem> CREATOR = new Creator<SocialMediaItem>() {
        @Override
        public SocialMediaItem createFromParcel(Parcel in) {
            return new SocialMediaItem(in);
        }

        @Override
        public SocialMediaItem[] newArray(int size) {
            return new SocialMediaItem[size];
        }
    };

    //this method was added to handle the click event. In my case, I will change the view to a bigger card view to show the full description of the item
    public void changeDescription(String description)
    {
        mTextDescription = description;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public String getmTextName() {
        return mTextName;
    }

    public void setmTextName(String mTextName) {
        this.mTextName = mTextName;
    }

    public String getmTextCategory() {
        return mTextCategory;
    }

    public void setmTextCategory(String mTextCategory) {
        this.mTextCategory = mTextCategory;
    }

    public String getmTextDescription() {
        return mTextDescription;
    }

    public void setmTextDescription(String mTextDescription) {
        this.mTextDescription = mTextDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mTextName);
        dest.writeString(mTextCategory);
        dest.writeString(mTextDescription);
    }
}
