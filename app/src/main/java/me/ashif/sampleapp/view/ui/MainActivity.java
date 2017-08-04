package me.ashif.sampleapp.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.ashif.sampleapp.R;
import me.ashif.sampleapp.databinding.ActivityMainBinding;
import me.ashif.sampleapp.view.ui.home.HomeFragment;
import me.ashif.sampleapp.util.AppUtils;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    @Inject DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;
    @Inject AppUtils mAppUtils;
    private ActivityMainBinding mBinding;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                    selectedFragment = HomeFragment.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.parent, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setViewListeners();
        showHomeAsDefault();
    }

    private void showHomeAsDefault() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.parent, HomeFragment.newInstance());
        transaction.commit();
    }

    private void setViewListeners() {
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }
}
