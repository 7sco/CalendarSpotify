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

public class EventAdapter$EventViewHolder_ViewBinding implements Unbinder {
  private EventAdapter.EventViewHolder target;

  @UiThread
  public EventAdapter$EventViewHolder_ViewBinding(EventAdapter.EventViewHolder target,
      View source) {
    this.target = target;

    target.titleTv = Utils.findRequiredViewAsType(source, R.id.title_tv, "field 'titleTv'", TextView.class);
    target.timeTv = Utils.findRequiredViewAsType(source, R.id.time_tv, "field 'timeTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventAdapter.EventViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleTv = null;
    target.timeTv = null;
  }
}
