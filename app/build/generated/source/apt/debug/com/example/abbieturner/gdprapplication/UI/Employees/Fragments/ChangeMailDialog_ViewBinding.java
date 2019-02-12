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

public class ChangeMailDialog_ViewBinding implements Unbinder {
  private ChangeMailDialog target;

  @UiThread
  public ChangeMailDialog_ViewBinding(ChangeMailDialog target, View source) {
    this.target = target;

    target.et_mail = Utils.findRequiredViewAsType(source, R.id.et_mail, "field 'et_mail'", TextInputEditText.class);
    target.et_pass = Utils.findRequiredViewAsType(source, R.id.et_pass, "field 'et_pass'", TextInputEditText.class);
    target.btn_chg_mail = Utils.findRequiredViewAsType(source, R.id.btn_chg_mail, "field 'btn_chg_mail'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangeMailDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.et_mail = null;
    target.et_pass = null;
    target.btn_chg_mail = null;
  }
}
