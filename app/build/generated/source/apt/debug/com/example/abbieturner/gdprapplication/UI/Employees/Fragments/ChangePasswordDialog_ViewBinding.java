// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordDialog_ViewBinding implements Unbinder {
  private ChangePasswordDialog target;

  @UiThread
  public ChangePasswordDialog_ViewBinding(ChangePasswordDialog target, View source) {
    this.target = target;

    target.et_old_pass = Utils.findRequiredViewAsType(source, R.id.et_old_pass, "field 'et_old_pass'", TextInputEditText.class);
    target.et_new_pass = Utils.findRequiredViewAsType(source, R.id.et_new_pass, "field 'et_new_pass'", TextInputEditText.class);
    target.et_conf_pass = Utils.findRequiredViewAsType(source, R.id.et_conf_pass, "field 'et_conf_pass'", TextInputEditText.class);
    target.btn_chg_pass = Utils.findRequiredViewAsType(source, R.id.btn_chg_pass, "field 'btn_chg_pass'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePasswordDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_old_pass = null;
    target.et_new_pass = null;
    target.et_conf_pass = null;
    target.btn_chg_pass = null;
  }
}
