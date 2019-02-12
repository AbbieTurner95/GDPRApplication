// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Admin.Activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdminHomeActivity_ViewBinding implements Unbinder {
  private AdminHomeActivity target;

  @UiThread
  public AdminHomeActivity_ViewBinding(AdminHomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AdminHomeActivity_ViewBinding(AdminHomeActivity target, View source) {
    this.target = target;

    target.bottomNavigationView = Utils.findRequiredViewAsType(source, R.id.bottomNavigationView, "field 'bottomNavigationView'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdminHomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bottomNavigationView = null;
  }
}
