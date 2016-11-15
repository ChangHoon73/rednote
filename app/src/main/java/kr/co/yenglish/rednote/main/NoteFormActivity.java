package kr.co.yenglish.rednote.main;

import android.os.Bundle;
import android.view.View;

import kr.co.yenglish.rednote.BaseDrawerActivity;
import kr.co.yenglish.rednote.R;

public class NoteFormActivity extends BaseDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.note_form, frameLayout);

        setTitle("출석");
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigationView.getMenu().getItem(1).setChecked(true);
    }

}
