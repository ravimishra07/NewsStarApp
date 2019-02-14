package com.ravimishra.newstar;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ravimishra.newstar.Model.Articles;
import com.ravimishra.newstar.Model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentScienceNews  extends Fragment {
    Context context;
    TextView ty_error;
    ImageView iv_error;
    RecyclerView recyclerViewTopNews;
    FrameLayout frameScienceNews;
    FragmentScienceNews.OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    RvAdapter rvAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout errorLayout;
    Button btnRetry;
    private ShimmerFrameLayout mShimmerViewContainer;
    private List<Articles> articles = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_fragment_science_news, container, false);

        frameScienceNews= view.findViewById(R.id.frameScienceNews);
        iv_error = view.findViewById(R.id.iv_error);
        ty_error = view.findViewById(R.id.tv_error);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        //  btnRetry=view.findViewById(R.id.btnRetry);
        errorLayout = view.findViewById(R.id.error_layout);
        recyclerView = view.findViewById(R.id.recylerviewTopNews);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // recyclerView.setAdapter(rvAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mShimmerViewContainer.startShimmerAnimation();
        //  recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        //recyclerView);
        checkNetwork();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkNetwork();
                loadJSON();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        loadJSON();
        return view;
    }


    public void loadJSON() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String country = Utils.getCountry();
        //String q = "india";
        Call<News> call;
        int page=50;
        call = apiInterface.getNews("Science",Constants.API_KEY,page);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {

                    articles = response.body().getArticles();
                    rvAdapter = new RvAdapter(articles, getActivity());
                    recyclerView.setAdapter(rvAdapter);
                    rvAdapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "No result", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void checkNetwork() {
        if (isNetworkAvailable()) {
            ty_error.setVisibility(View.GONE);
            iv_error.setVisibility(View.GONE);
            errorLayout.setVisibility(View.GONE);
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            // loadJSON();
            //      Toast.makeText(this, "connecte4d", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "no conn", Toast.LENGTH_SHORT).show();
            recyclerView.setVisibility(View.GONE);
            ty_error.setVisibility(View.VISIBLE);
            iv_error.setVisibility(View.VISIBLE);
            mShimmerViewContainer.setVisibility(View.GONE);
            errorLayout.setVisibility(View.VISIBLE);
        }
    }

    public boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public FragmentScienceNews() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentScienceNews.OnFragmentInteractionListener) {
            mListener = (FragmentScienceNews.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
