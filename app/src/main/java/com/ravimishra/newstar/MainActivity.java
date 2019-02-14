package com.ravimishra.newstar;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FragmentTechNews.OnFragmentInteractionListener,
        FragmentScienceNews.OnFragmentInteractionListener,
        FragmentSportsNews.OnFragmentInteractionListener,
        FragmentHeathcareNews.OnFragmentInteractionListener,
        FramentTopNews.OnFragmentInteractionListener, View.OnClickListener,
        FragmentSearchNews.OnFragmentInteractionListener,
        FragmentEntertainmentNews.OnFragmentInteractionListener

{

    RecyclerView.LayoutManager layoutManager;

    RelativeLayout errorLayout;
    TabLayout tabs;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabs= (TabLayout) findViewById(R.id.tabs);
        // btnRetry = findViewById(R.id.btnRetry);
        errorLayout = findViewById(R.id.error_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);

      final  SearchView searchView=(SearchView) menu.findItem(R.id.action_search).getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      searchView.setQueryHint("Search News here...");
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              if(query.length()>0){
               //   hideLayout();
                  LoadFragSearch(query);
              }
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
             // hideLayout();
              //LoadFragSearch();
              return false;
          }
      });
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FramentTopNews(), "Top");

        adapter.addFragment(new FragmentSportsNews(), "Sports");
        adapter.addFragment(new FragmentTechNews(), "Technology");
        adapter.addFragment(new FragmentHeathcareNews(), "Healthcare");
        adapter.addFragment(new FragmentEntertainmentNews(), "Entertainment");
        adapter.addFragment(new FragmentScienceNews(), "Science");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onClick(View v) {

    }

    public void hideLayout(){
   tabs.setVisibility(View.GONE);
   viewPager.setVisibility(View.GONE);
    }

    public void showLayout(){
        tabs.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
    }
public void LoadFragSearch(String query){

    Bundle bundle = new Bundle();
    bundle.putString("query",query);
    Fragment frag = new FragmentSearchNews();
    frag.setArguments(bundle);
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    hideLayout();
    transaction.replace(R.id.mainFrame,frag,"Search Fragment").addToBackStack(null);
    transaction.commit();
}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
      else  if (getSupportFragmentManager().getBackStackEntryCount() >= 0) {
            showLayout();
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}
