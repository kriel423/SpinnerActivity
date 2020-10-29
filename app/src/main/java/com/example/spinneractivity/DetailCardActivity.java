package com.example.spinneractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_card);

        Intent intent = getIntent();
        SocialMediaItem socialMediaItem = intent.getParcelableExtra("Social Media Item");

        int imageRes = socialMediaItem.getmImageResource();
        String name = socialMediaItem.getmTextName();
        String category = socialMediaItem.getmTextCategory();
        String description = socialMediaItem.getmTextDescription();

        ImageView imageView = findViewById(R.id.detail_image);
        imageView.setImageResource(imageRes);

        TextView textName = findViewById(R.id.text_title_detailed);
        textName.setText(name);

        TextView textCategory = findViewById(R.id.text_view_category);
        textCategory.setText(category);

        TextView textDescription = findViewById(R.id.text_view_description);
        textDescription.setText(description);
    }
}