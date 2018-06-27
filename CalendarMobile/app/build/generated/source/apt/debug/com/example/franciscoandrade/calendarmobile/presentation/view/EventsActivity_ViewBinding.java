// Generated code from Butter Knife. Do not modify!
package com.example.franciscoandrade.calendarmobile.presentation.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.franciscoandrade.calendarmobile.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventsActivity_ViewBinding implements Unbinder {
  private EventsActivity target;

  private View view2131230849;

  private View view2131230747;

  private View view2131230850;

  private View view2131230857;

  @UiThread
  public EventsActivity_ViewBinding(EventsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EventsActivity_ViewBinding(final EventsActivity target, View source) {
    this.target = target;

    View view;
    target.currentDay = Utils.findRequiredViewAsType(source, R.id.currentDay, "field 'currentDay'", TextView.class);
    target.eventTitle = Utils.findRequiredViewAsType(source, R.id.event_title, "field 'eventTitle'", EditText.class);
    target.timeTextView = Utils.findRequiredViewAsType(source, R.id.time_textView, "field 'timeTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.pick_Time, "field 'pickTime' and method 'onViewClicked'");
    target.pickTime = Utils.castView(view, R.id.pick_Time, "field 'pickTime'", Button.class);
    view2131230849 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.addEvent_btn, "field 'addEventBtn' and method 'onViewClicked'");
    target.addEventBtn = Utils.castView(view, R.id.addEvent_btn, "field 'addEventBtn'", Button.class);
    view2131230747 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.eventInfo = Utils.findRequiredViewAsType(source, R.id.event_info, "field 'eventInfo'", TextView.class);
    target.eventsRv = Utils.findRequiredViewAsType(source, R.id.events_rv, "field 'eventsRv'", RecyclerView.class);
    target.endtimeTextView = Utils.findRequiredViewAsType(source, R.id.endtime_textView, "field 'endtimeTextView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.pick_TimeEnd, "field 'pickTimeEnd' and method 'onViewClicked'");
    target.pickTimeEnd = Utils.castView(view, R.id.pick_TimeEnd, "field 'pickTimeEnd'", Button.class);
    view2131230850 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.retry_button, "field 'retryButton' and method 'onViewClicked'");
    target.retryButton = Utils.castView(view, R.id.retry_button, "field 'retryButton'", Button.class);
    view2131230857 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EventsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.currentDay = null;
    target.eventTitle = null;
    target.timeTextView = null;
    target.pickTime = null;
    target.addEventBtn = null;
    target.eventInfo = null;
    target.eventsRv = null;
    target.endtimeTextView = null;
    target.pickTimeEnd = null;
    target.retryButton = null;

    view2131230849.setOnClickListener(null);
    view2131230849 = null;
    view2131230747.setOnClickListener(null);
    view2131230747 = null;
    view2131230850.setOnClickListener(null);
    view2131230850 = null;
    view2131230857.setOnClickListener(null);
    view2131230857 = null;
  }
}
