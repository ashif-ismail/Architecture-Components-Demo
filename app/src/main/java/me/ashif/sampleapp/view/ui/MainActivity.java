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
import me.ashif.sampleapp.view.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> mDispatchingAndroidInjector;
  private ActivityMainBinding mBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    showHomeAsDefault();

    FirebaseCrash.log("It Works !");
  }

  private void showHomeAsDefault() {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.parent, HomeFragment.newInstance());
    transaction.commit();
  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return mDispatchingAndroidInjector;
  }
}
