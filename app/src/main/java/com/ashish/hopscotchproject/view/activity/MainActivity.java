package com.ashish.hopscotchproject.view.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.ashish.hopscotchproject.R;
import com.ashish.hopscotchproject.util.UtilFactory;
import com.ashish.hopscotchproject.view.fragment.MovieListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.fl_contentmain)
    FrameLayout flContentMain;
    @Bind(R.id.cl_main)
    CoordinatorLayout clMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        loadFragment(new MovieListFragment());
    }

    public void loadFragment(Fragment fragment) {
        UtilFactory.hideKeybord(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_contentmain, fragment).addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/

    public void showSnackBar(String message) {
        UtilFactory.showSnackBar(clMain, message);
    }

}
