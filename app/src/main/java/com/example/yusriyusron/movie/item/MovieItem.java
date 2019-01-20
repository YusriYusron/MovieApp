package com.example.yusriyusron.movie.item;

import org.json.JSONObject;

public class MovieItem {
    private String judulFilm,gambarFilm,ratingFilm,penontonFilm,bahasaFilm,rilisFilm,overviewFilm;

    public MovieItem(JSONObject object){
        try{
            // Get data from JSON API MovieDB
            String judul = object.getString("title");
            String gambar = object.getString("poster_path");
            String rating = object.getString("vote_average");
            String penonton = object.getString("popularity");
            String bahasa = object.getString("original_language");
            String rilis = object.getString("release_date");
            String overview = object.getString("overview");

            this.judulFilm = judul;
            this.gambarFilm = gambar;
            this.ratingFilm = rating;
            this.penontonFilm = penonton;
            this.bahasaFilm = bahasa;
            this.rilisFilm = rilis;
            this.overviewFilm = overview;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public String getGambarFilm() {
        return gambarFilm;
    }

    public String getRatingFilm() {
        return ratingFilm;
    }

    public String getPenontonFilm() {
        return penontonFilm;
    }

    public String getBahasaFilm() {
        return bahasaFilm;
    }

    public String getRilisFilm() {
        return rilisFilm;
    }

    public String getOverviewFilm() {
        return overviewFilm;
    }
}
