package com.errorstation.wallpaper.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.errorstation.wallpaper.R;
import com.errorstation.wallpaper.adapters.ViewPagerAdapter;
import com.errorstation.wallpaper.fragments.EditorChoiceFragment;
import com.errorstation.wallpaper.fragments.FeaturedFragment;
import com.errorstation.wallpaper.fragments.LikedFragment;
import com.errorstation.wallpaper.fragments.PopularFragment;
import com.errorstation.wallpaper.fragments.TrendingFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FeaturedFragment(), "Featured");
        adapter.addFragment(new EditorChoiceFragment(), "Editor Choice");
        adapter.addFragment(new PopularFragment(), "Popular");
        adapter.addFragment(new TrendingFragment(), "Trending");
        adapter.addFragment(new LikedFragment(), "Liked");
        viewPager.setAdapter(adapter);
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

        if (id == R.id.abstractND) {
            startTransection(getString(R.string.name_abstract));

        } else if (id == R.id.animalsND) {
            startTransection(getString(R.string.name_animals));

        } else if (id == R.id.architectureND) {
            startTransection(getString(R.string.name_architecture));

        } else if (id == R.id.beachND) {
            startTransection(getString(R.string.name_beach));

        } else if (id == R.id.bikeND) {
            startTransection(getString(R.string.name_bikes));

        } else if (id == R.id.businessND) {
            startTransection(getString(R.string.name_business));

        } else if (id == R.id.cityND) {
            startTransection(getString(R.string.name_city));

        } else if (id == R.id.creativeND) {
            startTransection(getString(R.string.name_creative));

        } else if (id == R.id.flowersND) {
            startTransection(getString(R.string.name_flowers));

        } else if (id == R.id.foodND) {
            startTransection(getString(R.string.name_food));

        } else if (id == R.id.gamesND) {
            startTransection(getString(R.string.name_games));

        } else if (id == R.id.macroND) {
            startTransection(getString(R.string.name_macro));

        } else if (id == R.id.natureND) {
            startTransection(getString(R.string.name_nature));

        } else if (id == R.id.spaceND) {
           startTransection(getString(R.string.name_space));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTransection(String categoryName)
    {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("category", categoryName);
        startActivity(intent);
    }

}
