package com.ashish.hopscotchproject.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ashish.hopscotchproject.app.AppController;
import com.ashish.hopscotchproject.model.movielist.Movies;
import com.ashish.hopscotchproject.view.activity.MainActivity;
import com.ashish.hopscotchproject.view.fragment.ImageCrousalFragment;
import com.ashish.hopscotchproject.view.fragment.MovieDetailFragment;

/**
 * Created by amit.chauhan on 05-Sep-16.
 */
public class MovieListViewModel extends BaseObservable {
    Context mContext;
    Movies movieElement;

    public MovieListViewModel(Context context, Movies movieElement) {
        mContext = context;
        this.movieElement = movieElement;
    }

    @Bindable
    public String getTitle() {

        return movieElement.getTitle();
    }

    @Bindable
    public String getYear() {
        return movieElement.getYear().toString();
    }

    @Bindable
    public String getUrl() {
        return movieElement.getPosters().getProfile().toString();
    }

    public View.OnClickListener onClickDetail() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDetailFragment();
            }
        };
    }

    private void loadDetailFragment() {
        MovieDetailFragment detailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString("id", movieElement.getId());
        detailFragment.setArguments(args);
        ((MainActivity) mContext).loadFragment(detailFragment);
    }

    public View.OnClickListener onClickMovieImage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageCrousalFragment crousalFragment = new ImageCrousalFragment();
                Bundle args = new Bundle();
                args.putString("url", movieElement.getPosters().getProfile());
                crousalFragment.setArguments(args);
                ((MainActivity) mContext).loadFragment(crousalFragment);
                ((MainActivity) mContext).loadFragment(crousalFragment);
            }
        };
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(NetworkImageView iv, String url) {
        if (!TextUtils.isEmpty(url)) {
            ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
            mImageLoader.get(url, ImageLoader.getImageListener(iv,
                    android.R.drawable
                            .ic_dialog_alert, android.R.drawable
                            .ic_dialog_alert));
            iv.setImageUrl(url, mImageLoader);
        }

        //notifyPropertyChanged();
    }


}
