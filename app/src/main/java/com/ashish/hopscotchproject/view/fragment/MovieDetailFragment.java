package com.ashish.hopscotchproject.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.ViewModel.MovieDetailViewModel;
import com.ashish.hopscotchproject.app.ApiRepository;
import com.ashish.hopscotchproject.app.AppController;
import com.ashish.hopscotchproject.data.remote.HopscotchRequest;
import com.ashish.hopscotchproject.databinding.FragmentMovieDetailBinding;
import com.ashish.hopscotchproject.model.moviedetail.MovieDetailModel;
import com.ashish.hopscotchproject.util.UtilFactory;
import com.ashish.hopscotchproject.view.activity.MainActivity;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    String movieId;
    FragmentMovieDetailBinding movieDetailBinder;


    public MovieDetailFragment() {
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
    public static MovieDetailFragment newInstance(String param1) {
        MovieDetailFragment fragment = new MovieDetailFragment();
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
            movieId = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        movieDetailBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        //MovieDetailViewModel detailViewModel = new MovieDetailViewModel(getActivity(), new MovieDetailModel());
        //movieDetailBinder.setDetailModel(detailViewModel);
        setUpToolbar();
        downloadUrlVolley();
        return movieDetailBinder.getRoot();
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

    private void downloadUrlVolley() {
        //UtilFactory.showDialog(getActivity());
        String url = ApiRepository.getMovieDetailUrl(movieId);
        if (UtilFactory.isNetworkConnected(getActivity())) {
            StringRequest request = new HopscotchRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //UtilFactory.hideDialog();
                    if (!TextUtils.isEmpty(response)) {
                        loadData(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //UtilFactory.hideDialog();
                    ((MainActivity) getActivity()).showSnackBar(getString(R.string.error_message));
                }
            });
            AppController.getInstance().addToRequestQueue(request);
        } else {
            //UtilFactory.hideDialog();
            Cache.Entry entry = AppController.getInstance().getRequestQueue().getCache().get(url);
            if (entry != null) {
                loadData(new String(entry.data));
            } else {
                ((MainActivity) getActivity()).showSnackBar(getString(R.string.message_data_not_available));
            }
        }

    }

    private void loadData(String response) {
        Gson gson = new Gson();
        MovieDetailModel detailModel = gson.fromJson(response, MovieDetailModel.class);
        MovieDetailViewModel detailViewModel = new MovieDetailViewModel(getActivity(), detailModel);
        movieDetailBinder.setDetailModel(detailViewModel);

    }

}
