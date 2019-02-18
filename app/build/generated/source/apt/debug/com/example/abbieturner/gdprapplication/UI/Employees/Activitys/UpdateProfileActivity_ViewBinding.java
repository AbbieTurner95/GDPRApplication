// Generated code from Butter Knife. Do not modify!
package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.abbieturner.gdprapplication.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdateProfileActivity_ViewBinding implements Unbinder {
  private UpdateProfileActivity target;

  @UiThread
  public UpdateProfileActivity_ViewBinding(UpdateProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UpdateProfileActivity_ViewBinding(UpdateProfileActivity target, View source) {
    this.target = target;

    target.img_profile = Utils.findRequiredViewAsType(source, R.id.img_profile, "field 'img_profile'", ImageView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.et_address, "field 'etAddress'", EditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", EditText.class);
    target.etFax = Utils.findRequiredViewAsType(source, R.id.et_fax, "field 'etFax'", EditText.class);
    target.etLang = Utils.findRequiredViewAsType(source, R.id.et_lang, "field 'etLang'", Spinner.class);
    target.etEthn = Utils.findRequiredViewAsType(source, R.id.et_ethnicity, "field 'etEthn'", Spinner.class);
    target.etMedical = Utils.findRequiredViewAsType(source, R.id.et_medical, "field 'etMedical'", EditText.class);
    target.etWorkHour = Utils.findRequiredViewAsType(source, R.id.et_work_hour, "field 'etWorkHour'", EditText.class);
    target.etWorkPlace = Utils.findRequiredViewAsType(source, R.id.et_work_place, "field 'etWorkPlace'", EditText.class);
    target.btn_update = Utils.findRequiredViewAsType(source, R.id.btn_update, "field 'btn_update'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UpdateProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.img_profile = null;
    target.etName = null;
    target.etAddress = null;
    target.etPhone = null;
    target.etFax = null;
    target.etLang = null;
    target.etEthn = null;
    target.etMedical = null;
    target.etWorkHour = null;
    target.etWorkPlace = null;
    target.btn_update = null;
  }
}