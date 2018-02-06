package com.example.vladka.mymetaphory;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vladka.mymetaphory.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mSearchResults;

    private TextView mUrlDisplayTextView;

    TextView mErrorMessageResults;

    ProgressBar mLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchBoxEditText = findViewById(R.id.et_search_box);

        mSearchResults = findViewById(R.id.tv_github_search_results_json);

        mUrlDisplayTextView = findViewById(R.id.tv_url_display);

        mErrorMessageResults = findViewById(R.id.tv_error_message_display);

        // then show it in onPreExecute !!!
        mLoadingProgressBar = findViewById(R.id.loading_indicator);

        /**
         * This method retrieves the search text from the EditText, constructs the
         * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
         * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
         * our {@link GithubQueryTask}
         */

    }

    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();

        // !!! variable type URL githubSearchUrl !!!
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());

        // use my AsyncTask class GithubQueryTask and execute githubSearchUrl
        new GithubQueryTask().execute(githubSearchUrl);
    }

    // Add some helper methods after creating error message text view
    // this one hides error message and show the results
    private void showJsonDataView() {
        mErrorMessageResults.setVisibility(View.INVISIBLE);
        mSearchResults.setVisibility(View.VISIBLE);
    }

    // this one show error message and hides results, which we will show on post execute if results are not ok
    private void showErrorMessage() {
        mErrorMessageResults.setVisibility(View.VISIBLE);
        mSearchResults.setVisibility(View.INVISIBLE);
    }

    public void onClickShareTextButton(View view) {
        TextView urlIwantToShare = mUrlDisplayTextView;
    }

    /*make AsyncTask. <URL, Void, String> = Our execute and doInBackground method will take URLs,
    Void = I ll not make use of published progress,
     String = I ll return a String containing the contents of the query*/
    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        // show loading bar and than hide in onPostExecute
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgressBar.setVisibility(View.VISIBLE);

            mUrlDisplayTextView.setVisibility(View.INVISIBLE);

            mSearchResults.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {

            URL searchForUrl = params[0];
            String githubSearchResults = null;

            try {
                // in NetworkUtils class call method getResponseFromHttpUrl and pass in
                // searchUrl
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchForUrl);
            } catch (IOException e) {

                // e.printStackTrace() = tool for diagnosing an Exception. It tells me
                // what happened and where in the code it happened
                /*First your main method will be called and inserted to stack,
                then the second method will be called and inserted to the stack
                in LIFO order and if any error occurs somewhere inside any method
                then this stack will help to identify that method.*/
                /*printStacktrace() is a method of the class Throwable of java.lang package.
                It prints several lines in the output console. The first line consists of several strings.
                 It contains the name of the Throwable sub-class & the package information. From second line onwards,
                 it describes the error position/line number beginning with "at".*/
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            mLoadingProgressBar.setVisibility(View.INVISIBLE);

            mUrlDisplayTextView.setVisibility(View.VISIBLE);

            mSearchResults.setVisibility(View.VISIBLE);


            if (githubSearchResults != null && !githubSearchResults.equals("")) {

                showJsonDataView();
                mSearchResults.setText(githubSearchResults);

            } else {
                // method where I stated that mErrorMessageResults will be visible, try with airplane mode
                showErrorMessage();
            }
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
                makeGithubSearchQuery();
                return true;

            case R.id.categories_menu:
                Context context = SearchActivity.this;

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                String message = "You've just tried to explore categories";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        // super = executes default functionality in the base class
        // after calling base class I can override my own logic
        return super.onOptionsItemSelected(item);
    }
}


