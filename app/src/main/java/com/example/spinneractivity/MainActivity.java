package com.example.spinneractivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SocialMediaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<SocialMediaItem> mSocialMediaList;
    private EditText editTextInsert;
    private EditText editTextRemove;
    private Button buttonInsert;
    private Button buttonRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();

        buildRecyclerView();

        setButtons();

    }

    public void insertItem(int position)
    {
        mSocialMediaList.add(position, new SocialMediaItem(R.drawable.greenblack, "New Item at Position"+position, "Hat/Boots", "New Items Available Daily"));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position)
    {
        mSocialMediaList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

   public void changeItem(int position, String text)
   {
       mSocialMediaList.get(position).changeDescription(text);
       mAdapter.notifyItemChanged(position);
   }

    private void createExampleList() {
        mSocialMediaList = new ArrayList<>();
        mSocialMediaList.add(new SocialMediaItem(R.drawable.greenblack, "Green on Black", "Long-Top", "Long sleeve workout sweatshirt"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.greyblack, "Gery on Black", "Pants", "Jogging pants"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.mustardred, "Mustard and Red", "Long-Top", "Long sleeve workout sweatshirt"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.greenblack, "Green on Black", "Hat", "Flat cap"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.navywhite, "White on Navy", "Long-Top", "Long sleeve workout sweatshirt"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.greenblack, "Green on Black", "Pants", "Jogging pants"));
        mSocialMediaList.add(new SocialMediaItem(R.drawable.mustardred, "Red and Yellow", "Hat", "Flat cap"));
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
//        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SocialMediaAdapter(mSocialMediaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SocialMediaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //add new Intent
                Intent intent = new Intent(MainActivity.this, DetailCardActivity.class);
                intent.putExtra("New Item", mSocialMediaList.get(position));

                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons()
    {
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.social_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}