// Generated code from Butter Knife. Do not modify!
package com.example.franciscoandrade.calendarmobile.presentation.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.franciscoandrade.calendarmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CalendarActivity_ViewBinding implements Unbinder {
  private CalendarActivity target;

  private View view2131230836;

  @UiThread
  public CalendarActivity_ViewBinding(CalendarActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CalendarActivity_ViewBinding(final CalendarActivity target, View source) {
    this.target = target;

    View view;
    target.yearTV = Utils.findRequiredViewAsType(source, R.id.year, "field 'yearTV'", TextView.class);
    target.monthTV = Utils.findRequiredViewAsType(source, R.id.month, "field 'monthTV'", TextView.class);
    target.calendarRv = Utils.findRequiredViewAsType(source, R.id.calendar_rv, "field 'calendarRv'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.noInternet_btn, "field 'noInternetBtn' and method 'onViewClicked'");
    target.noInternetBtn = Utils.castView(view, R.id.noInternet_btn, "field 'noInternetBtn'", Button.class);
    view2131230836 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CalendarActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.yearTV = null;
    target.monthTV = null;
    target.calendarRv = null;
    target.toolbar = null;
    target.noInternetBtn = null;

    view2131230836.setOnClickListener(null);
    view2131230836 = null;
  }
}
