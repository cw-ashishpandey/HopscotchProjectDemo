package com.ashish.hopscotchproject.ViewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ashish.hopscotchproject.app.AppController;

/**
 * Created by amit.chauhan on 05-Sep-16.
 */
public class MovieCrousalViewModel extends BaseObservable {
    Context mContext;
    String url;

    public MovieCrousalViewModel(Context context, String url) {
        mContext = context;
        this.url = url;
    }

    @Bindable
    public String getUrl() {
        return url;
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
