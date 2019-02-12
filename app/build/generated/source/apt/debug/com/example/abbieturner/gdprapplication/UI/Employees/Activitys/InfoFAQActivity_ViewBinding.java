// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InfoFAQActivity_ViewBinding implements Unbinder {
  private InfoFAQActivity target;

  @UiThread
  public InfoFAQActivity_ViewBinding(InfoFAQActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public InfoFAQActivity_ViewBinding(InfoFAQActivity target, View source) {
    this.target = target;

    target.reqdata_btn = Utils.findRequiredViewAsType(source, R.id.reqdata_btn, "field 'reqdata_btn'", Button.class);
    target.update_btn = Utils.findRequiredViewAsType(source, R.id.update_btn, "field 'update_btn'", Button.class);
    target.tech_btn = Utils.findRequiredViewAsType(source, R.id.tech_btn, "field 'tech_btn'", Button.class);
    target.del_btn = Utils.findRequiredViewAsType(source, R.id.del_btn, "field 'del_btn'", Button.class);
    target.kept_btn = Utils.findRequiredViewAsType(source, R.id.kept_btn, "field 'kept_btn'", Button.class);
    target.contact_q_btn = Utils.findRequiredViewAsType(source, R.id.contact_q_btn, "field 'contact_q_btn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InfoFAQActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.reqdata_btn = null;
    target.update_btn = null;
    target.tech_btn = null;
    target.del_btn = null;
    target.kept_btn = null;
    target.contact_q_btn = null;
  }
}
