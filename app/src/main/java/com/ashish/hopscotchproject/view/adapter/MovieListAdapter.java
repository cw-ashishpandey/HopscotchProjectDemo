package com.ashish.hopscotchproject.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.ViewModel.MovieListViewModel;
import com.ashish.hopscotchproject.databinding.MovieListContentBinding;
import com.ashish.hopscotchproject.model.movielist.MovieListModel;

/**
 * Created by amit.chauhan on 04-Sep-16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {


    private Context mContext;
    private MovieListModel listModel;

    public MovieListAdapter(Context context, MovieListModel listModel) {
        mContext = context;
        setListModel(listModel);
    }

    public void setListModel(MovieListModel listModel) {
        this.listModel = listModel;
    }


    @Override
    public int getItemCount() {
        return listModel != null && listModel.getMovies() != null ? listModel.getMovies().length : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MovieListContentBinding listBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.movie_list_content,
                parent,
                false);
        return new ViewHolder(listBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieListContentBinding movieListBinding = (MovieListContentBinding) holder.binding;
        MovieListViewModel movieModel = new MovieListViewModel(mContext, listModel.getMovies()[position]);
        //movieModel.loadImage(movieListBinding.ivMovie);
        movieListBinding.setListModel(movieModel);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private MovieListContentBinding binding;

        public ViewHolder(MovieListContentBinding binding) {
            super(binding.containerItem);
            this.binding = binding;
        }


    }

}
