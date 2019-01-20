package com.example.yusriyusron.movie.activity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yusriyusron.movie.R;
import com.example.yusriyusron.movie.adapter.MovieAdapter;
import com.example.yusriyusron.movie.item.MovieItem;
import com.example.yusriyusron.movie.loader.AsyncTaskLoaderMovie;

import java.util.ArrayList;

import cz.msebera.android.httpclient.util.TextUtils;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItem>> {

    private ListView listViewFilm;
    private MovieAdapter movieAdapter;
    private EditText textCari;
    private Button btnCari;

    static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();

        textCari = findViewById(R.id.cari);
        btnCari = findViewById(R.id.btn_cari);
        btnCari.setOnClickListener(listener);

        listViewFilm = findViewById(R.id.list_movie);
        listViewFilm.setAdapter(movieAdapter);
        listViewFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                MovieItem item = (MovieItem) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);

                intent.putExtra(DetailMovieActivity.EXTRA_GAMBAR, item.getGambarFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_JUDUL,item.getJudulFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_RATING,item.getRatingFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_PENONTON,item.getPenontonFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_BAHASA,item.getBahasaFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_RILIS,item.getRilisFilm());
                intent.putExtra(DetailMovieActivity.EXTRA_OVERVIEW,item.getOverviewFilm());

                startActivity(intent);
            }
        });

        String judul = textCari.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, judul);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<MovieItem>> onCreateLoader(int id, Bundle args) {
        String judul = "";
        if (args != null) {
            judul = args.getString(EXTRA_MOVIE);
        }
        return new AsyncTaskLoaderMovie(this, judul);
    }


    @Override
    public void onLoadFinished(Loader<ArrayList<MovieItem>> loader, ArrayList<MovieItem> data) {
        movieAdapter.setDataFilm(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItem>> loader) {
        movieAdapter.setDataFilm(null);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String judul = textCari.getText().toString();

            if (TextUtils.isEmpty(judul)) {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIE, judul);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}