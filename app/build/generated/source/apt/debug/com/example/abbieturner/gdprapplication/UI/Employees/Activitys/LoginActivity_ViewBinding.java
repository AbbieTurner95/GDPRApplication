// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.etMail = Utils.findRequiredViewAsType(source, R.id.et_mail, "field 'etMail'", EditText.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.et_pass, "field 'etPass'", EditText.class);
    target.tvRegister = Utils.findRequiredViewAsType(source, R.id.tv_register, "field 'tvRegister'", TextView.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'login'", Button.class);
    target.drawer = Utils.findRequiredViewAsType(source, R.id.drawer_layout, "field 'drawer'", DrawerLayout.class);
    target.navigationView = Utils.findRequiredViewAsType(source, R.id.nav_view, "field 'navigationView'", NavigationView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tv_forget_password = Utils.findRequiredViewAsType(source, R.id.tv_forget_password, "field 'tv_forget_password'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etMail = null;
    target.etPass = null;
    target.tvRegister = null;
    target.login = null;
    target.drawer = null;
    target.navigationView = null;
    target.toolbar = null;
    target.tv_forget_password = null;
  }
}
