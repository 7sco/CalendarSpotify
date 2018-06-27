// Generated code from Butter Knife. Do not modify!
package com.example.franciscoandrade.calendarmobile.presentation.recyclerView;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.franciscoandrade.calendarmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CalendarAdapter$CalendarViewHolder_ViewBinding implements Unbinder {
  private CalendarAdapter.CalendarViewHolder target;

  @UiThread
  public CalendarAdapter$CalendarViewHolder_ViewBinding(CalendarAdapter.CalendarViewHolder target,
      View source) {
    this.target = target;

    target.dayTV = Utils.findRequiredViewAsType(source, R.id.day, "field 'dayTV'", TextView.class);
    target.number = Utils.findRequiredViewAsType(source, R.id.number, "field 'number'", TextView.class);
    target.remainders = Utils.findRequiredViewAsType(source, R.id.remainders, "field 'remainders'", TextView.class);
    target.remainders_line = Utils.findRequiredViewAsType(source, R.id.remainders_line, "field 'remainders_line'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CalendarAdapter.CalendarViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dayTV = null;
    target.number = null;
    target.remainders = null;
    target.remainders_line = null;
  }
}
