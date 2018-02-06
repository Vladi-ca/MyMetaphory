package com.example.vladka.mymetaphory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UniverseActivity extends AppCompatActivity {

    private TextView mUniverseCategoryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);

        mUniverseCategoryTextView = findViewById(R.id.universe_header_tv);
    }
}
