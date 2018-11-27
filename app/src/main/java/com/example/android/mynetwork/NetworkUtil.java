package com.example.android.mynetwork;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class NetworkUtil {
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?"; // Base URI for the Books API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type
    private static final String LOG_TAG = NetworkUtil.class.getSimpleName();

    public static String getBookInfo(String q) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer();
        String line;
        String bookJason = null;
        try {
            Uri uriBuilder = Uri.parse(BOOK_BASE_URL).buildUpon().appendQueryParameter(QUERY_PARAM, q)
                    .appendQueryParameter(MAX_RESULTS, "10").appendQueryParameter(PRINT_TYPE, "books").build();
            URL req = new URL(uriBuilder.toString());
            httpURLConnection = (HttpURLConnection) req.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            if (inputStream == null)
                return null;
            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            if (buffer.length() == 0)
                return null;
            bookJason = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("aa",bookJason);
        return bookJason;
    }
}
