package com.akamahesh.tickyprogress;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;


/**
 * Created by akaMahesh on 16/8/17
 * contact : mckay1718@gmail.com
 */

public class TickProgress extends FrameLayout {

  private static int degreeInc = 1;
  private ImageView correctImageView;
  private ImageView errorImageView;
  private ColorArcProgressBar progressBar;
  private Context context;

  public TickProgress(@NonNull Context context) {
    super(context);
    this.context = context;
    addTickProgressView(context);
  }


  public TickProgress(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
    addTickProgressView(context);
  }

  public TickProgress(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.context = context;
    addTickProgressView(context);
  }


  private void addTickProgressView(Context context) {
    correctImageView = new ImageView(context);
    errorImageView = new ImageView(context);
    correctImageView
        .setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_green_500_24dp));
    errorImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel));
    addView(correctImageView);
    addView(errorImageView);
    progressBar = new ColorArcProgressBar(context);
    addView(progressBar);
    showProgress();
  }

  public void showProgress() {
    progressBar.setVisibility(VISIBLE);
    correctImageView.setVisibility(INVISIBLE);
    errorImageView.setVisibility(INVISIBLE);
  }


  public void showError() {
    progressBar.setVisibility(INVISIBLE);
    errorImageView.setVisibility(VISIBLE);
    errorImageView.animate().rotation(360 * degreeInc++).setDuration(500).start();
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        errorImageView.setVisibility(INVISIBLE);
      }
    }, 1500);

  }

  public void showSuccess() {
    progressBar.setVisibility(INVISIBLE);
    correctImageView.setVisibility(VISIBLE);
    correctImageView.animate().rotation(360 * degreeInc++).setDuration(500).start();
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        correctImageView.setVisibility(INVISIBLE);
      }
    }, 1500);
  }
}
