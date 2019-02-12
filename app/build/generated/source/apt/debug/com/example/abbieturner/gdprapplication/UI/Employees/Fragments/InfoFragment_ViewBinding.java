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

public class InfoFragment_ViewBinding implements Unbinder {
  private InfoFragment target;

  @UiThread
  public InfoFragment_ViewBinding(InfoFragment target, View source) {
    this.target = target;

    target.about_btn = Utils.findRequiredViewAsType(source, R.id.about_btn, "field 'about_btn'", Button.class);
    target.contactus_btn = Utils.findRequiredViewAsType(source, R.id.contactus_btn, "field 'contactus_btn'", Button.class);
    target.faqs_btn = Utils.findRequiredViewAsType(source, R.id.faqs_btn, "field 'faqs_btn'", Button.class);
    target.why_btn = Utils.findRequiredViewAsType(source, R.id.why_btn, "field 'why_btn'", Button.class);
    target.how_btn = Utils.findRequiredViewAsType(source, R.id.how_data_used_btn, "field 'how_btn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InfoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.about_btn = null;
    target.contactus_btn = null;
    target.faqs_btn = null;
    target.why_btn = null;
    target.how_btn = null;
  }
}
