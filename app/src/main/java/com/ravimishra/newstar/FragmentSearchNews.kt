package com.ravimishra.newstar

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.facebook.shimmer.ShimmerFrameLayout
import com.ravimishra.newstar.ApiClient.apiClient
import com.ravimishra.newstar.Model.Articles
import com.ravimishra.newstar.Model.News
import com.ravimishra.newstar.Utils.country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSearchNews : Fragment() {
    var ty_error: TextView? = null
    var iv_error: ImageView? = null
    var recyclerViewTopNews: RecyclerView? = null
    var frameLayoutSeacrchNews: FrameLayout? = null
    var mListener: OnFragmentInteractionListener? = null
    var recyclerView: RecyclerView? = null
    var rvAdapter: RvAdapter? = null
    var swipeRefreshLayout: SwipeRefreshLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var errorLayout: RelativeLayout? = null
    var btnRetry: Button? = null
    private var mShimmerViewContainer: ShimmerFrameLayout? = null
    private var articles: List<Articles> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val view = inflater.inflate(R.layout.fragment_fragment_search_news, container, false)
        frameLayoutSeacrchNews = view.findViewById(R.id.searchNewsLayout)
        iv_error = view.findViewById(R.id.iv_error)
        ty_error = view.findViewById(R.id.tv_error)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container)
        //  btnRetry=view.findViewById(R.id.btnRetry);
        errorLayout = view.findViewById(R.id.error_layout)
        recyclerView = view.findViewById(R.id.recylerviewTopNews)
        layoutManager = LinearLayoutManager(activity)
        recyclerView?.setLayoutManager(layoutManager)
        recyclerView?.setItemAnimator(DefaultItemAnimator())
        // recyclerView.setAdapter(rvAdapter);
        recyclerView?.setNestedScrollingEnabled(false)
        recyclerView?.setItemAnimator(DefaultItemAnimator())
        mShimmerViewContainer?.startShimmer()
        //  recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        //recyclerView);
        checkNetwork()
        swipeRefreshLayout?.setOnRefreshListener(OnRefreshListener {
            checkNetwork()
            loadJSON()
            swipeRefreshLayout?.setRefreshing(false)
        })
        loadJSON()
        return view
    }

    fun loadJSON() {
        val apiInterface = apiClient.create(ApiInterface::class.java)
        val country = country
        //String q = "india";
        val call: Call<News>
        val strtext = arguments?.getString("query")
        call = apiInterface.getNews(strtext, Constants.API_KEY, 50)
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful && response.body()!!.articles != null) {
                    articles = response.body()!!.articles
                    rvAdapter = RvAdapter(articles, activity!!)
                    recyclerView!!.adapter = rvAdapter
                    rvAdapter!!.notifyDataSetChanged()
                    mShimmerViewContainer!!.stopShimmer()
                    mShimmerViewContainer!!.visibility = View.GONE
                } else {
                    Toast.makeText(activity, "No result", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {}
        })
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri?)
    }

    fun checkNetwork() {
        if (isNetworkAvailable) {
            ty_error!!.visibility = View.GONE
            iv_error!!.visibility = View.GONE
            errorLayout!!.visibility = View.GONE
            mShimmerViewContainer!!.visibility = View.VISIBLE
            recyclerView!!.visibility = View.VISIBLE
            // loadJSON();
            //      Toast.makeText(this, "connecte4d", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "no connection", Toast.LENGTH_SHORT).show()
            recyclerView!!.visibility = View.GONE
            ty_error!!.visibility = View.VISIBLE
            iv_error!!.visibility = View.VISIBLE
            mShimmerViewContainer!!.visibility = View.GONE
            errorLayout!!.visibility = View.VISIBLE
        }
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager =
                activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
                .isConnected
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnFragmentInteractionListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }
}