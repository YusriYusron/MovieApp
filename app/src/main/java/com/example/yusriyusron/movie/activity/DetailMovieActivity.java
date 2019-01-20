package com.example.yusriyusron.movie.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yusriyusron.movie.R;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {

    public static String EXTRA_GAMBAR = "EXTRA_GAMBAR";
    public static String EXTRA_JUDUL = "EXTRA_JUDUL";
    public static String EXTRA_RATING = "EXTRA_RATING";
    public static String EXTRA_PENONTON= "EXTRA_PENONTON";
    public static String EXTRA_BAHASA = "EXTRA_BAHASA";
    public static String EXTRA_RILIS= "EXTRA_RILIS";
    public static String EXTRA_OVERVIEW = "EXTRA_OVERVIEW";

    private TextView detailJudul,detailRating,
            detailPenonton,detailBahasa,detailRilis,detailOverview;
    private ImageView detailGambar;
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        // Read ID from layout
        detailGambar = findViewById(R.id.detail_gambar_film);
        detailJudul = findViewById(R.id.detail_judul_film);
        detailRating = findViewById(R.id.detail_rating);
        detailPenonton = findViewById(R.id.detail_penonton);
        detailBahasa = findViewById(R.id.detail_bahasa);
        detailRilis = findViewById(R.id.detail_rilis);
        detailOverview = findViewById(R.id.detail_overview);

        // Get data from MainActivity.java
        String gambar = getIntent().getStringExtra(EXTRA_GAMBAR);
        String judul = getIntent().getStringExtra(EXTRA_JUDUL);
        String rating = getIntent().getStringExtra(EXTRA_RATING);
        String penonton = getIntent().getStringExtra(EXTRA_PENONTON);
        String bahasa = getIntent().getStringExtra(EXTRA_BAHASA);
        String rilis = getIntent().getStringExtra(EXTRA_RILIS);
        String ovewview = getIntent().getStringExtra(EXTRA_OVERVIEW);

        // Set data from MainActivity.java to ID from layout
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w154/"+gambar)
                .into(detailGambar);
        detailJudul.setText(judul);
        detailRating.setText(rating);
        detailPenonton.setText(penonton);
        detailBahasa.setText(bahasa);
        detailRilis.setText(rilis);
        detailOverview.setText(ovewview);
    }
}
