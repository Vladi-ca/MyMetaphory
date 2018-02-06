package com.example.vladka.mymetaphory.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Vladka on 22/01/2018.
 */

public class NetworkUtils {

    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";

    private static final String STATIC_JSON_URL =
            "https://andfun-weather.udacity.com/staticweather";

    final static String PARAM_QUERY = "q";

    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    public static URL buildUrl(String githubSearchQuery) {
        // builds Github query
        // buldUpon = to obtain a builder representing an existing URI, constructs a new builder, copying the attributes from this Uri
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()

                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            // printStackTrace() = tells me where and what happened in the code when Exception is diagnosed, a method of the class Throwable of java.lang package
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException {

        // openConnection() = manipulate parameters that affect the connection to the remote resource
        // and connection object is created by invoking the method on a URL
        // it does not talk to network yet, it just creates the http URL connection object
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            // getInputStream = returns an input stream that reads from this open connection
            InputStream in = urlConnection.getInputStream();

            /* sets the delimiter of this Scanner object
            its helpfull if I would use Scanner to split String into multiple tokens
             The scanner class is used to split the String and output
             the tokens in the console e.g. "Vladi/Female/30" gives output if useDelimeter("/")
             then gives: Vladi
                         Female
                         30
            */
            // Scanner tokenised stream
            Scanner scanner = new Scanner(in);

            /* we force the scanner to read entire contents of the stream into the next token stream
             it buffers the data!!! = pulls the data from the network in small chunks,
             but because http is not required to give us a content size thus! our code
             needs to handle buffer of different sizes. It allocates and deallocates the buffers
             as needed. Also handles character encoding for us.
             It translates from UTF-8 (default encoding for JSON and JS to UTF-16 (format used by Android)
             */
            scanner.useDelimiter("\\A");

            // prints the tokenised Strings
            /* The next() and hasNext() methods and their primitive-type companion methods
            (such as nextInt() and hasNextInt()) first skip any input that matches the
            delimiter pattern, and then attempt to return the next token. Both hasNext and
            next methods may block waiting for further input. Whether a hasNext method
             blocks has no connection to whether or not its associated next method will block.
             */
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
