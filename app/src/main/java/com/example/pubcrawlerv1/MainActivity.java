package com.example.pubcrawlerv1;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fragments.AddBeerFragment;
import com.example.fragments.BeerCountFragment;
import com.example.fragments.ListBeerFragment;
import com.example.fragments.ListTescoAlcoholFragment;
import com.example.fragments.SectionsStatePagerAdapter;
import com.example.fragments.StandardMapFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private CustomViewPager mViewPager;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d(TAG, "onCreate: FOUND " + getIntent().getStringExtra("email"));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager viewPager){
        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mSectionsStatePagerAdapter.addFragment(new BeerCountFragment(), "Count");
        mSectionsStatePagerAdapter.addFragment(new AddBeerFragment(), "AddBeer");
        mSectionsStatePagerAdapter.addFragment(new ListBeerFragment(), "ListBeer");
        mSectionsStatePagerAdapter.addFragment(new ListTescoAlcoholFragment(), "ListTesco");
        mSectionsStatePagerAdapter.addFragment(new StandardMapFragment(), "StandardMap");
        Bundle bundle = new Bundle();
        bundle.putString("email", getIntent().getStringExtra("email"));
        for(int i = 0; i < mSectionsStatePagerAdapter.getCount(); i++){
            setEmail(getIntent().getStringExtra("email"));
            mSectionsStatePagerAdapter.getItem(i).setArguments(bundle);
        }
        viewPager.setAdapter(mSectionsStatePagerAdapter);


    }

    public void setViewPager(int fragmentIndex){
        mViewPager.setCurrentItem(fragmentIndex);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void addToForm(String name, String imgUrl){
        Bundle bundleFeatures = new Bundle();
        bundleFeatures.putString("name", name);
        bundleFeatures.putString("img", imgUrl);
        mSectionsStatePagerAdapter.getItem(1).setArguments(bundleFeatures);
        setViewPager(1);

    }

    /**
     * Menu on top right
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.v("onOptionsItemSelected","selected menuitem");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d(TAG, "onNavigationItemSelected: " + id);
        if (id == R.id.nav_add_beer) {
            setViewPager(1);
        } else if (id == R.id.nav_browse_beers) {
            setViewPager(2);
        } else if (id == R.id.nav_chart_beers) {
            setViewPager(0);
        } else if (id == R.id.nav_map_beers) {
            setViewPager(4);
        }else if (id == R.id.nav_tesco) {
            setViewPager(3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public SectionsStatePagerAdapter getmSectionsStatePagerAdapter() {
        return mSectionsStatePagerAdapter;
    }

    public void setmSectionsStatePagerAdapter(SectionsStatePagerAdapter mSectionsStatePagerAdapter) {
        this.mSectionsStatePagerAdapter = mSectionsStatePagerAdapter;
    }

    public CustomViewPager getmViewPager() {
        return mViewPager;
    }

    public void setmViewPager(CustomViewPager mViewPager) {
        this.mViewPager = mViewPager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
