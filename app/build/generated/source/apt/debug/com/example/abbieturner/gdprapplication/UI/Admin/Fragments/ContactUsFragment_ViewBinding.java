// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import com.google.android.gms.maps.MapView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactUsFragment_ViewBinding implements Unbinder {
  private ContactUsFragment target;

  @UiThread
  public ContactUsFragment_ViewBinding(ContactUsFragment target, View source) {
    this.target = target;

    target.mapView = Utils.findRequiredViewAsType(source, R.id.google_maps, "field 'mapView'", MapView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactUsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mapView = null;
  }
}
