// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListFragment_ViewBinding implements Unbinder {
  private ListFragment target;

  @UiThread
  public ListFragment_ViewBinding(ListFragment target, View source) {
    this.target = target;

    target.employee_rv = Utils.findRequiredViewAsType(source, R.id.employees_rv, "field 'employee_rv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.employee_rv = null;
  }
}
