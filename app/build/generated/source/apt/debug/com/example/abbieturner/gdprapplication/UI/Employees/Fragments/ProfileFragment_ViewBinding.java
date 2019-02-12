// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  @UiThread
  public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
    this.target = target;

    target.btn_chg_mail = Utils.findRequiredViewAsType(source, R.id.btn_chg_mail, "field 'btn_chg_mail'", Button.class);
    target.btn_chg_pass = Utils.findRequiredViewAsType(source, R.id.btn_chg_pass, "field 'btn_chg_pass'", Button.class);
    target.btn_chg_data = Utils.findRequiredViewAsType(source, R.id.btn_chg_data, "field 'btn_chg_data'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_chg_mail = null;
    target.btn_chg_pass = null;
    target.btn_chg_data = null;
  }
}
