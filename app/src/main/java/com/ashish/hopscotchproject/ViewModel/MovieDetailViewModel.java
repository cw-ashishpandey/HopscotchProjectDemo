package com.ashish.hopscotchproject.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ashish.hopscotchproject.app.AppController;
import com.ashish.hopscotchproject.model.moviedetail.MovieDetailModel;

/**
 * Created by amit.chauhan on 05-Sep-16.
 */
public class MovieDetailViewModel extends BaseObservable {
    Context mContext;
    MovieDetailModel movieDetail;

    public MovieDetailViewModel(Context context, MovieDetailModel movieDetail) {
        mContext = context;
        this.movieDetail = movieDetail;
    }

    @Bindable
    public String getDescription() {
        return movieDetail.getSynopsis();
    }

    @Bindable
    public String getUrl() {
        return movieDetail.getPosters().getDetailed();
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
