package com.example.vladka.mymetaphory;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.vladka.mymetaphory.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity
        implements CategoryAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 5;

    // references to RV and Adapter to reset list when Reset in menu is clicked
    private CategoryAdapter mAdapter;
    private RecyclerView mNumberList;

    // declaring of TextView variable
    TextView mCategoriesListTextView;

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberList = findViewById(R.id.rv_categories_list);

        // ! LayoutManager !!!!!!!
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumberList.setLayoutManager(layoutManager);

        // for better performance - changes in content do nto change the child layout size in RV
        mNumberList.setHasFixedSize(true);

        mAdapter = new CategoryAdapter(NUM_LIST_ITEMS, this);

        mNumberList.setAdapter(mAdapter);

        //Set whether a custom view should be displayed, if set.
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //Set whether an activity title/subtitle should be displayed.
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView v = (TextView) getLayoutInflater().inflate(R.layout.custom_title_view, null);
        getSupportActionBar().setCustomView(v);

        mCategoriesListTextView = findViewById (R.id.tv_categories_names);

        // use static method and store the categories names in a String array
        String [] categoryNames = Categories.getCategoriesNames();

        for (String categoryName : categoryNames) {
            mCategoriesListTextView.append(categoryName + "\n\n\n\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuSelectedItem = item.getItemId();

        switch (menuSelectedItem) {

            case R.id.search_menu:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);

            case R.id.categories_menu:
                Context context = MainActivity.this;
                String message = "You've just tried to explore categories";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            case R.id.refresh_menu:
                mAdapter = new CategoryAdapter(NUM_LIST_ITEMS, this);
                mNumberList.setAdapter(mAdapter);
                return true;
        }

        // super = executes default functionality in the base class
        // after calling base class I can override my own logic
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        if(mToast!=null) {
            mToast.cancel();
        }

        if (clickedItemIndex == 0) {
            String toastMessage = "Item Wisdom #" + clickedItemIndex + " clicked.";
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        }

        if (clickedItemIndex == 1) {
            String toastMessage = "Item Universe #" + clickedItemIndex + " clicked.";
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        }

        if (clickedItemIndex == 2) {
            String toastMessage = "Item Fun #" + clickedItemIndex + " clicked.";
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        }

        if (clickedItemIndex == 3) {
            String toastMessage = "Item Life #" + clickedItemIndex + " clicked.";
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        }

        if (clickedItemIndex == 4) {
            String toastMessage = "Item Happiness #" + clickedItemIndex + " clicked.";
            mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);

        }

        mToast.show();

        // intent to start new activity when item in list is clicked
        Context context = MainActivity.this;

        Class destinationActivity = DetailCategoryActivity.class;

        Intent intent = new Intent(context, destinationActivity);

        startActivity(intent);
    }
}

