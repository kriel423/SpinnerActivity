package com.example.spinneractivity;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SocialMediaAdapter extends ArrayAdapter {

    public SocialMediaAdapter(Context context, ArrayList<SocialMediaItem> socialMediaList)
    {
        super(context, 0, socialMediaList);
    }


}
