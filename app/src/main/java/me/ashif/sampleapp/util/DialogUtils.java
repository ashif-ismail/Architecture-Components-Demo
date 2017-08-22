package me.ashif.sampleapp.util;

import android.app.ProgressDialog;
import android.content.Context;
import javax.inject.Inject;

/**
 * Created by Ashif on 16/8/17,August,2017
 * github.com/SheikhZayed
 */

public class DialogUtils {

  private static ProgressDialog mProgressDialog;
  @Inject
  Context mContext;

//  public DialogUtils(ProgressDialog mProgressDialog) {
//    this.mProgressDialog = mProgressDialog;
//  }

  public DialogUtils() {
  }

  public void showProgress(String message) {
    mProgressDialog = new ProgressDialog(mContext);
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.setMessage(message);
      mProgressDialog.show();
    }
  }

  public void hideProgress() {
    mProgressDialog.cancel();
  }
}
