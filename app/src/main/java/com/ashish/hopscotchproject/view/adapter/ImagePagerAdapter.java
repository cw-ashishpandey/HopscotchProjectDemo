package com.ashish.hopscotchproject.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.ViewModel.MovieCrousalViewModel;
import com.ashish.hopscotchproject.databinding.MovieCrousalListBinding;

/**
 * Created by amit.chauhan on 06-Sep-16.
 */
public class ImagePagerAdapter extends PagerAdapter {

    Context mContext;
    String url;

    public ImagePagerAdapter(Context context, String url) {
        mContext = context;
        this.url = url;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        MovieCrousalListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.movie_crousal_list, container, false);
        binding.setCrousalModel(new MovieCrousalViewModel(mContext, url));
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    @Override
    public int getCount() {
        return (!TextUtils.isEmpty(url)) ? 10 : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
