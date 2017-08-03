package me.ashif.sampleapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import me.ashif.sampleapp.R;
import me.ashif.sampleapp.databinding.ActivityMainBinding;
import me.ashif.sampleapp.di.modules.AppModule;
import me.ashif.sampleapp.util.AppUtils;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    private ActivityMainBinding mBinding;

    @Inject DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;
    @Inject AppUtils mAppUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        String something = mAppUtils.ShowMessage();
        mBinding.message.setText(something);
        mBinding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mBinding.message.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mBinding.message.setText(R.string.title_dashboard);
                    mBinding.message.append(" " +mAppUtils.ShowMessage());
                    return true;
                case R.id.navigation_notifications:
                    mBinding.message.setText(R.string.title_notifications);
                    mBinding.message.append(" " +mAppUtils.ShowMessage());
                    return true;
            }
            return false;
        }
    };

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mDispatchingAndroidInjector;
    }
}
