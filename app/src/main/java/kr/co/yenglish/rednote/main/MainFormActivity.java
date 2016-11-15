package kr.co.yenglish.rednote.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.yenglish.rednote.BaseDrawerActivity;
import kr.co.yenglish.rednote.R;


public class MainFormActivity extends BaseDrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.main_form, frameLayout);

        Button mf_btn_01 = (Button) view.findViewById(R.id.mf_btn_01);

        mf_btn_01.setOnClickListener(mClick);

        setTitle("메인폼");
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    View.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.mf_btn_01:

                    break;
            }
        }
    };


}
