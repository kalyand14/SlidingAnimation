package com.example.verticalstack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements AnimationListener {

	private FrameLayout frame;
	private FrameLayout l1;
	private FrameLayout l2;
	private FrameLayout selectedFrame;
	FrameLayout internalLayout;

	private float lastY;

	Animation animPushUp, animPushDown;
	TranslateAnimation translation;

	private String[] titles = { "Statement New", "Liberty feature ", " GCP " };
	private int[] images = new int[] { R.drawable.nature, R.drawable.nature2,
			R.drawable.sky };
	private FrameLayout[] multiFrames = new FrameLayout[titles.length];
	private static int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		frame = new FrameLayout(this);
		frame.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		animPushUp = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.out_from_up);
		animPushDown = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.in_from_up);

		for (int i = 0; i < titles.length; i++) {

			frame.addView(createView(i));

		}
		count = titles.length - 1;

		frame.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				/*
				 * if (count >= 0) { selectedFrame.requestFocusFromTouch();
				 * 
				 * multiFrames[count].startAnimation(animPushUp);
				 * multiFrames[count].setVisibility(View.GONE); count--;
				 * 
				 * } return false;
				 * 
				 * }
				 */
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					lastY = event.getY();

					return true;

				}

				case MotionEvent.ACTION_UP: {
					// float currentX = touchevent.getX();
					float currentY = event.getY();
					float diffPos = lastY - currentY;

					if (lastY > currentY) {
						if (count >= 0) {

							if (diffPos < frame.getHeight() / 2) {
								// multiFrames[count].startAnimation(animPushUp);

								/*
								 * translation = new TranslateAnimation(0f, 0F,
								 * diffPos, multiFrames[count].getHeight());
								 * translation.setStartOffset(500);
								 * translation.setDuration(3500);
								 * translation.setFillAfter(false); translation
								 * .setInterpolator(new BounceInterpolator());
								 * multiFrames
								 * [count].startAnimation(translation);
								 */

								multiFrames[count].startAnimation(animPushDown);

							}

							else {
								selectedFrame.requestFocusFromTouch();

								multiFrames[count].startAnimation(animPushUp);
								multiFrames[count].setVisibility(View.GONE);
								count--;
							}

						}

					}
					break;
				}

				/*
				 * case MotionEvent.ACTION_MOVE: { event.get break; }
				 */

				}
				return false;
			}
		});

		for (int i = 0; i < multiFrames.length; i++) {
			selectedFrame = multiFrames[i];
			multiFrames[i].setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					/*
					 * selectedFrame.startAnimation(animPushUp);
					 * frame.clearDisappearingChildren();
					 */

					selectedFrame.setFocusableInTouchMode(true);
					/**
					 * v.startAnimation(animPushUp);
					 * v.setVisibility(View.INVISIBLE);
					 */

					return false;
				}
			});

			setContentView(frame);
		}

	}

	private View createView(int i) {

		internalLayout = new FrameLayout(this);
		internalLayout.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		ImageView image = new ImageView(this);
		image.setImageResource(images[i]);
		image.setScaleType(ImageView.ScaleType.FIT_XY);
		TextView txt = new TextView(this);
		txt.setText(titles[i]);
		txt.setGravity(Gravity.CENTER);
		internalLayout.addView(image);
		internalLayout.addView(txt);
		multiFrames[i] = internalLayout;
		/*
		 * frame1.setOnTouchListener(new View.OnTouchListener() {
		 * 
		 * @Override public boolean onTouch(View v, MotionEvent event) {
		 * frame1.startAnimation(animPushUp); frame.removeView(frame1);
		 * 
		 * return false; } });
		 */
		return internalLayout;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		/* frame.removeView(l2); */
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
