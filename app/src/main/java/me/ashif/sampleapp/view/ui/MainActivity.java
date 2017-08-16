package me.ashif.sampleapp.view.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.google.firebase.crash.FirebaseCrash;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import javax.inject.Inject;
import me.ashif.sampleapp.R;
import me.ashif.sampleapp.databinding.ActivityMainBinding;
import me.ashif.sampleapp.util.AppUtils;
import me.ashif.sampleapp.view.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;
  @Inject
  AppUtils mAppUtils;
  private ActivityMainBinding mBinding;
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = item -> {
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
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    setViewListeners();
    showHomeAsDefault();

    FirebaseCrash.log("It Works !");
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
