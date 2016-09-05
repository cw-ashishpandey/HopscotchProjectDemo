package com.ashish.hopscotchproject.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.view.activity.MainActivity;
import com.ashish.hopscotchproject.view.adapter.ImagePagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageCrousalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageCrousalFragment extends Fragment implements ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    String url;

    private float MIN_SCALE = 1f - 1f / 7f;
    private float MAX_SCALE = 1f;

    @Bind(R.id.vp_crousal)
    ViewPager vpCrousal;

    @Bind(R.id.view_pager_count_dots)
    LinearLayout viewPagerIndicator;


    ImagePagerAdapter adapter;
    int dotsCount;
    ImageView[] dots;


    public ImageCrousalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment MovieListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageCrousalFragment newInstance(String param1) {
        ImageCrousalFragment fragment = new ImageCrousalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            url = getArguments().getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crousal, container, false);
        ButterKnife.bind(this, view);
        setUpToolbar();
        setUpViewPager();
        setUpDotHighlight();
        return view;
    }

    private void setUpViewPager() {

        adapter = new ImagePagerAdapter(getActivity(), url);
        vpCrousal.setAdapter(adapter);
        vpCrousal.setCurrentItem(0);
        vpCrousal.setOnPageChangeListener(this);
        //rvMovieList.setAdapter(new MovieListAdapter(getActivity(),));
    }

    private void setUpToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getString(R.string.text_movie_detail_title));
            actionBar.setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
            ((MainActivity) getActivity()).toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).onBackPressed();
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       /* for (int i = 0; i < vpCrousal.getChildCount(); i++) {
            View cardView = vpCrousal.getChildAt(i);
            int itemPosition = (Integer) cardView.getTag();

            if (itemPosition == position) {
                cardView.setScaleX(MAX_SCALE - positionOffset / 7f);
                cardView.setScaleY(MAX_SCALE - positionOffset / 7f);
            }

            if (itemPosition == (position + 1)) {
                cardView.setScaleX(MIN_SCALE + positionOffset / 7f);
                cardView.setScaleY(MIN_SCALE + positionOffset / 7f);
            }
        }*/
    }

    private void setUpDotHighlight() {

        dotsCount = adapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            viewPagerIndicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
