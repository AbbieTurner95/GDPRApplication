// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainFragment_ViewBinding implements Unbinder {
  private MainFragment target;

  @UiThread
  public MainFragment_ViewBinding(MainFragment target, View source) {
    this.target = target;

    target.iv_user_profile = Utils.findRequiredViewAsType(source, R.id.iv_user_profile, "field 'iv_user_profile'", ImageView.class);
    target.tv_user_name = Utils.findRequiredViewAsType(source, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    target.request_data = Utils.findRequiredViewAsType(source, R.id.request_btn, "field 'request_data'", Button.class);
    target.delete_data_btn = Utils.findRequiredViewAsType(source, R.id.delete_data_btn, "field 'delete_data_btn'", Button.class);
    target.log_out_btn = Utils.findRequiredViewAsType(source, R.id.log_out_btn, "field 'log_out_btn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iv_user_profile = null;
    target.tv_user_name = null;
    target.request_data = null;
    target.delete_data_btn = null;
    target.log_out_btn = null;
  }
}
