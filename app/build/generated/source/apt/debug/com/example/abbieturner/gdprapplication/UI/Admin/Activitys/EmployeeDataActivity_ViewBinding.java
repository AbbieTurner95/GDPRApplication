// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Admin.Activitys;

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

public class EmployeeDataActivity_ViewBinding implements Unbinder {
  private EmployeeDataActivity target;

  @UiThread
  public EmployeeDataActivity_ViewBinding(EmployeeDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EmployeeDataActivity_ViewBinding(EmployeeDataActivity target, View source) {
    this.target = target;

    target.user_image = Utils.findRequiredViewAsType(source, R.id.user_image, "field 'user_image'", ImageView.class);
    target.user_name = Utils.findRequiredViewAsType(source, R.id.user_name, "field 'user_name'", TextView.class);
    target.user_address = Utils.findRequiredViewAsType(source, R.id.user_address, "field 'user_address'", TextView.class);
    target.user_fax = Utils.findRequiredViewAsType(source, R.id.user_fax, "field 'user_fax'", TextView.class);
    target.user_number = Utils.findRequiredViewAsType(source, R.id.user_number, "field 'user_number'", TextView.class);
    target.user_email = Utils.findRequiredViewAsType(source, R.id.user_email, "field 'user_email'", TextView.class);
    target.user_lang = Utils.findRequiredViewAsType(source, R.id.user_lang, "field 'user_lang'", TextView.class);
    target.user_workplace = Utils.findRequiredViewAsType(source, R.id.user_workplace, "field 'user_workplace'", TextView.class);
    target.user_workhours = Utils.findRequiredViewAsType(source, R.id.user_workhours, "field 'user_workhours'", TextView.class);
    target.user_medicalcond = Utils.findRequiredViewAsType(source, R.id.user_medicalcond, "field 'user_medicalcond'", TextView.class);
    target.user_ethnicity = Utils.findRequiredViewAsType(source, R.id.user_ethnicity, "field 'user_ethnicity'", TextView.class);
    target.delete_data_btn = Utils.findRequiredViewAsType(source, R.id.delete_data_btn, "field 'delete_data_btn'", Button.class);
    target.contact_emp_btn = Utils.findRequiredViewAsType(source, R.id.contact_emp_btn, "field 'contact_emp_btn'", Button.class);
    target.notifiation_btn = Utils.findRequiredViewAsType(source, R.id.notification_btn, "field 'notifiation_btn'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmployeeDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.user_image = null;
    target.user_name = null;
    target.user_address = null;
    target.user_fax = null;
    target.user_number = null;
    target.user_email = null;
    target.user_lang = null;
    target.user_workplace = null;
    target.user_workhours = null;
    target.user_medicalcond = null;
    target.user_ethnicity = null;
    target.delete_data_btn = null;
    target.contact_emp_btn = null;
    target.notifiation_btn = null;
  }
}
