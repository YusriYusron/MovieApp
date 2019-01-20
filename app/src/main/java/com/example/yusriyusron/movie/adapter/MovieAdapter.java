package com.example.yusriyusron.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yusriyusron.movie.R;
import com.example.yusriyusron.movie.item.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItem> dataFilm = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    // Constructor
    public MovieAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Setter Data Film
    public void setDataFilm(ArrayList<MovieItem> items) {
        dataFilm.clear();
        dataFilm = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item){
        dataFilm.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        dataFilm.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (dataFilm == null){
            return 0;
        }
        return dataFilm.size();
    }

    @Override
    public MovieItem getItem(int position) {
        return dataFilm.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.item_row_movies,null);

            viewHolder.judulFilm = view.findViewById(R.id.judul_film);
            viewHolder.deskripsiFilm = view.findViewById(R.id.deskripsi_film);
            viewHolder.gambarFilm = view.findViewById(R.id.image_movies);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.judulFilm.setText(dataFilm.get(position).getJudulFilm());
        viewHolder.deskripsiFilm.setText(dataFilm.get(position).getOverviewFilm());
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w154/"+dataFilm.get(position).getGambarFilm())
                .into(viewHolder.gambarFilm);
        return view;
    }

    private static class ViewHolder{
        private TextView judulFilm,deskripsiFilm;
        private ImageView gambarFilm;
    }
}
