package com.akamahesh.tickyprogress;


import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by akaMahesh on 4/8/17.
 * copyright to : CustomProgressBar Bhatt
 * contact : mckay1718@gmail.com
 */

public class ColorArcProgressBar extends ProgressBar {

  public ColorArcProgressBar(Context context) {
    super(context);
    changingColor();
  }

  public ColorArcProgressBar(Context context, AttributeSet attrs) {
    super(context, attrs);
    changingColor();
  }

  public ColorArcProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    changingColor();
  }

  @Override
  public void setVisibility(int visibility) {
    super.setVisibility(visibility);
    if (visibility == VISIBLE) {
      startTimer();
    } else {
      stopTimer();
    }
  }

  public void changingColor() {
    // fixes pre-Lollipop progressBar indeterminateDrawable tinting
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
      Drawable wrapDrawable = DrawableCompat.wrap(getIndeterminateDrawable());
      DrawableCompat.setTint(wrapDrawable, TickUtil.generateRandomColor());
      setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
    } else {
      getIndeterminateDrawable()
          .setColorFilter(TickUtil.generateRandomColor(), PorterDuff.Mode.SRC_IN);
    }
  }


  private Timer timer;
  private TimerTask timerTask;
  private Handler handler = new Handler();

  public void startTimer() {
    timer = new Timer();
    timerTask = new TimerTask() {
      @Override
      public void run() {
        handler.post(new Runnable() {
          @Override
          public void run() {
            changingColor();
          }
        });
      }
    };
    timer.schedule(timerTask, 1, 1000);
  }

  void stopTimer() {
    if (timer != null) {
      timer.cancel();
      timer.purge();
    }

  }

}
