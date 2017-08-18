package me.ashif.sampleapp.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Ashif on 16/8/17,August,2017
 * github.com/SheikhZayed
 */

public class DialogUtils {

  private static ProgressDialog mProgressDialog;

//  public DialogUtils(ProgressDialog mProgressDialog) {
//    this.mProgressDialog = mProgressDialog;
//  }

  public DialogUtils() {
  }

  public void showProgress(Context context, String message) {
    mProgressDialog = new ProgressDialog(context);
    if (!mProgressDialog.isShowing()) {
      mProgressDialog.setMessage(message);
      mProgressDialog.show();
    }
  }

  public void hideProgress() {
    mProgressDialog.cancel();
  }
}
