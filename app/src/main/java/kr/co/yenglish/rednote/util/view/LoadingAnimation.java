package kr.co.yenglish.rednote.util.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import kr.co.yenglish.rednote.Constants;
import kr.co.yenglish.rednote.R;

/**
 * Created by Hoon on 2016-11-12.
 */

public class LoadingAnimation extends ImageView {

    public LoadingAnimation(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setAnimation(attrs);
    }

    public LoadingAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAnimation(attrs);
    }

    public LoadingAnimation(Context context) {
        super(context);
        setAnimation(Constants.ANIMATION_FRAME_COUNT, Constants.ANIMATION_LOADING_TIME);
    }

    private void setAnimation(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs,
                R.styleable.LoadingAnimation);
        int frameCount = a.getInt(R.styleable.LoadingAnimation_frameCount, Constants.ANIMATION_FRAME_COUNT);
        int duration = a.getInt(R.styleable.LoadingAnimation_duration, Constants.ANIMATION_LOADING_TIME);
        a.recycle();

        setAnimation(frameCount, duration);
    }

    public void setAnimation(final int frameCount, final int duration) {
        Animation animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.loading_anim);
        animation.setDuration(duration);
        animation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float)Math.floor(input*frameCount)/frameCount;
            }
        });
        startAnimation(animation);
    }

}
