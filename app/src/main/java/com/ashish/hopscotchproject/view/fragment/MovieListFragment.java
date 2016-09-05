package com.ashish.hopscotchproject.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.app.ApiRepository;
import com.ashish.hopscotchproject.app.AppController;
import com.ashish.hopscotchproject.data.remote.HopscotchRequest;
import com.ashish.hopscotchproject.model.movielist.MovieListModel;
import com.ashish.hopscotchproject.util.UtilFactory;
import com.ashish.hopscotchproject.view.activity.MainActivity;
import com.ashish.hopscotchproject.view.adapter.MovieListAdapter;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    @Bind(R.id.et_search)
    EditText etSearch;

    @Bind(R.id.rv_movie_list)
    RecyclerView rvMovieList;

    private MovieListModel listModel;
    private MovieListAdapter listAdapter;
    private String TAG = this.getClass().getSimpleName();


    public MovieListFragment() {
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
    public static MovieListFragment newInstance(String param1) {
        MovieListFragment fragment = new MovieListFragment();
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
        }
        listModel = new MovieListModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        ButterKnife.bind(this, view);
        initListener();
        setUpToolbar();
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        rvMovieList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieList.setHasFixedSize(true);
        listAdapter = new MovieListAdapter(getActivity(), listModel);
        rvMovieList.setAdapter(listAdapter);
    }

    private void setUpToolbar() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(getString(R.string.text_movie_list_title));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_white));
        }
    }

    private void initListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String data = s.toString();
                if (TextUtils.isEmpty(data)) {
                    ((MainActivity) getActivity()).showSnackBar(getString(R.string.error_search));
                } else {
                    downloadUrlVolley(data);
                }
            }
        });
    }

    private void downloadUrlVolley(String query) {
        //UtilFactory.showDialog(getActivity());
        String url = ApiRepository.getMovieListUrl(query);
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
            AppController.getInstance().cancelPendingRequests(TAG);
            AppController.getInstance().addToRequestQueue(request, TAG);
        } else {
            //UtilFactory.hideDialog();
            AppController.getInstance().cancelPendingRequests(TAG);
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
        listModel = gson.fromJson(response, MovieListModel.class);
        listAdapter.setListModel(listModel);
        listAdapter.notifyDataSetChanged();
    }

}
