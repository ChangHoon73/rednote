package kr.co.yenglish.rednote.member;

import android.os.Bundle;
import android.view.View;

import kr.co.yenglish.rednote.BaseDrawerActivity;
import kr.co.yenglish.rednote.R;

public class MemberListActivity extends BaseDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(R.layout.member_list, frameLayout);

        setTitle("회원관리");
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigationView.getMenu().getItem(2).setChecked(true);
    }

}
