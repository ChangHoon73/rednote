package kr.co.yenglish.rednote.util.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import kr.co.yenglish.rednote.Constants;
import kr.co.yenglish.rednote.R;

/**
 * Created by Hoon on 2016-11-12.
 */

public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = Constants.CUSTOM_LOADING_DAILOG_DIM;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.loading_animation_layout);
    }


}