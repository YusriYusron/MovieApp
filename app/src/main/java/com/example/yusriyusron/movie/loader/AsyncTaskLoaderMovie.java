package com.example.yusriyusron.movie.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.example.yusriyusron.movie.item.MovieItem;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AsyncTaskLoaderMovie extends AsyncTaskLoader<ArrayList<MovieItem>> {

    private ArrayList<MovieItem> dataFilm;
    private boolean result = false;

    private String kumpulanJudul;

    public AsyncTaskLoaderMovie(final Context context, String kumpulanJudul) {
        super(context);
        onContentChanged();
        this.kumpulanJudul = kumpulanJudul;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()){
            forceLoad();
        }else {
            deliverResult(dataFilm);
        }
    }

    @Override
    public void deliverResult(ArrayList<MovieItem> data) {
        dataFilm = data;
        result = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (result){
            dataFilm = null;
            result = false;
        }
    }

    private static final String API_KEY = "c1c287ec318c5d212f5d101e39ed3220";

    @Override
    public ArrayList<MovieItem> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItem> movieItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&language=en-US&query="+kumpulanJudul;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject film = list.getJSONObject(i);
                        MovieItem movieItem = new MovieItem(film);
                        movieItems.add(movieItem);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return movieItems;
    }
}
